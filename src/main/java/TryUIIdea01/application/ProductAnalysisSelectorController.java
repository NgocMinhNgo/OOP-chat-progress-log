package TryUIIdea01.application;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductAnalysisSelectorController implements Initializable {
    @FXML private ComboBox<ProductType> productSelector;
    @FXML private ComboBox<AnalysisMethod> analysisMethodSelector;
    @FXML private Button confirmButton;
    @FXML private HBox rootContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize combo boxes
        productSelector.getItems().setAll(ProductType.values());
        analysisMethodSelector.getItems().setAll(AnalysisMethod.values());

        // Setup button enable/disable logic
        BooleanBinding isValidSelection = Bindings.createBooleanBinding(() ->
                        productSelector.getValue() != null && analysisMethodSelector.getValue() != null,
                productSelector.valueProperty(), analysisMethodSelector.valueProperty()
        );
        confirmButton.disableProperty().bind(isValidSelection.not());
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