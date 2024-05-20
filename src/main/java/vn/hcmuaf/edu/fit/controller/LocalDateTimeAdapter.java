package vn.hcmuaf.edu.fit.controller;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    public final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD");
    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       return LocalDateTime.parse(jsonElement.getAsString(),formatter);
    }

    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
       return new JsonPrimitive(formatter.format(localDateTime));
    }
}