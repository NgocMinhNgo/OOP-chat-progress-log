package TryListViewChatCopy.entity;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

public class ProductCell extends ListCell<Product> {
    private VBox vbox = new VBox();
    private Label nameLabel = new Label();
    private Label priceLabel = new Label();
    private Label brandLabel = new Label();
    //
    //private ImageView imageView = new ImageView(new Image(Phone.getImageUrl()));

    public ProductCell() {
        super();
        vbox.getChildren().addAll(nameLabel, priceLabel, brandLabel);
        // them khaong cach
        vbox.setSpacing(5);
        //vbox.setStyle("-fx-border-color: #ccc; -fx-border-width: 2px; -fx-padding: 20px; -fx-margin: 10px;");
        vbox.setStyle("-fx-border-color: #ccc; -fx-border-width: 2px; -fx-padding: 10px;");
        // them chieu cao cho ohu hop
        setPrefHeight(80);
    }

    @Override
    protected void updateItem(Product product, boolean empty) {
        super.updateItem(product, empty);

        if (empty || product == null) {
            setGraphic(null);
        } else {
            System.out.println("DEBUG - Brand: " + product.getBrand());
            nameLabel.setText("Phone: " + product.getName());
            priceLabel.setText(String.format("Price: $%.2f", product.getPrice()));
            brandLabel.setText("Brand: " + product.getBrand());
            setGraphic(vbox);
        }
    }
}