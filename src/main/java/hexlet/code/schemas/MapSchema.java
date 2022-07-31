package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        addCheck((v) -> v instanceof Map);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck((v) -> ((Map) v).size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemaMap) {
        addCheck(value -> {
            for (Map.Entry<String, BaseSchema> entry : schemaMap.entrySet()) {
                if (!((Map) value).containsKey(entry.getKey())) {
                    continue;
                }
                if (!entry.getValue().isValid(((Map) value).get(entry.getKey()))) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
