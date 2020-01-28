import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private static List<Task> tasks;

    public Storage() {
    }

    public List<Task> loadFile() throws FileNotFoundException {
        tasks = new ArrayList<>();
        Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
        File file = new File(path.toString());
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String[] task = sc.nextLine().split("/");
            switch (task[0]) {
                case "T":
                    Task toDo = new Todo(task[2]);
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
            }
        }
        return tasks;
    }

    public void saveFile(List<Task> tasks) throws IOException {
        Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }
        File file = new File(path.toString());
        FileWriter writer = new FileWriter(file);
        for (Task task : tasks) {
            if (task instanceof Todo) {
                writer.write("T/" + task.isDone + "/ " + task.description.trim() + "\n");
            } else if (task instanceof Deadline) {
                writer.write("D/" + task.isDone + "/ " + task.description.trim() + " /by " + ((Deadline) task).date
                        + " " + ((Deadline) task).time + "\n");
            } else if (task instanceof Event) {
                writer.write("E/" + task.isDone + "/ " + task.description.trim() + " /at " + ((Event) task).date
                        + " " + ((Event) task).time + "\n");
            }
        }
        writer.close();
    }
}
