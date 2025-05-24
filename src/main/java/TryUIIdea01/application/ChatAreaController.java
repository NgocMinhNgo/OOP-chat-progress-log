package TryUIIdea01.application;

import TryUIIdea01.analysis.AnalysisResult;
import TryUIIdea01.analysis.AnalysisStrategy;

import TryUIIdea01.entity.*;
import TryUIIdea01.filter.FilterCriteria;
import TryUIIdea01.filter.PriceFilterCreator;
import TryUIIdea01.filter.TraitFilterGenerator;
import TryUIIdea01.filter.TraitMapper;
import TryUIIdea01.trait.common.*;
import TryUIIdea01.trait.techical.*;
import TryUIIdea01.trait.physical.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatAreaController implements Initializable {
    @FXML private ListView<Message> messageListView;
    @FXML private TextField inputField;
    @FXML private Button sendButton;
    @FXML private ProgressIndicator loadingIndicator;
    @FXML private ScrollPane scrollPane;
    @FXML private HBox inputBox;
    //@FXML private HBox sendBox;


    private final ObservableList<Message> messages = FXCollections.observableArrayList();
    private Conversation conversation;
    private List<? extends Product> availableProducts;

    // Các thành phần xử lý logic
    private TraitMapper mapper;
    private TraitFilterGenerator generator;
    private AnalysisStrategy strategy;

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        messageListView.setItems(messages);
//        messageListView.setCellFactory(param -> new MessageCell());
//
//        messages.addListener((ListChangeListener<Message>) c -> {
//            while (c.next()) {
//                if (c.wasAdded()) {
//                    Platform.runLater(() -> messageListView.scrollTo(c.getFrom()));
//                }
//            }
//        });
//
//
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Kiểm tra null cho tất cả thành phần
        if (messageListView == null) throw new IllegalStateException("messageListView not injected!");
        if (inputField == null) throw new IllegalStateException("inputField not injected!");
        if (sendButton == null) throw new IllegalStateException("sendButton not injected!");
        if (loadingIndicator == null) throw new IllegalStateException("loadingIndicator not injected!");
        if (scrollPane == null) throw new IllegalStateException("scrollPane not injected!");
        if (inputBox == null) throw new IllegalStateException("inputBox not injected!");

        messageListView.setItems(messages);
        messageListView.setCellFactory(param -> new MessageCell());

        messages.addListener((ListChangeListener<Message>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    Platform.runLater(() -> messageListView.scrollTo(c.getFrom()));
                }
            }
        });
    }

    public void initializeWithData(Conversation conversation, List<? extends Product> products) {
        this.conversation = conversation;
        this.availableProducts = products;

        initializeComponents();
        setupEventHandlers();
        loadPreviousMessages();
    }

    private void initializeComponents() {
        initializeFilterSystem();
        this.strategy = AnalysisStrategyFactory.createStrategy(
                conversation.getAnalysisMethod(),
                conversation.getProductType()
        );
    }

    private void initializeFilterSystem() {
        this.mapper = new TraitMapper();
        registerTraitsByProductType();
        this.generator = new TraitFilterGenerator(mapper);
        registerFilterCreators();
    }

    private void registerProductTraits() {
        mapper.registerTrait(Branded.class, "brand");
        mapper.registerTrait(DescriptionSpecified.class, "description");
        mapper.registerTrait(Identifiable.class, "id");
        mapper.registerTrait(Nameable.class, "name");
        mapper.registerTrait(Priced.class, "price");
        mapper.registerTrait(Ratable.class, "rating");
        mapper.registerTrait(ReviewCountSpecified.class, "numberReview");
        mapper.registerTrait(WarrantyDurationSpecified.class, "warrantyDuration");
        mapper.registerTrait(SoldQuantitySpecified.class, "soldQuantity");
        mapper.registerTrait(OriginSpecified.class, "origin");
    }

    private void registerPhysicalTraits() {
        mapper.registerTrait(MaterialDescribed.class, "shellMaterial");
        mapper.registerTrait(SizeSpecified.class, "size");
        mapper.registerTrait(Weighable.class, "weight");
    }

    private void registerTechnicalTraits() {
        mapper.registerTrait(Chargeable.class, "chargingPortType");
        mapper.registerTrait(BatteryCapacitySpecified.class, "batteryCapacity");
        mapper.registerTrait(CPUEquipped.class, "cpu");
        mapper.registerTrait(GPUEquipped.class, "gpu");
        mapper.registerTrait(RAMEquipped.class, "ram");
        mapper.registerTrait(ResolutionSpecified.class, "resolution");
        mapper.registerTrait(ScreenSizeSpecified.class, "screenSize");
        mapper.registerTrait(StorageEquipped.class, "storage");
        mapper.registerTrait(UsageTimeSpecified.class, "usageTime");
    }

    private void registerTraitsByProductType() {
        registerProductTraits();
        registerPhysicalTraits();
        registerTechnicalTraits();
    }

    private void registerFilterCreators() {
        generator.registerCreator(new PriceFilterCreator());
        // Các filter creator khác có thể thêm ở đây
    }

    private void setupEventHandlers() {
        setOnSendAction(this::handleUserInput);
    }

    private void handleUserInput(ActionEvent event) {
        String text = getInputText().trim();
        if (!text.isEmpty()) {
            sendUserMessage(text);
            processUserMessage(text);
        }
    }

    private void sendUserMessage(String text) {
        UserMessage message = new UserMessage(text);
        conversation.addMessage(message);
        addMessage(message);
        clearInput();
        setInputDisabled(true);
    }

    private void processUserMessage(String text) {
        new Thread(() -> {
            try {
                AnalysisResult result = strategy.analyze(text);
                FilterCriteria criteria = generator.generateFilters(result, getProductClass());
                List<? extends Product> filteredProducts = criteria.applyFilters(availableProducts);

                sendModelResponse(text, filteredProducts);
            } catch (Exception e) {
                handleError(e);
            } finally {
                Platform.runLater(() -> setInputDisabled(false));
            }
        }).start();
    }

    private void sendModelResponse(String userInput, List<? extends Product> products) {
        String responseText = buildResponseText(userInput, products);
        ModelMessage response = new ModelMessage(responseText, products);

        conversation.addMessage(response);
        Platform.runLater(() -> addMessage(response));
    }

    private String buildResponseText(String userInput, List<? extends Product> products) {
        return products.isEmpty()
                ? "Không tìm thấy sản phẩm phù hợp với: " + userInput
                : "Tìm thấy " + products.size() + " sản phẩm phù hợp:";
    }

    private Class<? extends Product> getProductClass() {
        switch (conversation.getProductType()) {
            case PHONE: return Phone.class;
            // Các loại sản phẩm khác có thể thêm ở đây
            default: return Product.class;
        }
    }

    private void handleError(Exception e) {
        Platform.runLater(() -> {
            addMessage(new UserMessage("Lỗi: " + e.getMessage()));
        });
    }

    private void loadPreviousMessages() {
        conversation.getMessages().forEach(this::addMessage);
    }

    // ========== Public Methods ==========
    public void addMessage(Message message) {
        messages.add(message);
    }

    public void clearMessages() {
        messages.clear();
    }

    public String getInputText() {
        return inputField.getText();
    }

    public void clearInput() {
        inputField.clear();
    }

    public void setOnSendAction(EventHandler<ActionEvent> handler) {
        sendButton.setOnAction(handler);
        inputField.setOnAction(handler);
    }

    public void setInputDisabled(boolean disabled) {
        inputField.setDisable(disabled);
        sendButton.setDisable(disabled);
    }

    public void showLoading(boolean show) {
        loadingIndicator.setVisible(show);
    }
}