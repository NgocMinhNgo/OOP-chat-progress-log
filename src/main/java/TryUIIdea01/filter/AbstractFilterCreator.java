package TryUIIdea01.filter;

public abstract class AbstractFilterCreator<T> implements FilterCreator {
    private final String attribute;
    private final Class<T> traitType;

    protected AbstractFilterCreator(String attribute, Class<T> traitType) {
        this.attribute = attribute;
        this.traitType = traitType;
    }

    @Override
    public boolean canCreate(String attribute, Class<?> traitType) {
        return this.attribute.equals(attribute) && this.traitType.isAssignableFrom(traitType);
    }
}