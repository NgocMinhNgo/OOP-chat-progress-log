package TryListViewChat.entity;

import javafx.scene.control.ListCell;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import javax.swing.text.html.ImageView;

public class PhoneCell extends ListCell<Phone> {
    private VBox vbox = new VBox();
    private Label nameLabel = new Label();
    private Label priceLabel = new Label();
    //
    //private ImageView imageView = new ImageView(new Image(Phone.getImageUrl()));

    public PhoneCell() {
        super();
        vbox.getChildren().addAll(nameLabel, priceLabel);
        vbox.setStyle("-fx-border-color: #ccc; -fx-border-width: 1px; -fx-padding: 10px; -fx-margin: 5px;");
    }

    @Override
    protected void updateItem(Phone phone, boolean empty) {
        super.updateItem(phone, empty);

        if (empty || phone == null) {
            setGraphic(null);
        } else {
            nameLabel.setText("Phone: " + phone.getName());
            priceLabel.setText(String.format("Price: $%.2f", phone.getPrice()));
            setGraphic(vbox);
        }
    }
}