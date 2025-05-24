package TryUIIdea01.analysis;

import TryUIIdea01.application.ProductType;

import java.io.IOException;

public class LLMAnalysisStrategy implements AnalysisStrategy {
    private LLMService llmService;
    private ProductType productType;

    public LLMAnalysisStrategy(LLMService llmService, ProductType productType) {
        this.llmService = llmService;
        this.productType = productType;
    }

    public void setLLMService(LLMService llmService) {
        this.llmService = llmService;
    }

    @Override
    public AnalysisResult analyze(String userInput) throws IOException {
        AnalysisResult result = llmService.analyzeText(userInput);
        return result;
    }
}