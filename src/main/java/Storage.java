import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Storage class is for database I/O.
 * Methods to load Tasks from database and save Tasks to database are available.
 */
public class Storage {

    private Scanner inputStream;
    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
        try {
            inputStream = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + fileName);
            System.exit(0);
        }
    }

    public List<Task> getAllTasksFromFile() {
        List<Task> tasks = new ArrayList<>();
        while (inputStream.hasNextLine()) {
            String currentLine = inputStream.nextLine();
            String[] nextLine = currentLine.split("\\|");

            Task task = new Todo((nextLine[2])); // default
            if (nextLine[0].equals("T")) { // Todo
                task = new Todo(nextLine[2]);
            } else if (nextLine[0].equals("D")) {
                task = new Deadline(nextLine[2], nextLine[3]);
            } else if (nextLine[0].equals("E")) {
                task = new Deadline(nextLine[2], nextLine[3]);
            }

            if (nextLine[1].trim().equals("1")) {
                task.markAsDone();
            }
            tasks.add(task);
        }
        return tasks;
    }

    public void saveAllTasksToFile(TaskList tasks) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
        for (Task task : tasks.getTasks()) {
            int isDone = (task.isTaskDone()) ? 1 : 0;

            pw.println(
                    task.getTypeName() + "|"
                            + isDone + "|"
                            + task.getDescription() + "|"
                            + task.getTimeToDatabase()
            );
        }
        pw.close();
    }
}
