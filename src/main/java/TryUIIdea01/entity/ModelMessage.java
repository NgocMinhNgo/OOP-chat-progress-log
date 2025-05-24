package TryUIIdea01.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelMessage<T extends Product> extends Message {
    private final List<T> recommendedProducts;

    public ModelMessage(String textContent, List<T> recommendedProducts) {
        super("model", textContent);
        this.recommendedProducts = recommendedProducts != null ?
                new ArrayList<>(recommendedProducts) :
                Collections.emptyList();
    }

    // Trả về danh sách read-only với wildcard
    public List<? extends Product> getRecommendedProducts() {
        return new ArrayList<>(recommendedProducts); // Hoặc Collections.unmodifiableList()
    }
}

//public class ModelMessage extends Message {
//    private final List<Product> recommendedProducts;
//
//    public ModelMessage(String textContent, List<Product> recommendedProducts) {
//        super("model", textContent);
//        this.recommendedProducts = recommendedProducts != null ?
//                new ArrayList<>(recommendedProducts) :
//                Collections.emptyList();
//    }
//
//    public List<Product> getRecommendedProducts() {
//        return Collections.unmodifiableList(recommendedProducts);
//    }
//}

//public class ModelMessage<T extends Product> extends Message {
//    private final List<T> recommendedProducts;
//
//    public ModelMessage(String textContent, List<T> recommendedProducts) {
//        super("model", textContent);
//        this.recommendedProducts = recommendedProducts != null ?
//                new ArrayList<>(recommendedProducts) :
//                Collections.emptyList();
//    }
//
//    public List<T> getRecommendedProducts() {
//        return Collections.unmodifiableList(recommendedProducts);
//    }
//}