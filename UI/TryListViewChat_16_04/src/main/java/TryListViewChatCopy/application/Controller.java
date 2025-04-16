package TryListViewChatCopy.application;

import TryListViewChatCopy.entity.*;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private ListView<Message> messageListView;

    //private ObservableList<Phone> phoneData = FXCollections.observableArrayList();
    //private ObservableList<Product> productData = FXCollections.observableArrayList();
    private ObservableList<Message> messageData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Add sample phone data
//        productData.add(new Product("IPhone 13", 799.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
//        productData.add(new Product("Samsung Galaxy S21", 699.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
//        productData.add(new Product("Google Pixel 6", 599.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
//        productData.add(new Product("Oppo Ultra 10", 799.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
//        productData.add(new Product("Samsung A21s", 699.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
//        productData.add(new Product("Iphone 16", 599.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
//        productData.add(new Product("IPhone 15 pro max", 799.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
//        productData.add(new Product("Xiaomi Extreme 12", 699.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
//        productData.add(new Product("Oppo Grandpix 15 pro", 599.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));

        // Add sample phone data
        List<Product> demoList = new ArrayList<>();
        demoList.add(new Product("Laptop", 1500.0, "Dell"));
        demoList.add(new Product("Smartphone", 800.0, "Samsung"));
        demoList.add(new Product("Phone", 100.0, "Sony"));
        demoList.add(new Product("Headphones", 100.0, "Sony"));
        demoList.add(new Product("Bass", 100.0, "Sony"));

        messageData.add(new UserMessage("I want Apple Phones"));
        messageData.add(new ModelMessage("Here are the Phones for you:", demoList));
        messageData.add(new UserMessage("I want Samsung Phones"));
        messageData.add(new ModelMessage("Here are the Phones for you:", demoList));
        messageData.add(new UserMessage("I want Oppo Phones"));
        messageData.add(new ModelMessage("Here are the Phones for you:", demoList));

        messageListView.setItems(messageData);
        messageListView.setCellFactory(param -> new MessageCell());
    }
}
