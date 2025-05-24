package TryUIIdea01.filter;

public interface FilterCreator {
    boolean canCreate(String attribute, Class<?> traitType);
    Filter<?> create(FilterCondition condition);
}
