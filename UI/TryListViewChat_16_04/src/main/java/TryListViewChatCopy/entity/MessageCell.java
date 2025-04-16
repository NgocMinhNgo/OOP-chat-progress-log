package TryListViewChatCopy.entity;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;

public class MessageCell extends ListCell<Message> {
    private final VBox userMessageBox = new VBox();
    private final Label userMessageLabel = new Label();

    private final VBox modelMessageBox = new VBox();
    private final Label modelMessageLabel = new Label();
    private final VBox productsContainer = new VBox();

    public MessageCell() {
        super();

        // Styling cho user message
        userMessageBox.getChildren().add(userMessageLabel);
        userMessageBox.setStyle("-fx-background-color: #e3f2fd; -fx-padding: 10px; -fx-background-radius: 10px;");

        // Styling cho model message
        modelMessageBox.setSpacing(10);
        modelMessageBox.getChildren().addAll(modelMessageLabel, productsContainer);
        modelMessageBox.setStyle("-fx-background-color: #f5f5f5; -fx-padding: 10px; -fx-background-radius: 10px;");

        // Styling chung
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        setPrefWidth(0); // Tự động co giãn theo ListView
    }

    @Override
    protected void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);

        if (empty || message == null) {
            setGraphic(null);
        } else {
            if (message instanceof UserMessage) {
                // Hiển thị user message đơn giản
                userMessageLabel.setText(message.getTextContent());
                setGraphic(userMessageBox);
            } else if (message instanceof ModelMessage) {
                // Hiển thị model message với sản phẩm đề xuất
                ModelMessage modelMessage = (ModelMessage) message;
                modelMessageLabel.setText(modelMessage.getTextContent());

                // Xóa các sản phẩm cũ
                productsContainer.getChildren().clear();

                // Thêm các sản phẩm mới
                for (Product product : modelMessage.getRecommendedProducts()) {
                    VBox productBox = createProductBox(product);
                    productsContainer.getChildren().add(productBox);
                }

                setGraphic(modelMessageBox);
            }
        }
    }

    private VBox createProductBox(Product product) {
        VBox box = new VBox(5);
        Label nameLabel = new Label(product.getName());
        Label priceLabel = new Label(String.format("$%.2f", product.getPrice()));

        // Styling cho product box
        box.getChildren().addAll(nameLabel, priceLabel);
        box.setStyle("-fx-background-color: #ffffff; -fx-padding: 8px; -fx-border-color: #ddd; -fx-border-width: 1px; -fx-border-radius: 5px;");

        // Thêm sự kiện click
        box.setOnMouseClicked(e -> {
            System.out.println("Selected product: " + product.getName());
            // Có thể mở dialog chi tiết sản phẩm ở đây
            showProductDetail(product);
        });

        return box;
    }

    private void showProductDetail(Product product) {
//        // Triển khai hiển thị chi tiết sản phẩm
//        Stage dialog = new Stage();
//        VBox dialogVbox = new VBox(10);
//        dialogVbox.getChildren().addAll(
//                new Label("Product Details"),
//                new Label("Name: " + product.getName()),
//                new Label("Price: $" + product.getPrice()),
//                new Label("Brand: " + product.getBrand())
//        );
//
//        Scene dialogScene = new Scene(dialogVbox, 300, 200);
//        dialog.setScene(dialogScene);
//        dialog.show();
//    }
        System.out.println("Showing product detail: ");
    }
}