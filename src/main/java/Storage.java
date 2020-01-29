import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Storage {
    private static final String storagePath = "data/duke.txt";
    public static void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(storagePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(storagePath);
        fw.write(textToAdd);
        fw.close();
    }

    public static void writeTasks(List<Task> tasks) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Task task: tasks) {
            sb.append(task.toStorageString());
        }
        writeToFile(sb.toString());
    }


}
