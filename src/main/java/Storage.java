import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String workingDir = System.getProperty("user.dir");
    private Path file;

    public Storage(String filePath) {
        this.file = Paths.get(workingDir, filePath);
    }

    public List<Task> loadTasks() {
        List<Task> retrievedTasks = new ArrayList<Task>();

        try {
            if (!Files.exists(file)) {
                Files.createDirectories(file.getParent());
                Files.createFile(file);
            } else {
                List<String> lines = Files.readAllLines(file);

                for (String line : lines) {
                    String[] lineSplit = line.split(" \\| ");

                    if (lineSplit[0].equals("T")) {
                        retrievedTasks.add(new Todo(lineSplit[2], parseTaskStatusFromString(lineSplit[1])));
                    } else if (lineSplit[0].equals("D")) {
                        retrievedTasks.add(new Deadline(lineSplit[2], LocalDate.parse(lineSplit[3]),
                                parseTaskStatusFromString(lineSplit[1])));
                    } else if (lineSplit[0].equals("E")) {
                        retrievedTasks.add(new Event(lineSplit[2], LocalDate.parse(lineSplit[3]),
                                parseTaskStatusFromString(lineSplit[1])));
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return retrievedTasks;
    }

    public void storeTasks(List<Task> tasks) {
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append(task.generateSaveFileEntry());
        }

        try {
            Files.write(file, result.toString().getBytes());
        } catch (FileNotFoundException ex) {
            System.err.println("Task file not found.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean parseTaskStatusFromString(String status) {
        return status.equals("1") ? true : false;
    }
}
