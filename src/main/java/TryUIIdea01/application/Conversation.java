package TryUIIdea01.application;

import TryUIIdea01.analysis.AnalysisStrategy;
import TryUIIdea01.entity.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.UUID;

public class Conversation {
    private final String id;
    private final ProductType productType;
    private final AnalysisMethod analysisMethod;
    private final ObservableList<Message> messages;
    private final AnalysisStrategy strategy;
    private final LocalDateTime createdAt;

    public Conversation(ProductType productType, AnalysisMethod method) {
        this.id = UUID.randomUUID().toString();
        this.productType = productType;
        this.analysisMethod = method;
        this.messages = FXCollections.observableArrayList();
        this.strategy = AnalysisStrategyFactory.createStrategy(method, productType);
        this.createdAt = LocalDateTime.now();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    // Thêm các phương thức getter
    public ObservableList<Message> getMessages() {
        return messages;
    }

    public String getId() {
        return id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public AnalysisMethod getAnalysisMethod() {
        return analysisMethod;
    }

    public AnalysisStrategy getStrategy() {
        return strategy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // ... các getter khác
}