package TryUIIdea01.application;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.util.Pair;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class ProductAnalysisSelectionController implements Initializable {
    @FXML private ComboBox<ProductType> productTypeCombo;
    @FXML private ComboBox<AnalysisMethod> analysisMethodCombo;
    @FXML private Button confirmButton;
    @FXML private GridPane selectionForm;
    @FXML private VBox rootContainer;

    private Consumer<Pair<ProductType, AnalysisMethod>> onSelectionConfirmed;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureComboBoxes();
        setupEventHandlers();
    }

    private void configureComboBoxes() {
        // Cấu hình ComboBox loại sản phẩm
        productTypeCombo.getItems().setAll(ProductType.values());
        productTypeCombo.setConverter(createProductTypeConverter());

        // Cấu hình ComboBox phương thức phân tích
        analysisMethodCombo.getItems().setAll(AnalysisMethod.values());
        analysisMethodCombo.setConverter(createAnalysisMethodConverter());
    }

    private StringConverter<ProductType> createProductTypeConverter() {
        return new StringConverter<>() {
            @Override
            public String toString(ProductType type) {
                return type != null ? type.getDisplayName() : "";
            }

            @Override
            public ProductType fromString(String string) {
                if (string == null || string.isEmpty()) return null;
                for (ProductType type : ProductType.values()) {
                    if (type.getDisplayName().equals(string)) {
                        return type;
                    }
                }
                return null;
            }
        };
    }

    private StringConverter<AnalysisMethod> createAnalysisMethodConverter() {
        return new StringConverter<>() {
            @Override
            public String toString(AnalysisMethod method) {
                return method != null ? method.getDisplayName() : "";
            }

            @Override
            public AnalysisMethod fromString(String string) {
                if (string == null || string.isEmpty()) return null;
                for (AnalysisMethod method : AnalysisMethod.values()) {
                    if (method.getDisplayName().equals(string)) {
                        return method;
                    }
                }
                return null;
            }
        };
    }

    private void setupEventHandlers() {
        // Ràng buộc điều kiện kích hoạt nút xác nhận
        BooleanBinding isValidSelection = Bindings.createBooleanBinding(() ->
                        productTypeCombo.getValue() != null && analysisMethodCombo.getValue() != null,
                productTypeCombo.valueProperty(), analysisMethodCombo.valueProperty()
        );
        confirmButton.disableProperty().bind(isValidSelection.not());

        // Xử lý sự kiện xác nhận
        confirmButton.setOnAction(event -> {
            if (onSelectionConfirmed != null) {
                onSelectionConfirmed.accept(
                        new Pair<>(productTypeCombo.getValue(), analysisMethodCombo.getValue())
                );
            }
        });
    }

    public void setOnSelectionConfirmed(Consumer<Pair<ProductType, AnalysisMethod>> handler) {
        this.onSelectionConfirmed = handler;
    }
}