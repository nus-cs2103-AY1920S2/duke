import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    private Gson gson;


    public Storage(String filePath) {
        this.filePath = filePath;
        this.gson = new Gson();
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        Task[] taskArray = tasks.toArray(new Task[tasks.size()]);
        fileWriter.write(gson.toJson(taskArray, Task[].class));
        fileWriter.close();
    }

    public ArrayList<Task> load() {
        try {
            String fileContent = Files.readString(Path.of(this.filePath));
            JsonArray array = JsonParser.parseString(fileContent).getAsJsonArray();
            ArrayList<Task> result = new ArrayList<>();
            for (int i = 0; i < array.size(); i++) {
                JsonObject taskToCheck = (JsonObject) array.get(i);
                if (taskToCheck.has("deadline")) {
                    result.add(new Deadline(gson.fromJson(taskToCheck.get("description"), String.class),
                            gson.fromJson(taskToCheck.get("deadline"), String.class),
                            gson.fromJson(taskToCheck.get("isCompleted"), boolean.class)));
                } else if (taskToCheck.has("timeframe")) {
                    result.add(new Event(gson.fromJson(taskToCheck.get("description"), String.class),
                            gson.fromJson(taskToCheck.get("timeframe"), String.class),
                            gson.fromJson(taskToCheck.get("isCompleted"), boolean.class)));
                } else {
                    result.add(new ToDo(gson.fromJson(taskToCheck.get("description"), String.class),
                            gson.fromJson(taskToCheck.get("isCompleted"), boolean.class)));
                }
            }
            return result;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
