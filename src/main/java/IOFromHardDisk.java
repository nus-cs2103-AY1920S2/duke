import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IOFromHardDisk {
    /**
        referenced from
        https://crunchify.com/java-saving-and-loading-data-from-a-file-simple-production-ready-utility-for-file-readwrite-operation/
     */
    String fileName = "./data/duke.txt"; // relative path
    Scanner inputStream;
    public IOFromHardDisk() {
        try {
            inputStream = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + fileName);
            System.exit(0);
        }
    }

    public List<Task> getAllTasksFromFile() {
        List<Task> history = new ArrayList<>();
        while (inputStream.hasNextLine()) {
            String currentLine = inputStream.nextLine();
            String[] nextLine = currentLine.split("\\|");
            // index 0: type, index 1: 1/0 (isDone), index 2: description, index 3: time
            if (nextLine[0].trim().equals("T")) { // Todo
                history.add(new Todo(nextLine[2].trim())); //trim space
            } else if (nextLine[0].trim().equals("D")) {
                history.add(new Deadline(nextLine[2].trim(), nextLine[3].trim()));
            } else if (nextLine[0].trim().equals("E")) {
                history.add(new Event(nextLine[2].trim(), nextLine[3].trim()));
            }
            if (nextLine[1].trim().equals("1")) { // marked as done
                history.get(history.size() - 1).markAsDone(); // last element
            }
        }
        return history;
    }

    public void saveAllTasksToFile(@NotNull List<Task> history) throws FileNotFoundException {
        System.out.println(fileName);
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
            for (Task task : history) {
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
