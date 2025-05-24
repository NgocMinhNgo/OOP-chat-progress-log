package TryUIIdea01.filter;

public interface Filter<T> {
    boolean apply(T item);
}