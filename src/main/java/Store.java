import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private Path filePath;
    private File file;

    public Store(Path filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath.toString());
    }

    public void update(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task t : tasks) {
                fw.write(String.format("%s%n", t.toFileFormat()));
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getStoredTasks() {
        ArrayList<Task> tempList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(this.filePath);
            for (String line : lines) {
                try {
                    Task t = Task.getTaskFromMemory(line);
                    tempList.add(t);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempList;
    }
}
