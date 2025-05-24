package TryUIIdea01.application;

import TryUIIdea01.entity.Phone;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;


import java.util.ArrayList;
import java.util.List;

public class MainAppController {
    private final BorderPane root;
    private final ConversationSidebar sidebar;
    private final StackPane contentArea;
    private final ObservableList<Conversation> conversations;

    private Conversation currentConversation;

    public MainAppController() {
        // 1. Khởi tạo các thành phần UI chính
        root = new BorderPane();
        conversations = FXCollections.observableArrayList();
        sidebar = new ConversationSidebar(conversations, this::handleConversationSelection);
        contentArea = new StackPane();

        // 2. Thiết lập layout
        root.setLeft(sidebar);
        root.setCenter(contentArea);

        // 3. Load dữ liệu ban đầu
        loadInitialData();
    }

    private void loadInitialData() {
        // Load conversations từ storage
        List<Conversation> savedConversations = ConversationStorage.loadAllConversations();
        conversations.addAll(savedConversations);

        // Hiển thị màn hình welcome ban đầu
        showWelcomeView();
    }

    private void showWelcomeView() {
        // 1. Tạo layout chính
        VBox welcomePane = new VBox(20);
        welcomePane.setAlignment(Pos.CENTER);
        welcomePane.setPadding(new Insets(40));
        welcomePane.setStyle("-fx-background-color: #f8f9fa;");

        // 2. Thêm tiêu đề
        Label titleLabel = new Label("Product Advisor Chat");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // 3. Thêm mô tả
        Label descLabel = new Label("Chọn sản phẩm bạn muốn được tư vấn hoặc tiếp tục cuộc trò chuyện trước đó");
        descLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #7f8c8d;");
        descLabel.setWrapText(true);
        descLabel.setMaxWidth(400);
        descLabel.setTextAlignment(TextAlignment.CENTER);

        // 4. Nút bắt đầu
        Button startButton = new Button("Bắt đầu trò chuyện mới");
        startButton.setStyle("-fx-background-color: #4a90e2; -fx-text-fill: white; -fx-font-size: 14px;");
        startButton.setOnAction(e -> showProductSelectionView());

        // 5. Thêm hình ảnh (tuỳ chọn)
//        ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/images/welcome.png")));
//        logo.setFitWidth(200);
//        logo.setPreserveRatio(true);

        // 6. Thêm các thành phần vào layout
        //welcomePane.getChildren().addAll(logo, titleLabel, descLabel, startButton);
        welcomePane.getChildren().addAll(titleLabel, descLabel, startButton);


        // 7. Hiển thị lên content area
        contentArea.getChildren().setAll(welcomePane);
    }

    private void handleConversationSelection(Conversation conversation) {
        if (conversation == null) {
            // Tạo conversation mới
            showProductSelectionView();
        } else {
            // Tải conversation đã có
            loadExistingConversation(conversation);
        }
    }

    private void showProductSelectionView() {
        ProductAnalysisSelectionView selectionView = new ProductAnalysisSelectionView();
//        selectionView.setOnSelectionConfirmed(this::createNewConversation);
        // Sửa chỗ này: nhận Pair rồi gọi lại đúng hàm
        selectionView.setOnSelectionConfirmed(pair ->
                createNewConversation(pair.getKey(), pair.getValue()));
        contentArea.getChildren().setAll(selectionView);
    }


    private void createNewConversation(ProductType productType, AnalysisMethod method) {
        currentConversation = new Conversation(productType, method);
        conversations.add(currentConversation);
        initializeNewConversation(currentConversation);
    }

    private void initializeNewConversation(Conversation conversation) {
        // Hiển thị loading screen
        contentArea.getChildren().setAll(new LoadingPane());

        new Thread(() -> {
            // 1. Scrape data
            //List<Product> products = scrapeProducts(conversation.getProductType());
            //List<Product> products = scrapeProducts(conversation.getProductType());
            // giả vờ fake data tạm đã =))))
            ArrayList<Phone> products = createSamplePhones();

            // 2. Khởi tạo chat area
            Platform.runLater(() -> {
                ChatArea chatArea = new ChatArea();
                ChatController chatController = new ChatController(chatArea, conversation, products);
                contentArea.getChildren().setAll(chatArea);
            });
        }).start();
    }

