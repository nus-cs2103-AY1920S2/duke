import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHelper {
    private static final String taskDirPath = "data/";
    private static final String divider = " | ";

    public static ArrayList<Task> getList() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        checkDir();
        File file = getFile();
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String a = s.nextLine();
            String[] temp = a.split(" \\| ");
            switch(temp[0]) {
                case ("T"):
                    Todo t = new Todo(temp[2]);
                    if (temp[1].equals("1")) {
                        t.markDone();
                    }
                    tasks.add(t);
                    break;
                case ("D"):
                    Deadline d = new Deadline(temp[2], temp[3]);
                    if (temp[1].equals("1")) {
                        d.markDone();
                    }
                    tasks.add(d);
                    break;
                case ("E"):
                    Event e = new Event(temp[2], temp[3]);
                    if (temp[1].equals("1")) {
                        e.markDone();
                    }
                    tasks.add(e);
                    break;
            }
        }
        return tasks;
    }

    private static void checkDir() throws IOException {
        File dir = new File(Paths.get(taskDirPath).toUri());
        if (!dir.exists()) {
            boolean success = dir.mkdir();
            if (!success) {
                throw new IOException("Failed to create directory ");
            }
        }
    }

    private static File getFile() throws IOException {
        File file = new File(Paths.get(taskDirPath.concat("duke.txt")).toUri());
        if (!file.exists()) {
            boolean success = file.createNewFile();
            if (!success) {
                throw new IOException("Failed to create directory ");
            }
        }
        return file;
    }

    public static void appendToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(Paths.get(taskDirPath.concat("duke.txt")).toString(), true);
        fw.write(task.saveString() + System.lineSeparator());
        fw.close();
    }

    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(Paths.get(taskDirPath.concat("duke.txt")).toString());
        for (Task task : tasks) {
            fw.write(task.saveString() + System.lineSeparator());
        }
        fw.close();
    }
}
