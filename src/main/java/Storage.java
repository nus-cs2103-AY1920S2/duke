import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private Path filePath;
    private File file;

    public Storage(Path filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath.toString());
    }

    public void update(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(String.format("%s%n", tasks.getTask(i + 1).toFileFormat()));
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() throws DukeException {
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
            throw new DukeException(e.getMessage());
        }
        return tempList;
    }
}
