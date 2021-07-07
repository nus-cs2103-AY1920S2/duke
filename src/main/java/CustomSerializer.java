import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a JSON serializer which is able to store polymorphic lists.
 */
//@@author giampaolo
//Reused from https://stackoverflow.com/a/19600090 with minor modifications
public class CustomSerializer implements JsonSerializer<List<Task>> {
    private static Map<String, Class> map = new TreeMap<>();

    static {
        map.put("Task", Task.class);
        map.put("Todo", Todo.class);
        map.put("Event", Event.class);
        map.put("Deadline", Deadline.class);
    }

    @Override
    public JsonElement serialize(List<Task> tasks, Type typeOfSrc,
                                 JsonSerializationContext context) {
        if (tasks == null) {
            return null;
        } else {
            JsonArray ja = new JsonArray();
            for (Task t : tasks) {
                Class c = map.get(t.getType());
                if (c == null) {
                    throw new RuntimeException("Unknown class: " + t.type);
                }
                ja.add(context.serialize(t, c));
            }
            return ja;
        }
    }
}
//@@author
