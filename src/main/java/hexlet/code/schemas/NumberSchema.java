package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        addCheck((v) -> v instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        addCheck(value -> value == null || value instanceof Integer && (Integer) value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCheck(value ->
                value == null || value instanceof Integer && (Integer) value >= min && (Integer) value <= max);
        return this;
    }
}
