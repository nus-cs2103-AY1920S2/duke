import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class abstracts the I/O method of
 * reading and writing tasks from a file.
 */
public class Storage {

    private static List<Task> tasks;

    /**
     * Constructs a Storage instance.
     */
    public Storage() {
    }

    /**
     * Loads the tasks from the file.
     *
     * @return The List of all the tasks loaded from the file.
     * @throws IOException If an I/O error occurred.
     */
    public List<Task> loadFile() throws IOException {
        tasks = new ArrayList<>();
        Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        File file = new File(path.toString());
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] task = sc.nextLine().split("/");
            switch (task[0]) {
            case "T":
                Task toDo = new ToDo(task[2]);
                if (task[1].equals("true")) {
                    toDo.markAsDone();
                }
                tasks.add(toDo);
                break;
            case "D":
                Task deadLine = new Deadline(task[2],
                        LocalDate.parse(task[3].substring(3, 13)),
                        task[3].substring(14));
                if (task[1].equals("true")) {
                    deadLine.markAsDone();
                }
                tasks.add(deadLine);
                break;
            case "E":
                Task event = new Event(task[2],
                        LocalDate.parse(task[3].substring(3, 13)),
                        task[3].substring(14));
                if (task[1].equals("true")) {
                    event.markAsDone();
                }
                tasks.add(event);
                break;
            default:
                break;
            }
        }
        return tasks;
    }

    /**
     * Saves the List of tasks to the file.
     *
     * @param tasks The list of tasks.
     * @throws IOException If an I/O error occurred.
     */
    public void saveFile(List<Task> tasks) throws IOException {
        Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        File file = new File(path.toString());
        FileWriter writer = new FileWriter(file);
        for (Task task : tasks) {
            if (task instanceof ToDo) {
                writer.write("T/" + task.isDone + "/" + task.description + "\n");
            } else if (task instanceof Deadline) {
                writer.write("D/" + task.isDone + "/" + task.description + "/by " + ((Deadline) task).date
                        + " " + ((Deadline) task).time + "\n");
            } else if (task instanceof Event) {
                writer.write("E/" + task.isDone + "/" + task.description + "/at " + ((Event) task).date
                        + " " + ((Event) task).time + "\n");
            }
        }
        writer.close();
    }
}
