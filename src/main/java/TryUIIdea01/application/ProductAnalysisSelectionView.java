package TryUIIdea01.application;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.util.Pair;

import java.util.function.Consumer;

public class ProductAnalysisSelectionView extends VBox {
    private final ComboBox<ProductType> productTypeCombo = new ComboBox<>();
    private final ComboBox<AnalysisMethod> analysisMethodCombo = new ComboBox<>();
    private final Button confirmButton = new Button("Confirm");
    private Consumer<Pair<ProductType, AnalysisMethod>> onSelectionConfirmed;

    public ProductAnalysisSelectionView() {
        initializeUI();
    }

    private void initializeUI() {
        // Thiết lập layout chính
        configureMainLayout();

        // Tạo và thêm các thành phần
        getChildren().addAll(
                createTitleLabel(),
                createSelectionForm(),
                confirmButton
        );

        // Thiết lập sự kiện
        setupEventHandlers();
    }

    private void configureMainLayout() {
        setSpacing(20);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(30));
        setStyle("-fx-background-color: #f8f9fa;");

        // Cấu hình nút xác nhận
        confirmButton.setStyle("-fx-background-color: #4a90e2; -fx-text-fill: white;");
        confirmButton.setDisable(true);
    }

    private Label createTitleLabel() {
        Label titleLabel = new Label("Chọn loại sản phẩm và phương thức phân tích");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        return titleLabel;
    }

    private GridPane createSelectionForm() {
        GridPane form = new GridPane();
        form.setHgap(15);
        form.setVgap(15);
        form.setAlignment(Pos.CENTER);

        configureComboBoxes();

        form.addRow(0, new Label("Loại sản phẩm:"), productTypeCombo);
        form.addRow(1, new Label("Phương thức phân tích:"), analysisMethodCombo);

        return form;
    }

    private void configureComboBoxes() {
        // Cấu hình ComboBox loại sản phẩm
        productTypeCombo.getItems().setAll(ProductType.values());
        productTypeCombo.setPromptText("Choose Type of Product");
        productTypeCombo.setConverter(createProductTypeConverter());

        // Cấu hình ComboBox phương thức phân tích
        analysisMethodCombo.getItems().setAll(AnalysisMethod.values());
        analysisMethodCombo.setPromptText("Chọn phương thức");
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