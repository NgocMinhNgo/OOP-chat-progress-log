package chatAppTrial01.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import com.google.gson.Gson;
import chatAppTrial01.entity.Message;
import okhttp3.*;
import java.io.IOException;
import java.util.List;

public class CallGeminiAPI {
    private static final String API_KEY = "AIzaSyCnIYbumBIR79szZxSCyEUpAYEk5Sb8jEY";
    // Updated URL with the correct model name
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent";

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    //public String generateContent(List<Message> conversation) throws IOException {
    public String generateContent(String message) throws IOException {

        // Simplified request structure
        JsonObject requestBody = new JsonObject();
        JsonArray contents = new JsonArray();
        JsonObject content = new JsonObject();

        JsonArray parts = new JsonArray();
        JsonObject part = new JsonObject();
        //part.addProperty("text", "What is the capital of Vietnam");
        part.addProperty("text", message);

        parts.add(part);

        content.add("parts", parts);
        contents.add(content);
        requestBody.add("contents", contents);

        Request request = new Request.Builder()
                .url(API_URL + "?key=" + API_KEY)
                .post(RequestBody.create(
                        MediaType.parse("application/json"),
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

                //System.out.println(text);
                finalResponse = text;
            }

        return finalResponse;

    }


//    private String createRequestBody(List<Message> conversation) {
//        // Tạo JSON request theo format Gemini API yêu cầu
//        StringBuilder sb = new StringBuilder();
//        sb.append("{\"contents\":[");
//
//        for (Message message : conversation) {
//            sb.append("{\"role\":\"").append(message.getRole()).append("\",");
//            sb.append("\"parts\":[{\"text\":\"").append(escapeJson(message.getContent())).append("\"}]},");
//        }
//
//        if (!conversation.isEmpty()) {
//            sb.deleteCharAt(sb.length() - 1); // Xóa dấu phẩy cuối cùng
//        }
//
//        sb.append("]}");
//        return sb.toString();
//    }
//
//    private String parseResponse(String jsonResponse) {
//        // Phân tích cú pháp JSON response và trích xuất văn bản
//        // Đây là một cách đơn giản, bạn có thể cần điều chỉnh tùy thuộc vào response thực tế
//        int start = jsonResponse.indexOf("\"text\":\"") + 7;
//        int end = jsonResponse.indexOf("\"", start);
//        return jsonResponse.substring(start, end).replace("\\n", "\n");
//    }
//
//    private String escapeJson(String input) {
//        return input.replace("\"", "\\\"")
//                .replace("\n", "\\n")
//                .replace("\r", "\\r")
//                .replace("\t", "\\t");
//    }
}
