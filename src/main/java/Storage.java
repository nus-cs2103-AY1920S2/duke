import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String storagePath = "data/duke.txt";
    public static void appendToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(storagePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void writeToFile(String textToAdd) {
        try {
            FileWriter fw = new FileWriter(storagePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void writeTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task task: tasks) {
            sb.append(task.toStorageString());
        }
        writeToFile(sb.toString());
    }

    public static List<List<String>> loadTasksFromSaveFile() throws FileNotFoundException {
        List<List<String>> tasks = new ArrayList<>();
        File f = new File(storagePath);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String line = sc.nextLine();
            // It's formatted with the pipe
            String[] separated = line.split(" \\| ");

            // Turn the String array into an array list
            List<String> al = Arrays.asList(separated);
            tasks.add(al);
        }
        return tasks;
    }


}
