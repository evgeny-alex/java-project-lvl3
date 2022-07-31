package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class SchemaTest {

    @Test
    public void stringSchemaTest() {
        Validator v = new Validator();

        StringSchema schema = v.string();

        Assertions.assertTrue(schema.isValid(""));
        Assertions.assertTrue(schema.isValid(null));

        schema = v.string();
        schema.required();

        Assertions.assertTrue(schema.isValid("hexlet")); // true
        Assertions.assertFalse(schema.isValid(null)); // false

        Assertions.assertTrue(schema.contains("wh").isValid("what does the fox say")); // true
        Assertions.assertFalse(schema.contains("whatthe").isValid("what does the fox say")); // false

        schema = v.string();
        schema.contains("hello");
        schema.minLength(8);

        Assertions.assertTrue(schema.isValid("hello world!"));
        Assertions.assertFalse(schema.isValid("hello w"));
    }

    @Test
    public void numberSchemaTest() {
        Validator v = new Validator();

        NumberSchema schema = v.number();

        Assertions.assertTrue(schema.isValid(1));
        schema.isValid(null); // true

        schema.required();

        Assertions.assertFalse(schema.isValid(null)); // false
        Assertions.assertTrue(schema.isValid(10)); // true
        Assertions.assertFalse(schema.isValid("5")); // false

        Assertions.assertTrue(schema.positive().isValid(10)); // true
        Assertions.assertFalse(schema.isValid(-10)); // false

        schema.range(5, 10);

        Assertions.assertTrue(schema.isValid(5)); // true
        Assertions.assertTrue(schema.isValid(10)); // true
        Assertions.assertFalse(schema.isValid(4)); // false
        Assertions.assertFalse(schema.isValid(11)); // false

    }

    @Test
    public void mapSchemaTest() {
        Validator v = new Validator();

        MapSchema schema = v.map();

        Assertions.assertTrue(schema.isValid(null));

        schema.required();

        Assertions.assertFalse(schema.isValid(null)); // false
        Assertions.assertTrue(schema.isValid(new HashMap())); // true

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        Assertions.assertTrue(schema.isValid(data)); // true

        schema.sizeof(2);

        Assertions.assertFalse(schema.isValid(data));  // false
        data.put("key2", "value2");
        Assertions.assertTrue(schema.isValid(data)); // true
    }

    @Test
    public void shapeSchemaTest() {
        Validator v = new Validator();

        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        Assertions.assertTrue(schema.isValid(human1)); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        Assertions.assertTrue(schema.isValid(human2)); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        Assertions.assertFalse(schema.isValid(human3)); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        Assertions.assertFalse(schema.isValid(human4)); // false
    }

}
