import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Storage class is for database I/O.
 * Methods to load Tasks from database and save Tasks to database are available.
 */

public class Storage {

    private Path filePath;

    /**
     * Initialises database.
     */
    // Solution below adapted from
    // https://www.sghill.net/how-do-i-make-cross-platform-file-paths-in-java.html
    public Storage() throws IOException {
        String home = System.getProperty("user.home");
        Path dirPath = Paths.get(home);
        Path filePath = Paths.get(home, "duke-database.txt");

        if (Files.exists(dirPath)) {
            if (Files.notExists(filePath)) {
                try {
                    Files.createFile(filePath);
                } catch (IOException e) {
                    System.err.println("File cannot be created. Please check your permissions");
                }
            }
        } else {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                System.out.println("Directory cannot be created. Please check your permissions.");
            }
        }
        this.filePath = filePath;
    }

    /**
     * Generates all Tasks of the TaskList from the database.
     */
    public List<Task> getAllTasksFromFile() throws FileNotFoundException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(String.valueOf(filePath));
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(file);
        } catch (FileNotFoundException error) {
            System.out.println("File not found. Please try again");
        }
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

    /**
     * Saves all Tasks of the TaskList to the database.
     */
    public void saveAllTasksToFile(TaskList tasks) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File(String.valueOf(filePath)));
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
