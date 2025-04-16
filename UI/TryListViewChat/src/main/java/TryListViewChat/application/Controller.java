package TryListViewChat.application;

import TryListViewChat.entity.Phone;
import TryListViewChat.entity.PhoneCell;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {
    @FXML
    private ListView<Phone> phoneListView;

    private ObservableList<Phone> phoneData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Add sample phone data
        phoneData.add(new Phone("IPhone 13", 799.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
        phoneData.add(new Phone("Samsung Galaxy S21", 699.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
        phoneData.add(new Phone("Google Pixel 6", 599.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
        phoneData.add(new Phone("Oppo Ultra 10", 799.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
        phoneData.add(new Phone("Samsung A21s", 699.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
        phoneData.add(new Phone("Iphone 16", 599.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
        phoneData.add(new Phone("IPhone 15 pro max", 799.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
        phoneData.add(new Phone("Xiaomi Extreme 12", 699.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));
        phoneData.add(new Phone("Oppo Grandpix 15 pro", 599.99, "https://salt.tikicdn.com/ts/product/29/27/d2/225a0ca303b4f2f87cc8bfca2338f484.png"));

        phoneListView.setItems(phoneData);
        phoneListView.setCellFactory(param -> new PhoneCell());
    }
}
