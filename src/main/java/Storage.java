import java.io.*;
import java.nio.file.Path;
import java.util.LinkedList;
import java.nio.file.Paths;
import java.nio.file.Files;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Storage {
    Path path;

    public Storage() {
        String currentDirectory = System.getProperty("user.dir");
        Path dataDirectory = java.nio.file.Paths.get(currentDirectory, "data");
        this.path = java.nio.file.Paths.get(currentDirectory, "data", "duke.txt");

        if (!java.nio.file.Files.exists(dataDirectory)) {
            try {
                java.nio.file.Files.createDirectory(dataDirectory);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void saveData(LinkedList<Task> list) {
        try {
            java.nio.file.Files.deleteIfExists(path);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String taskString = task.toStore() + "\n";
            byte data[] = taskString.getBytes();

            try (OutputStream out = new BufferedOutputStream(
                    Files.newOutputStream(this.path, CREATE, APPEND))) {
                out.write(data, 0, data.length);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void loadData(Tracker tracker) {
        try (InputStream in = Files.newInputStream(this.path);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            int index = 0;

            while ((line = reader.readLine()) != null) {
                String taskType = line.substring(1,2);
                String state = line.substring(4,5);
                String content = line.substring(7);

                switch (taskType) {
                case "T":
                    tracker.add(new ToDo(content));
                    break;
                case "E":
                    String[] eventArray = content.split(" \\(at: ");
                    int eContentLength = eventArray[1].length();
                    tracker.add(new Event(eventArray[0], eventArray[1].substring(0, eContentLength - 1)));
                    break;
                case "D":
                    String[] deadlineArray = content.split(" \\(by: ");
                    int dContentLength = deadlineArray[1].length();
                    tracker.add(new Deadline(deadlineArray[0], deadlineArray[1].substring(0, dContentLength - 1)));
                    break;
                }

                if (state.equals("\u2713")) {
                    tracker.markDone(index);
                }

                index++;
            }
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public boolean hasPreviousData() {
        return java.nio.file.Files.exists(this.path);
    }
}
