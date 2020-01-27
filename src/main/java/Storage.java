import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(" \\| ");
                Task task;
                switch (data[0]) {
                    case "T":
                        task = new Todo(data[2], data[1].equals("1"));
                        break;
                    case "D":
                        task = new Deadline(data[2], data[3], data[1].equals("1"));
                        break;
                    case "E":
                        task = new Event(data[2], data[3], data[1].equals("1"));
                        break;
                    default:
                        throw new DukeException("Could not load tasks.");
                }
                tasks.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            createFile();
        }
        return tasks;
    }

    private void createFile() throws DukeException {
        try {
            Path path = Paths.get(filePath);
            Path parentDir = path.getParent();
            if (parentDir != null) {
                Files.createDirectories(parentDir);
            }
            Files.createFile(path);
        } catch (IOException e) {
            throw new DukeException("Could not create data file.");
        }
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                fw.write(task.formatToSave() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Could not save tasks.");
        }
    }
}
