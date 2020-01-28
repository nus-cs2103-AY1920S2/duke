import java.util.LinkedList;
import java.nio.file.Paths;
import java.nio.file.Files;

public class DataManager {
    java.nio.file.Path path;

    public DataManager() {
        String currentDirectory = System.getProperty("user.dir");
        this.path = java.nio.file.Paths.get(currentDirectory, "data", "duke.txt");
    }

    public void saveData(LinkedList<Task> list) {

    }

    public void loadData(Tracker tracker) {

    }

    public boolean hasPreviousData() {
        System.out.println(java.nio.file.Files.exists(path));
    }
}
