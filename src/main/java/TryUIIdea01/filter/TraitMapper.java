package TryUIIdea01.filter;

import java.util.*;

public class TraitMapper {

    private final Map<String, Set<Class<?>>> attributeToTraitsMap = new HashMap<>();

    private final Map<Class<?>, Set<String>> traitToAttributesMap = new HashMap<>();

    public void registerTrait(Class<?> traitType, String... attributes) {
        for (String attribute : attributes) {
            if (!attributeToTraitsMap.containsKey(attribute)) {
                attributeToTraitsMap.put(attribute, new HashSet<>());
            }
            attributeToTraitsMap.get(attribute).add(traitType);
        }

        traitToAttributesMap.put(traitType, new HashSet<>(Arrays.asList(attributes)));
    }

    public Set<Class<?>> getTraitsForAttribute(String attribute) {
        if (attributeToTraitsMap.containsKey(attribute)) {
            return attributeToTraitsMap.get(attribute);
        }
        return Collections.emptySet();
    }
}