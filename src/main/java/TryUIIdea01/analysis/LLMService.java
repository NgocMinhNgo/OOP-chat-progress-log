package TryUIIdea01.analysis;

import java.io.IOException;

public interface LLMService {
    AnalysisResult analyzeText(String userInput) throws IOException;
}