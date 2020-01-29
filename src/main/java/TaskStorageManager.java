import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class TaskStorageManager {
    public final String STORAGE_PATH = "./data/tasks.txt";

    public boolean save(List<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(STORAGE_PATH);
            for (Task task : tasks) {
                writer.write(task.toStorage());
                writer.write("\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public List<Task> load() {
        try {
            List<Task> output = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(STORAGE_PATH));
            reader.lines()
                .forEach(line -> output.add(Task.fromStorage(line)));
            reader.close();
            return output;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}