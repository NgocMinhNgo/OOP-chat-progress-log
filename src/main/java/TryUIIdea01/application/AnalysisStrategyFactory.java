package TryUIIdea01.application;

import TryUIIdea01.analysis.AnalysisStrategy;
import TryUIIdea01.analysis.GeminiService;
import TryUIIdea01.analysis.LLMAnalysisStrategy;
import TryUIIdea01.analysis.LLMService;

public class AnalysisStrategyFactory {
    public static AnalysisStrategy createStrategy(AnalysisMethod method, ProductType productType) {
        switch (method) {
            case LLM:
                LLMService llmService = new GeminiService(); // Hoặc service khác
                return new LLMAnalysisStrategy(llmService, productType); // MỘT VAN DE LONNNNNN
            // BOI VI CAI LLMAnalysis nay no ch customize prompt

            case TRADITIONAL:
                //return new TraditionalAnalysisStrategy(productType);

            case HYBRID:
                //return new HybridAnalysisStrategy(productType);

            default:
                throw new IllegalArgumentException("Unknown analysis method");
        }
    }
}