package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        addCheck((v) -> v instanceof String && !((String) v).isEmpty());
        return this;
    }

    public StringSchema contains(String subString) {
        addCheck(value -> value == null || value instanceof String && ((String) value).contains(subString));
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck(value -> value == null || value instanceof String && ((String) value).length() >= length);
        return this;
    }
}
