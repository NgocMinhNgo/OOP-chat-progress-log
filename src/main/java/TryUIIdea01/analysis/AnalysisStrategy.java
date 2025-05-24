package TryUIIdea01.analysis;

import java.io.IOException;

public interface AnalysisStrategy {
    AnalysisResult analyze(String userInput) throws IOException;
}
