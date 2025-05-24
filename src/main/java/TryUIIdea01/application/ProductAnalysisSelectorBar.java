package TryUIIdea01.application;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class ProductAnalysisSelectorBar extends HBox {
    private final ComboBox<ProductType> productSelector;
    private final ComboBox<AnalysisMethod> analysisMethodSelector;
    private final Button confirmButton;

    public ProductAnalysisSelectorBar() {
        // 1. Product Type Selector
        productSelector = new ComboBox<>();
        productSelector.getItems().setAll(ProductType.values());
        productSelector.setPromptText("Select product type");

        // 2. Analysis Method Selector
        analysisMethodSelector = new ComboBox<>();
        analysisMethodSelector.getItems().setAll(AnalysisMethod.values());
        analysisMethodSelector.setPromptText("Select analysis method");

        // 3. Confirm Button
        confirmButton = new Button("Start TryUIIdea01.application.Conversation");
        confirmButton.setDisable(true);

        // Logic enable/disable confirm button
        BooleanBinding isValidSelection = Bindings.createBooleanBinding(() ->
                        productSelector.getValue() != null && analysisMethodSelector.getValue() != null,
                productSelector.valueProperty(), analysisMethodSelector.valueProperty()
        );
        confirmButton.disableProperty().bind(isValidSelection.not());

        this.getChildren().addAll(productSelector, analysisMethodSelector, confirmButton);
        this.setSpacing(10);
        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color: #f0f0f0;");
    }

    // Getter methods
    public ProductType getSelectedProductType() {
        return productSelector.getValue();
    }

    public AnalysisMethod getSelectedAnalysisMethod() {
        return analysisMethodSelector.getValue();
    }

    public Button getConfirmButton() {
        return confirmButton;
    }
}