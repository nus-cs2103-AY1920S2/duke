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
        File file = getFile();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] taskContent = sc.nextLine().split("\\|");
            String taskType = taskContent[0];
            String status = taskContent[1];
            String description = taskContent[2];
            switch (taskType) {
            case "T":
                Task toDo = new ToDo(description);
                if (status.equals("true")) {
                    toDo.markAsDone();
                }
                tasks.add(toDo);
                break;
            case "D":
                Task deadLine = new Deadline(description, getDate(taskContent[3]), getTime(taskContent[3]));
                if (status.equals("true")) {
                    deadLine.markAsDone();
                }
                tasks.add(deadLine);
                break;
            case "E":
                Task event = new Event(description, getDate(taskContent[3]), getTime(taskContent[3]));
                if (status.equals("true")) {
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
     * Extracts the time of the task from the given string.
     *
     * @param s The string provided.
     * @return The String, i.e. the time of the event or deadline task provided in the string.
     */
    private String getTime(String s) {
        return s.substring(14);
    }

    /**
     * Extracts the date of the task from the given string.
     *
     * @param s The string provided.
     * @return LocalDate, i.e. the date of the event or deadline task provided in the string.
     */
    private LocalDate getDate(String s) {
        return LocalDate.parse(s.substring(3, 13));
    }

    /**
     * Saves the List of tasks to the file.
     *
     * @param tasks The list of tasks.
     * @throws IOException If an I/O error occurred.
     */
    public void saveFile(List<Task> tasks) throws IOException {
        File file = getFile();
        FileWriter writer = new FileWriter(file);
        for (Task task : tasks) {
            if (task instanceof ToDo) {
                writer.write("T|" + task.isDone + "|" + task.description + "\n");
            } else if (task instanceof Deadline) {
                writer.write("D|" + task.isDone + "|" + task.description + "|by " + ((Deadline) task).date
                        + " " + ((Deadline) task).time + "\n");
            } else if (task instanceof Event) {
                writer.write("E|" + task.isDone + "|" + task.description + "|at " + ((Event) task).date
                        + " " + ((Event) task).time + "\n");
            }
        }
        writer.close();
    }

    /**
     * Creates a file to store Duke's data if the file does not exist,
     * otherwise returns the existing file containing Duke's data.
     *
     * @return File, the file containing Duke's data.
     * @throws IOException If an I/O error occurred.
     */
    private File getFile() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        return new File(path.toString());
    }

}

