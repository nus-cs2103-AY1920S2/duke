import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a custom JSON deserializer that can parse subclasses from the JSON
 * form of a list of the base class.
 */
//@@author giampaolo
//Reused from https://stackoverflow.com/a/19600090 with minor modifications
public class CustomDeserializer implements JsonDeserializer<List<Task>> {

    private static Map<String, Class> map = new TreeMap<>();

    static {
        map.put("Task", Task.class);
        map.put("Todo", Todo.class);
        map.put("Event", Event.class);
        map.put("Deadline", Deadline.class);
    }

    @Override
    public List<Task> deserialize(JsonElement json, Type typeOfT,
                                  JsonDeserializationContext context) throws JsonParseException {

        List list = new ArrayList<>();
        JsonArray ja = json.getAsJsonArray();

        for (JsonElement je : ja) {
            String type = je.getAsJsonObject().get("type").getAsString();
            Class c = map.get(type);
            if (c == null) {
                throw new RuntimeException("Unknown class: " + type);
            }
            list.add(context.deserialize(je, c));
        }
        return list;
    }
}
//@@author
