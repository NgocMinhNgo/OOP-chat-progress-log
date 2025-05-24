package TryUIIdea01.application;

import TryUIIdea01.analysis.AnalysisResult;
import TryUIIdea01.analysis.AnalysisStrategy;
import TryUIIdea01.entity.ModelMessage;
import TryUIIdea01.entity.Phone;
import TryUIIdea01.entity.Product;
import TryUIIdea01.entity.UserMessage;
import TryUIIdea01.filter.FilterCriteria;
import TryUIIdea01.filter.PriceFilterCreator;
import TryUIIdea01.filter.TraitFilterGenerator;
import TryUIIdea01.filter.TraitMapper;
import TryUIIdea01.trait.common.*;
import TryUIIdea01.trait.techical.*;
import javafx.application.Platform;
import TryUIIdea01.trait.physical.Chargeable;
import TryUIIdea01.trait.physical.MaterialDescribed;
import TryUIIdea01.trait.physical.SizeSpecified;
import TryUIIdea01.trait.physical.Weighable;

import java.util.List;

public class ChatController {  // Thêm generic type parameter
    private final ChatArea chatArea;
    private final Conversation conversation;
    private final List<? extends Product> availableProducts;  // Sửa thành List<T>

    // Các thành phần xử lý logic
    private TraitMapper mapper;
    private TraitFilterGenerator generator;
    private AnalysisStrategy strategy;

    // Constructor sử dụng generic type
    public ChatController(ChatArea chatArea, Conversation conversation, List<? extends Product> products) {
        this.chatArea = chatArea;
        this.conversation = conversation;
        this.availableProducts = products;

        initializeComponents();
        setupEventHandlers();
        loadPreviousMessages();
    }

    private void initializeComponents() {
        // 1. Khởi tạo hệ thống filter
        initializeFilterSystem();

        // 2. Khởi tạo strategy dựa trên conversation
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

    private void registerProductTraits(){
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

    private void registerPhysicalTraits(){
        mapper.registerTrait(MaterialDescribed.class, "shellMaterial");
        mapper.registerTrait(SizeSpecified.class, "size");
        mapper.registerTrait(Weighable.class, "weight");

    }

    private void registerTechnicalTraits(){
        mapper.registerTrait(Chargeable.class, "chargingPortType");
        mapper.registerTrait(BatteryCapacitySpecified.class, "name");
        mapper.registerTrait(CPUEquipped.class, "name");
        mapper.registerTrait(GPUEquipped.class, "name");
        mapper.registerTrait(RAMEquipped.class, "name");
        mapper.registerTrait(ResolutionSpecified.class, "name");
        mapper.registerTrait(ScreenSizeSpecified.class, "name");
        mapper.registerTrait(StorageEquipped.class, "name");
        mapper.registerTrait(UsageTimeSpecified.class, "name");
    }

    private void registerTraitsByProductType() {
        registerProductTraits();
        registerPhysicalTraits();
        registerTechnicalTraits();
    }

    private void registerFilterCreators() {
        // Đăng ký các creator cố định
        generator.registerCreator(new PriceFilterCreator());
//        generator.registerCreator(new IdFilterCreator());
//        generator.registerCreator(new LinkFilterCreator());
//        generator.registerCreator(new NameFilterCreator());
//        generator.registerCreator(new BrandFilterCreator());
//        generator.registerCreator(new RatingFilterCreator());
//        generator.registerCreator(new NumberReviewFilterCreator());
//        generator.registerCreator(new DescriptionFilterCreator());
//        generator.registerCreator(new WarrantyDurationFilterCreator());
//        generator.registerCreator(new SoldQuantityFilterCreator());
//        generator.registerCreator(new OriginFilterCreator());

    }

    private void setupEventHandlers() {
        chatArea.setOnSendAction(event -> handleUserInput());
    }

    private void handleUserInput() {
        String text = chatArea.getInputText().trim();
        if (!text.isEmpty()) {
            sendUserMessage(text);
            processUserMessage(text);
        }
    }

    private void sendUserMessage(String text) {
        UserMessage message = new UserMessage(text);
        conversation.addMessage(message);
        chatArea.addMessage(message);
        chatArea.clearInput();
        chatArea.setInputDisabled(true); // Vô hiệu hóa input khi đang xử lý
    }

    private void processUserMessage(String text) {
        new Thread(() -> {
            try {
                // 1. Phân tích input
                AnalysisResult result = strategy.analyze(text);

                // 2. Tạo filter và lọc sản phẩm
                FilterCriteria criteria = generator.generateFilters(result, getProductClass());
                //List<T> filteredProducts = criteria.applyFilters(availableProducts);  // Sửa thành List<T>
                // Sửa thành List<? extends Product>
                List<? extends Product> filteredProducts = criteria.applyFilters(availableProducts);

                // 3. Tạo phản hồi
                sendModelResponse(text, filteredProducts);
            } catch (Exception e) {
                handleError(e);
            } finally {
                Platform.runLater(() -> chatArea.setInputDisabled(false));
            }
        }).start();
    }

    private void sendModelResponse(String userInput, List<? extends Product> products) {  // Sửa thành List<T>
        String responseText = buildResponseText(userInput, products);
        ModelMessage response = new ModelMessage<>(responseText, products);  // Sử dụng generic type T

        conversation.addMessage(response);
        Platform.runLater(() -> chatArea.addMessage(response));
    }

    private String buildResponseText(String userInput, List<? extends Product> products) {
        return products.isEmpty()
                ? "Không tìm thấy sản phẩm phù hợp với: " + userInput
                : "Tìm thấy " + products.size() + " sản phẩm phù hợp:";
    }

    private Class<? extends Product> getProductClass() {
        switch (conversation.getProductType()) {
            case PHONE: return Phone.class;
            //case LAPTOP: return Laptop.class;
            default: return Product.class;
        }
    }

    private void handleError(Exception e) {
        Platform.runLater(() -> {
            //chatArea.addMessage(new SystemMessage("Lỗi: " + e.getMessage()));
            chatArea.addMessage(new UserMessage("Lỗi: " + e.getMessage()));

        });
    }

    private void loadPreviousMessages() {
        conversation.getMessages().forEach(chatArea::addMessage);
    }
}