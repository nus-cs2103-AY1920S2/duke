import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Storage {

    private File file;

    public Storage(String filePath) {
        String[] path = filePath.split("/");
        String root = Paths.get("").toAbsolutePath().toString();
        this.file = new File(filePath);
        if (!file.exists()) {
            try {
                Files.createDirectories(Paths.get(root + File.separator + path[0]));
                Files.createFile(Paths.get(root + File.separator + path[0] + File.separator + path[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                fw.write(task.formatSavingName());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] task = s.nextLine().split(",");
                switch (task[0]) {
                case "todo":
                    tasks.add(new Todo(task[1], Boolean.parseBoolean(task[2])));
                    break;
                case "deadline":
                    tasks.add(new Deadline(task[1], Boolean.parseBoolean(task[2]), LocalDate.parse(task[3])));
                    break;
                case "event":
                    tasks.add(new Event(task[1], Boolean.parseBoolean(task[2]), LocalDate.parse(task[3])));
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Loading error");
        } catch (DateTimeException e) {
            throw new DukeException("OOPS!!! Please give me the date in yyyy-mm-dd format!");
        }
        return tasks;
    }

}
