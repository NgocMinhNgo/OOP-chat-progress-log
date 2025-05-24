package TryUIIdea01.analysis;

import com.google.gson.*;
import TryUIIdea01.filter.FilterCondition;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GeminiService implements LLMService {

    private static final String API_KEY = "AIzaSyCnIYbumBIR79szZxSCyEUpAYEk5Sb8jEY";
    // Updated URL with the correct model name
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent";

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    @Override
    public AnalysisResult analyzeText(String userInput) throws IOException {
//        // Giả lập kết quả từ Gemini API
//        List<FilterCondition> conditions = new ArrayList<>();
//        // Logic phân tích thực tế sẽ call API thật ở đây
//        conditions.add(new FilterCondition("price", "<=", 20000000));
//        conditions.add(new FilterCondition("brand", "in", List.of("Samsung", "Apple")));
//        return new AnalysisResult(conditions);

        // 1 - sẽ trả về cái này
        Map<String, FilterCondition> conditions = new HashMap<String, FilterCondition>();

        // 2 - đủ thứ của Gemini
        String jsonStringResponse = generateContent();

        // 3 - chuyển cái json string thành cái amp để mà trả về ok
        conditions = parseFilters(jsonStringResponse);
        return new AnalysisResult(conditions);


    }

    public Map<String, FilterCondition> parseFilters(String jsonString) {
        Map<String, FilterCondition> conditions = new HashMap<>();

        try {
            // Parse JSON string thành JsonObject
            JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
            JsonArray filters = jsonObject.getAsJsonArray("filters");

            // Duyệt qua từng filter trong mảng
            for (var element : filters) {
                JsonObject filter = element.getAsJsonObject();

                String attribute = filter.get("attribute").getAsString();
                String operator = filter.get("operator").getAsString();
                Object value = parseValue(filter.get("value"));

                // Tạo FilterCondition và thêm vào Map
                conditions.put(attribute, new FilterCondition(attribute, operator, value));
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse filters JSON", e);
        }

        return conditions;
    }

    private Object parseValue(JsonElement valueElement) {
        if (valueElement.isJsonPrimitive()) {
            var primitive = valueElement.getAsJsonPrimitive();
            if (primitive.isNumber()) {
                // Kiểm tra nếu là số nguyên
                if (primitive.getAsString().contains(".")) {
                    return primitive.getAsDouble();
                } else {
                    return primitive.getAsInt();
                }
            } else if (primitive.isBoolean()) {
                return primitive.getAsBoolean();
            }
        }
        // Mặc định trả về dạng string
        return valueElement.getAsString();
    }


    //public List<Product> generateContent(String message) throws IOException {
    //public String generateContent(String message) throws IOException {

    public String generateContent() throws IOException {

        // Simplified request structure
        JsonObject requestBody = new JsonObject();
        JsonArray contents = new JsonArray();
        JsonObject content = new JsonObject();

        JsonArray parts = new JsonArray();
        JsonObject part = new JsonObject();
        String userText = "Bạn là hệ thống trích xuất điều kiện lọc từ yêu cầu người dùng. Tuân thủ nghiêm ngặt:\n" +
                "\n" +
                "1. **DỮ LIỆU MẪU** (Giá trị thực tế của từng thuộc tính, những giá trị dưới đây là một số ví dụ để minh họa, các giá trị không bị bó hẹp trong ví dụ mà thực tế rất đa dạng):\n" +
                "```json\n" +
                "{\n" +
                "  \"id\": [277068431, 277068432, ..], // Số nguyên, dùng để lọc chính xác\n" +
                "  \"brand\": [\"SAMSUNG\", \"APPLE\", \"XIAOMI\", \"OPPO\", ...], // Chữ in hoa\n" +
                "  \"price\": [28990000, 15000000, , ...], // Đơn vị: VND\n" +
                "  \"discountPercent\": [\"15\", \"20\", ...], // Chuỗi phần trăm, ví dụ giảm 15%, 20%\n" +
                "  \"soldQuantity\": [4, 100, 5000, ...], // Số lượng đã bán\n" +
                "  \"batteryCapacity\": [4000, 5000, ...], // mAh\n" +
                "  \"frontCamera\": [\"12.0 MP\", \"32.0 MP\", ...], // Độ phân giải\n" +
                "  \"backCamera\": [\"200.0 MP + 50.0 MP\", \"50.0 MP\", ...], // Nhiều ống kính\n" +
                "  \"GPU\": [\"Adreno 660\", \"Mali-G78\", , ...], // Null nếu không có\n" +
                "  \"origin\": [\"Việt Nam\", \"Trung Quốc\", \"Mỹ\", ...], // Xuất xứ\n" +
                "  \"chargingPort\": [\"USB Type-C\", \"Lightning\", ...], // Cổng sạc\n" +
                "  \"RAM\": [8, 12, ...], // GB\n" +
                "  \"resolution\": [\"1080x2340\", ...], // Độ phân giải màn hình\n" +
                "  \"ROM\": [128, 256, ...], // GB\n" +
                "  \"screenSize\": [6.1, 6.9, ...], // inch\n" +
                "  \"warrantyDuration\": [\"12 Tháng\", \"24 Tháng\", ...], // Thời gian bảo hành\n" +
                "  \"rating\": [4.5, 5.0, ...], // 1-5 sao\n" +
                "  \"numberReview\": [0, 1500, ...], // Số lượt đánh giá\n" +
                "  \"description\": [\"Galaxy S25 Ultra...\", \"iPhone 15 Pro Max...\"] // Văn bản dài\n" +
                "}\n" +
                "\n" +
                "2.**THUỘC TÍNH HỢP LỆ** (Chỉ dùng các attribute sau): \n" +
                "brand, price, discountPercent, soldQuantity, batteryCapacity, frontCamera, backCamera, gpu, origin, chargingPort, ram, resolution, rom, screenSize, warrantyDuration, rating, numberReview\n" +
                "\n" +
                "3. **ĐẦU RA**:\n" +
                "json\n" +
                "{\n" +
                "  \"filters\": [\n" +
                "    {\"attribute\": \"tên_thuộc_tính\", \"operator\": \"toán_tử\", \"value\": \"giá_trị\"}\n" +
                "  ]\n" +
                "}\n" +
                "--> KHÔNG thêm bất kỳ chữ, ký tự nào khác.\n" +
                "\n" +
                "4. **QUY TẮC ÁNH XẠ THÔNG MINH:**\n" +
                "\n" +
                "Giảm giá:\n" +
                "\"Giảm sâu\" → discountPercent >= \"20\"\n" +
                "\n" +
                "\"Ít giảm giá\" → discountPercent <= \"10\"\n" +
                "\n" +
                "Bảo hành:\n" +
                "\"Bảo hành dài\" → warrantyDuration == \"24 Tháng\"\n" +
                "\n" +
                "Đánh giá:\n" +
                "\"Nhiều review\" → numberReview >= 1000\n" +
                "\n" +
                "\"Đánh giá tốt\" → rating >= 4.5\n" +
                "\n" +
                "Mô tả:\n" +
                "\"Có bút S Pen\" → description contains \"S Pen\"\n" +
                "\n" +
                "\"Hỗ trợ AI\" → description contains \"AI\"\n" +
                "\n" +
                "5. **VÍ DỤ CỤ THỂ:**TryFilter01.Test.Test Case Quan Trọng:\n" +
                "Input: \"tôi muốn mua điện thoại Samsung dưới 20 triệu, camera 50MP, ít đánh giá\"\n" +
                "\n" +
                "Expected Output:\n" +
                "{\n" +
                "  \"filters\": [\n" +
                "    {\"attribute\": \"brand\", \"operator\": \"==\", \"value\": \"SAMSUNG\"},\n" +
                "    {\"attribute\": \"price\", \"operator\": \"<=\", \"value\": 20000000},\n" +
                "    {\"attribute\": \"backCamera\", \"operator\": \"contains\", \"value\": \"50.0 MP\"},\n" +
                "    {\"attribute\": \"numberReview\", \"operator\": \"<=\", \"value\": 100}\n" +
                "  ]\n" +
                "}\n" +
                "\n" +
                "Input: \"tôi đang tìm điện thoại giảm trên 15%, bảo hành 2 năm, có GPU Adreno\"\n" +
                "\n" +
                "Output:\n" +
                "{\n" +
                "  \"filters\": [\n" +
                "    {\"attribute\": \"discountPercent\", \"operator\": \"<=\", \"value\": \"-15%\"},\n" +
                "    {\"attribute\": \"warrantyDuration\", \"operator\": \"==\", \"value\": \"24 Tháng\"},\n" +
                "    {\"attribute\": \"GPU\", \"operator\": \"contains\", \"value\": \"Adreno\"}\n" +
                "  ]\n" +
                "}\n" +
                "6. **XỬ LÝ ĐẶC BIỆT:**\n" +
                "Thuộc tính null: Bỏ qua (VD: GPU = null → không lọc)\n" +
                "Chuỗi phức tạp: Dùng contains (VD: BackCamera chứa \"200.0 MP\")\n" +
                "Giá trị số: Luôn dùng đơn vị mặc định (GB, inch, VND...)\n" +
                "Khuyến khích bạn hãy thông minh, hiểu sâu data thực tế trong khi vẫn linh hoạt với yêu cầu người dùng. Hãy đưa ra các điều kiện hợp lý, thông mình và tuần theo các thuộc tính được cung cấp\n" +
                "-----------------------------\n" +
                "Input: \"Tôi muốn mua điện thoại dưới 15 triệu\"";
        part.addProperty("text", userText);
        //part.addProperty("text", message);

        parts.add(part);

        content.add("parts", parts);
        contents.add(content);
        requestBody.add("contents", contents);

        Request request = new Request.Builder()
                .url(API_URL + "?key=" + API_KEY)
                .post(RequestBody.create(
                        MediaType.parse("TryFilter01/application/json"),
                        gson.toJson(requestBody)))
                .build();


        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();

//            System.out.println("Raw response:");
//            System.out.println(responseBody);

        // Simple parsing for successful responses
        String finalResponse = "";
        if (responseBody.contains("candidates")) {
            //System.out.println("\nExtracted answer:");
            JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);
            String text = jsonResponse
                    .getAsJsonArray("candidates")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("content")
                    .getAsJsonArray("parts")
                    .get(0).getAsJsonObject()
                    .get("text").getAsString();

            // Loại bỏ markdown block nếu có
            String cleanedText = text.replaceAll("(?m)^```json\\s*", "")  // dòng bắt đầu với ```json
                    .replaceAll("(?m)^```\\s*", "")      // dòng chỉ là ```
                    .trim();


            System.out.println(cleanedText);
            finalResponse = cleanedText;
        }
        return finalResponse;
    }
}