package za.co.knonchalant.space.domain;

import com.google.gson.*;

import java.lang.reflect.Type;

public class DataDeserializer implements JsonDeserializer<Object> {
    private final Class<?> target;

    public DataDeserializer(Class<?> target) {
        this.target = target;
    }

    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonElement data = ((JsonObject) jsonElement).get("data");
        return jsonDeserializationContext.deserialize(data, target);
    }
}
