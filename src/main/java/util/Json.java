package util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Json {
    private static final Gson gson;
    private static final DateTimeFormatter DATE = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());

        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());

        gson = gsonBuilder.setPrettyPrinting().create();
    }
    public static String parseJson(Object object) {
        return gson.toJson(object);
    }
    public static <T> T toObject(String jsonObject, Type classOfObject){
        return gson.fromJson(jsonObject, classOfObject);
    }
    private static class LocalDateSerializer implements JsonSerializer<LocalDate> {
        @Override
        public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext context) {
            return new JsonPrimitive(DATE.format(localDate));
        }
    }
    private static class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString(), DATE.withLocale(Locale.ENGLISH));
        }
    }
    private static class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(DATE_TIME.format(localDateTime));
        }
    }
    private static class LocalDateTimeDeserializer implements JsonDeserializer< LocalDateTime > {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(), DATE_TIME.withLocale(Locale.ENGLISH));
        }
    }
}
