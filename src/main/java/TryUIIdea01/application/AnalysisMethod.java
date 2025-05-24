package TryUIIdea01.application;

public enum AnalysisMethod {
    LLM("AI Analysis (LLM)"),
    TRADITIONAL("Traditional Rule-based"),
    HYBRID("Hybrid Approach");

    private final String displayName;

    AnalysisMethod(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
    public String getDisplayName() {
        return this.displayName;
    }
}