    private void loadExistingConversation(Conversation conversation) {
        currentConversation = conversation;
        ChatArea chatArea = new ChatArea();
        // Cai nay trien khai sau giờ fake data đã =))))
        //List<Product> products = DataStorage.loadProducts(conversation.getProductType());
        ArrayList<Phone> products = createSamplePhones();
        new ChatController(chatArea, conversation, products);
        contentArea.getChildren().setAll(chatArea);
    }

    public BorderPane getView() {
        return root;
    }

    public static ArrayList<Phone> createSamplePhones() {
        ArrayList<Phone> phones = new ArrayList<>();

        // entity.Phone 1
        Phone phone1 = new Phone();
        phone1.setId("P001");
        phone1.setLink("https://example.com/phone1");
        phone1.setName("iPhone 15 Pro Max");
        phone1.setPrice(29990000);
        phone1.setBrand("Apple");
        phone1.setPrice(27990000);
        //phone1.setDiscountPercent(6.67);
        phone1.setSoldQuantity(1500);
        phone1.setBatteryCapacity(4422);
        phone1.setFrontCamera("12MP");
        phone1.setBackCamera("48MP + 12MP + 12MP");
        phone1.setGpu("Apple A17 Pro");
        phone1.setOrigin("China");
        phone1.setChargingPort("USB-C");
        phone1.setRam(8);
        phone1.setResolution("2796 x 1290");
        phone1.setRom(256);
        phone1.setScreenSize(6.7);
        phone1.setWarrantyDuration("12 months");
        phone1.setRating(4.8);
        phone1.setNumberReview(1250);
        phone1.setDescription("Flagship smartphone from Apple with A17 Pro chip");
        phone1.setComments(new ArrayList<>());

        // entity.Phone 2
        Phone phone2 = new Phone();
        phone2.setId("P002");
        phone2.setLink("https://example.com/phone2");
        phone2.setName("Samsung Galaxy S23 Ultra");
        phone2.setPrice(24990000);
        phone2.setBrand("Samsung");
        phone2.setPrice(22990000);
        //phone2.setDiscountPercent(8.0);
        phone2.setSoldQuantity(1800);
        phone2.setBatteryCapacity(5000);
        phone2.setFrontCamera("12MP");
        phone2.setBackCamera("200MP + 12MP + 10MP + 10MP");
        phone2.setGpu("Adreno 740");
        phone2.setOrigin("Vietnam");
        phone2.setChargingPort("USB-C");
        phone2.setRam(12);
        phone2.setResolution("3088 x 1440");
        phone2.setRom(256);
        phone2.setScreenSize(6.8);
        phone2.setWarrantyDuration("12 months");
        phone2.setRating(4.7);
        phone2.setNumberReview(980);
        phone2.setDescription("Samsung flagship with 200MP camera");
        phone2.setComments(new ArrayList<>());

        // entity.Phone 3
        Phone phone3 = new Phone();
        phone3.setId("P003");
        phone3.setLink("https://example.com/phone3");
        phone3.setName("Xiaomi 13 Pro");
        phone3.setPrice(19990000);
        phone3.setBrand("Xiaomi");
        phone3.setPrice(17990000);
        //phone3.setDiscountPercent(10.0);
        phone3.setSoldQuantity(1200);
        phone3.setBatteryCapacity(4820);
        phone3.setFrontCamera("32MP");
        phone3.setBackCamera("50MP + 50MP + 50MP");
        phone3.setGpu("Adreno 730");
        phone3.setOrigin("China");
        phone3.setChargingPort("USB-C");
        phone3.setRam(8);
        phone3.setResolution("3200 x 1440");
        phone3.setRom(256);
        phone3.setScreenSize(6.73);
        phone3.setWarrantyDuration("12 months");
        phone3.setRating(4.6);
        phone3.setNumberReview(750);
        phone3.setDescription("Xiaomi flagship with Leica cameras");
        phone3.setComments(new ArrayList<>());

        // entity.Phone 4
        Phone phone4 = new Phone();
        phone4.setId("P004");
        phone4.setLink("https://example.com/phone4");
        phone4.setName("OPPO Find X5 Pro");
        phone4.setPrice(18990000);
        phone4.setBrand("OPPO");
        phone4.setPrice(16990000);
        //phone4.setDiscountPercent(10.53);
        phone4.setSoldQuantity(850);
        phone4.setBatteryCapacity(5000);
        phone4.setFrontCamera("32MP");
        phone4.setBackCamera("50MP + 50MP + 13MP");
        phone4.setGpu("Adreno 730");
        phone4.setOrigin("China");
        phone4.setChargingPort("USB-C");
        phone4.setRam(12);
        phone4.setResolution("3216 x 1440");
        phone4.setRom(256);
        phone4.setScreenSize(6.7);
        phone4.setWarrantyDuration("12 months");
        phone4.setRating(4.5);
        phone4.setNumberReview(620);
        phone4.setDescription("OPPO flagship with Hasselblad cameras");
        phone4.setComments(new ArrayList<>());

        // entity.Phone 5
        Phone phone5 = new Phone();
        phone5.setId("P005");
        phone5.setLink("https://example.com/phone5");
        phone5.setName("Vivo X80 Pro");
        phone5.setPrice(17990000);
        phone5.setBrand("Vivo");
        phone5.setPrice(15990000);
        //phone5.setDiscountPercent(11.12);
        phone5.setSoldQuantity(700);
        phone5.setBatteryCapacity(4700);
        phone5.setFrontCamera("32MP");
        phone5.setBackCamera("50MP + 48MP + 12MP + 8MP");
        phone5.setGpu("Adreno 730");
        phone5.setOrigin("China");
        phone5.setChargingPort("USB-C");
        phone5.setRam(12);
        phone5.setResolution("3200 x 1440");
        phone5.setRom(256);
        phone5.setScreenSize(6.78);
        phone5.setWarrantyDuration("12 months");
        phone5.setRating(4.4);
        phone5.setNumberReview(580);
        phone5.setDescription("Vivo flagship with Zeiss optics");
        phone5.setComments(new ArrayList<>());

        // entity.Phone 6
        Phone phone6 = new Phone();
        phone6.setId("P006");
        phone6.setLink("https://example.com/phone6");
        phone6.setName("Realme GT 2 Pro");
        phone6.setPrice(14990000);
        phone6.setBrand("Realme");
        phone6.setPrice(12990000);
        //phone6.setDiscountPercent(13.34);
        phone6.setSoldQuantity(950);
        phone6.setBatteryCapacity(5000);
        phone6.setFrontCamera("32MP");
        phone6.setBackCamera("50MP + 50MP + 2MP");
        phone6.setGpu("Adreno 730");
        phone6.setOrigin("China");
        phone6.setChargingPort("USB-C");
        phone6.setRam(12);
        phone6.setResolution("3216 x 1440");
        phone6.setRom(256);
        phone6.setScreenSize(6.7);
        phone6.setWarrantyDuration("12 months");
        phone6.setRating(4.3);
        phone6.setNumberReview(520);
        phone6.setDescription("Realme flagship with 2K AMOLED display");
        phone6.setComments(new ArrayList<>());

        // entity.Phone 7
        Phone phone7 = new Phone();
        phone7.setId("P007");
        phone7.setLink("https://example.com/phone7");
        phone7.setName("OnePlus 11");
        phone7.setPrice(16990000);
        phone7.setBrand("OnePlus");
        phone7.setPrice(14990000);
        //phone7.setDiscountPercent(11.77);
        phone7.setSoldQuantity(1100);
        phone7.setBatteryCapacity(5000);
        phone7.setFrontCamera("16MP");
        phone7.setBackCamera("50MP + 48MP + 32MP");
        phone7.setGpu("Adreno 740");
        phone7.setOrigin("China");
        phone7.setChargingPort("USB-C");
        phone7.setRam(16);
        phone7.setResolution("3216 x 1440");
        phone7.setRom(256);
        phone7.setScreenSize(6.7);
        phone7.setWarrantyDuration("12 months");
        phone7.setRating(4.6);
        phone7.setNumberReview(890);
        phone7.setDescription("OnePlus flagship with Hasselblad camera");
        phone7.setComments(new ArrayList<>());

        // entity.Phone 8
        Phone phone8 = new Phone();
        phone8.setId("P008");
        phone8.setLink("https://example.com/phone8");
        phone8.setName("Google Pixel 7 Pro");
        phone8.setPrice(19990000);
        phone8.setBrand("Google");
        phone8.setPrice(18990000);
        //phone8.setDiscountPercent(5.0);
        phone8.setSoldQuantity(650);
        phone8.setBatteryCapacity(5000);
        phone8.setFrontCamera("10.8MP");
        phone8.setBackCamera("50MP + 12MP + 48MP");
        phone8.setGpu("Google Tensor G2");
        phone8.setOrigin("USA");
        phone8.setChargingPort("USB-C");
        phone8.setRam(12);
        phone8.setResolution("3120 x 1440");
        phone8.setRom(128);
        phone8.setScreenSize(6.7);
        phone8.setWarrantyDuration("12 months");
        phone8.setRating(4.7);
        phone8.setNumberReview(720);
        phone8.setDescription("Google flagship with best-in-class camera software");
        phone8.setComments(new ArrayList<>());

        // entity.Phone 9
        Phone phone9 = new Phone();
        phone9.setId("P009");
        phone9.setLink("https://example.com/phone9");
        phone9.setName("ASUS ROG entity.Phone 7");
        phone9.setPrice(21990000);
        phone9.setBrand("ASUS");
        phone9.setPrice(19990000);
        //phone9.setDiscountPercent(9.1);
        phone9.setSoldQuantity(450);
        phone9.setBatteryCapacity(6000);
        phone9.setFrontCamera("32MP");
        phone9.setBackCamera("50MP + 13MP + 5MP");
        phone9.setGpu("Adreno 740");
        phone9.setOrigin("Taiwan");
        phone9.setChargingPort("USB-C");
        phone9.setRam(16);
        phone9.setResolution("2448 x 1080");
        phone9.setRom(512);
        phone9.setScreenSize(6.78);
        phone9.setWarrantyDuration("12 months");
        phone9.setRating(4.5);
        phone9.setNumberReview(380);
        phone9.setDescription("Gaming smartphone with high refresh rate display");
        phone9.setComments(new ArrayList<>());

        // entity.Phone 10
        Phone phone10 = new Phone();
        phone10.setId("P010");
        phone10.setLink("https://example.com/phone10");
        phone10.setName("Nokia X30 5G");
        phone10.setPrice(11990000);
        phone10.setBrand("Nokia");
        phone10.setPrice(9990000);
        //phone10.setDiscountPercent(16.68);
        phone10.setSoldQuantity(350);
        phone10.setBatteryCapacity(4200);
        phone10.setFrontCamera("16MP");
        phone10.setBackCamera("50MP + 13MP");
        phone10.setGpu("Adreno 642");
        phone10.setOrigin("Finland");
        phone10.setChargingPort("USB-C");
        phone10.setRam(8);
        phone10.setResolution("2400 x 1080");
        phone10.setRom(256);
        phone10.setScreenSize(6.43);
        phone10.setWarrantyDuration("24 months");
        phone10.setRating(4.2);
        phone10.setNumberReview(210);
        phone10.setDescription("Sustainable smartphone with 3 years of updates");
        phone10.setComments(new ArrayList<>());

        phones.add(phone1);
        phones.add(phone2);
        phones.add(phone3);
        phones.add(phone4);
        phones.add(phone5);
        phones.add(phone6);
        phones.add(phone7);
        phones.add(phone8);
        phones.add(phone9);
        phones.add(phone10);

        return phones;
    }
}