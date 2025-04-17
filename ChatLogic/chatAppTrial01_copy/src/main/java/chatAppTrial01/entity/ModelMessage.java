package chatAppTrial01.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelMessage extends Message {
    private final List<Product> recommendedProducts;

    public ModelMessage(String textContent, List<Product> recommendedProducts) {
        super("model", textContent);
        this.recommendedProducts = recommendedProducts != null ?
                new ArrayList<>(recommendedProducts) :
                Collections.emptyList();
    }

    public List<Product> getRecommendedProducts() {
        return Collections.unmodifiableList(recommendedProducts);
    }
}