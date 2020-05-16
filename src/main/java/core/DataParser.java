package core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import task.Deadline;
import task.Event;
import task.RecurringTask;
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

    /**
     * Initiates the parser with capabilities to parse local date and task.
     */
    public DataParser() {
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(Task.class, new TaskAdapter())
                .create();
    }

    /**
     * Parses task from task to string of standard data format.
     *
     * @param task task to be saved.
     * @return string of standard data format.
     */
    public String parseToString(Task task) {
        return gson.toJson(task);
    }

    /**
     * Parses string of standard data format to task.
     *
     * @param data string from the storage file.
     * @return task object.
     */
    public Task parseToTask(String data) {
        return gson.fromJson(data, Task.class);
    }

    /**
     * Enables the parser to serialize and deserialize LocalDate.
     */
    //Solution below adapted from https://stackoverflow.com/questions/39192945/serialize-java-8-localdate-as-yyyy-mm-dd-with-gson
    private static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

        @Override
        public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
        }

        @Override
        public LocalDate deserialize(JsonElement json, Type type,
                                     JsonDeserializationContext context) throws JsonParseException {
            return LocalDate.parse(json.getAsJsonPrimitive().getAsString());
        }
    }

    /**
     * Enables the parser to serialize and deserialize Task and its sub-class.
     */
    //Solution below adapted from https://stackoverflow.com/questions/5800433/polymorphism-with-gson with modification
    private static class TaskAdapter implements JsonSerializer<Task>, JsonDeserializer<Task> {

        private static Map<String, Class> map = new TreeMap<>();

        static {
            map.put("T", ToDo.class);
            map.put("D", Deadline.class);
            map.put("E", Event.class);
            map.put("R", RecurringTask.class);
        }

        @Override
        public JsonElement serialize(Task src, Type typeOfSrc,
                                     JsonSerializationContext context) {
            Class c = map.get(src.getTypeIcon().charAt(1));
            if (c == null) {
                throw new RuntimeException("Unknown class: " + src.getTypeIcon().charAt(1));
            }
            return context.serialize(src, c);
        }

        @Override
        public Task deserialize(JsonElement json, Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String type = jsonObject.get("typeIcon").getAsString();
            Class c = map.get(type);
            if (c == null) {
                throw new RuntimeException("Unknown class: " + type);
            }
            return context.deserialize(json, c);
        }
    }
}
