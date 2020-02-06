import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Storage class is for database I/O.
 * Methods to load Tasks from database and save Tasks to database are available.
 */
public class Storage {
    /**
        referenced from
        https://crunchify.com/java-saving-and-loading-data-from-a-file-simple-production-ready-utility-for-file-readwrite-operation/
     */

    //String fileName = "./data/duke.txt"; // relative path
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
            // index 0: type, index 1: 1/0 (isDone), index 2: description, index 3: time
            if (nextLine[0].trim().equals("T")) { // Todo
                tasks.add(new Todo(nextLine[2].trim())); //trim space
            } else if (nextLine[0].trim().equals("D")) {
                tasks.add(new Deadline(nextLine[2].trim(), nextLine[3].trim()));
            } else if (nextLine[0].trim().equals("E")) {
                tasks.add(new Event(nextLine[2].trim(), nextLine[3].trim()));
            }
            if (nextLine[1].trim().equals("1")) { // marked as done
                tasks.get(tasks.size() - 1).markAsDone(); // last element
            }
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
                                + task.getTime()
                );
            }
            pw.close();
    }
}
