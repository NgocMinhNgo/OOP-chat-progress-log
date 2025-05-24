package TryUIIdea01.filter;


import TryUIIdea01.trait.common.Priced;

import java.util.ArrayList;

// Price Filter (áp dụng cho mọi Priced device)
public class PriceFilterCreator extends AbstractFilterCreator<Priced> {
    public PriceFilterCreator() {
        super("price", Priced.class);
    }

    @Override
    public Filter<Priced> create(FilterCondition condition) {
        double value = ((Number) condition.getValue()).doubleValue();
        switch (condition.getOperator()) {
            case "<=": return item -> item.getPrice() <= value;
            case ">=": return item -> item.getPrice() >= value;
            case "between":
                ArrayList<?> range = (ArrayList<?>) condition.getValue();
                double min = ((Number) range.get(0)).doubleValue();
                double max = ((Number) range.get(1)).doubleValue();
                return item -> item.getPrice() >= min && item.getPrice() <= max;
            default: return null;
        }
    }
}