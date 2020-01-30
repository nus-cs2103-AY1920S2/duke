package core;

import com.google.gson.*;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.TreeMap;

/**
 * Parses between task and string of standard data format.
 */
public class DataParser {

    private Gson gson;

    public DataParser(){
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(Task.class, new TaskAdapter())
                .create();
    }

    /**
     * Parses task from task to string of standard data format.
     * @param task task to be saved.
     * @return string of standard data format.
     */
    public String parseToString(Task task){
        return gson.toJson(task);
    }

    /**
     * Parses string of standard data format to task.
     * @param data string from the storage file.
     * @return task object.
     */
    public Task parseToTask(String data){
        return gson.fromJson(data,Task.class);
    }

    /**
     * Enables the parser to serialize and deserialize LocalDate.
     */
    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
        }
    }

    /**
     * Enables the parser to serialize and deserialize Task and its sub-class.
     */
    private static class TaskAdapter implements JsonSerializer<Task>, JsonDeserializer<Task>{

        private static Map<String, Class> map = new TreeMap<>();

        static {
            map.put("T", ToDo.class);
            map.put("D", Deadline.class);
            map.put("E", Event.class);
        }

        @Override
        public JsonElement serialize(Task src, Type typeOfSrc,
                                     JsonSerializationContext context) {
            Class c=map.get(src.getTypeIcon().charAt(1));
            if (c == null) {
                throw new RuntimeException("Unknow class: " + src.getTypeIcon().charAt(1));
            }
            return context.serialize(src, c);
        }

        @Override
        public Task deserialize(JsonElement json, Type typeOfT,
                                   JsonDeserializationContext context) throws JsonParseException  {
            JsonObject jsonObject=json.getAsJsonObject();
            String type = jsonObject.get("typeIcon").getAsString();
            Class c=map.get(type);
            if (c == null) {
                throw new RuntimeException("Unknow class: " + type);
            }
            return context.deserialize(json, c);
        }
    }
}
