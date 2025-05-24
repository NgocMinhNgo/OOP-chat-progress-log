package TryUIIdea01.filter;

public class FilterCondition {
    private String attribute;
    private String operator;
    private Object value;

    public FilterCondition(String attribute, String operator, Object value) {
        this.attribute = attribute;
        this.operator = operator;
        this.value = value;
    }

    public String getAttribute() { return attribute; }
    public String getOperator() { return operator; }
    public Object getValue() { return value; }

    @Override
    public String toString() {
        return "FilterCondition{" +
                "attribute='" + attribute + '\'' +
                ", operator='" + operator + '\'' +
                ", value=" + value +
                '}';
    }
}
