package chatAppTrial01.api;

import chatAppTrial01.entity.Product;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import com.google.gson.Gson;
import chatAppTrial01.entity.Message;
import okhttp3.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CallGeminiAPI {
    private static final String API_KEY = "AIzaSyCnIYbumBIR79szZxSCyEUpAYEk5Sb8jEY";
    // Updated URL with the correct model name
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-flash:generateContent";

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

        public List<Product> generateContent(String message) throws IOException {

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

        //return finalResponse;
            // Làm tạm tạm để th xem như thế nào =)))))
            List<Product> demoList = new ArrayList<>();
            demoList.add(new Product("Laptop", 1500.0, "Dell"));
            demoList.add(new Product("Smartphone", 800.0, "Samsung"));
            demoList.add(new Product("Headphones", 100.0, "Sony"));
            demoList.add(new Product("Phone", 100.0, "Sony"));
            demoList.add(new Product("Headphones", 100.0, "Sony"));
            demoList.add(new Product("Bass", 100.0, "Sony"));

            return demoList;
            // kết thúc phần tàm tạm =)))


    }


    //public String generateContent(List<Message> conversation) throws IOException {
//    public String generateContent(String message) throws IOException {
//
//        // Simplified request structure
//        JsonObject requestBody = new JsonObject();
//        JsonArray contents = new JsonArray();
//        JsonObject content = new JsonObject();
//
//        JsonArray parts = new JsonArray();
//        JsonObject part = new JsonObject();
//        //part.addProperty("text", "What is the capital of Vietnam");
//        part.addProperty("text", message);
//
//        parts.add(part);
//
//        content.add("parts", parts);
//        contents.add(content);
//        requestBody.add("contents", contents);
//
//        Request request = new Request.Builder()
//                .url(API_URL + "?key=" + API_KEY)
//                .post(RequestBody.create(
//                        MediaType.parse("application/json"),
//                        gson.toJson(requestBody)))
//                .build();
//
//
//            Response response = client.newCall(request).execute();
//            String responseBody = response.body().string();
//
////            System.out.println("Raw response:");
////            System.out.println(responseBody);
//
//            // Simple parsing for successful responses
//        String finalResponse = "";
//            if (responseBody.contains("candidates")) {
//                //System.out.println("\nExtracted answer:");
//                JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);
//                String text = jsonResponse
//                        .getAsJsonArray("candidates")
//                        .get(0).getAsJsonObject()
//                        .getAsJsonObject("content")
//                        .getAsJsonArray("parts")
//                        .get(0).getAsJsonObject()
//                        .get("text").getAsString();
//
//                //System.out.println(text);
//                finalResponse = text;
//            }
//
//        return finalResponse;
//
//    }


}
