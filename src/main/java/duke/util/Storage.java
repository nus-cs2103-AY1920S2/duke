package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class Storage {

    /**
     * Checks if it's possible to get the file stored at Documents/Duke/dukeData.txt
     * Create the file if necessary and possible
     * @param storedItems
     */
    public void getDataFile(ArrayList<Task> storedItems) {
        String home = System.getProperty("user.home");

        Path dirPath = Paths.get(home, "Documents", "Duke");
        Path filePath = Paths.get(home, "Documents", "Duke", "dukeData.txt");

        if (Files.exists(dirPath)) {
            if (Files.notExists(filePath))
                try {
                    Files.createFile(filePath);
                } catch (IOException e) {
                    System.out.println(e);
                    exit(1);
                }

            readDataFile(filePath, storedItems);
        } else {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                System.out.println(e);
                exit(1);
            } finally {
                getDataFile(storedItems);
            }
        }
    }

    /**
     * Write all the task details from storedItems to local file
     * @param storedItems
     */
    public void writeData(ArrayList<Task> storedItems) {
        String home = System.getProperty("user.home");
        Path filePath = Paths.get(home, "Documents", "Duke", "dukeData.txt");

        StringBuilder data = new StringBuilder();
        for (Task item : storedItems) {
            data.append(item.encoder());
        }

        try {
            Files.writeString(filePath, data.toString());
        } catch (IOException e) {
            System.out.println(e);
            exit(1);
        }
    }

    /**
     * Read all data in filePath and turn them into tasks in storedItems
     * @param filePath
     * @param storedItems
     */
    private void readDataFile(Path filePath, ArrayList<Task> storedItems) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.out.println(e);
            exit(1);
        } finally {
            if (lines.isEmpty())
                return;

            for (String line : lines) {
                String[] lineParts = line.split(":");
                if (lineParts[0].equals("T"))
                    storedItems.add(new ToDo(lineParts[1], Integer.parseInt(lineParts[2]) == 1));
                else if (lineParts[0].equals("D"))
                    storedItems.add(new Deadline(lineParts[1], Integer.parseInt(lineParts[2]) == 1, lineParts[3]));
                else if (lineParts[0].equals("E"))
                    storedItems.add(new Event(lineParts[1], Integer.parseInt(lineParts[2]) == 1, lineParts[3]));
            }
        }
    }
}
