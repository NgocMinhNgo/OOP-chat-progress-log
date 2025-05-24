package TryUIIdea01.analysis;


import TryUIIdea01.filter.FilterCondition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AnalysisResult {
    private final Map<String, FilterCondition> conditions; // Key: attribute name
    //private final Map<String, Object> metadata; // Context bổ sung

    public AnalysisResult(Map<String, FilterCondition> conditions) {
        this.conditions = Collections.unmodifiableMap(conditions);
        //this.metadata = new HashMap<>();
    }

    // Thêm metadata động (ví dụ: confidence score từ AI)
//    public void addMetadata(String key, Object value) {
//        metadata.put(key, value);
//    }

    public List<FilterCondition> getConditions() {
        return new ArrayList<>(conditions.values());
    }

    // Lấy condition theo attribute (cho trường hợp đặc biệt)
//    public Optional<FilterCondition> getCondition(String attribute) {
//        return Optional.ofNullable(conditions.get(attribute));
//    }

    // Chuyển đổi sang JSON (dùng cho logging hoặc API response)
//    public String toJson() {
//        return new Gson().toJson(Map.of(
//                "conditions", conditions,
//                "metadata", metadata
//        ));
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AnalysisResult {\n");
        for (Map.Entry<String, FilterCondition> entry : conditions.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}