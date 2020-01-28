import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    private static String FILE_PATH = "../../../data/duke.txt";

    public static void readFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] split = line.strip().split(" ", 3);

            String isDone = split[0];
            String type = split[1];
            String typeRemoved = split[2];

            Task task = type.equals("todo")
                    ? new ToDo(typeRemoved)
                    : type.equals("deadline")
                    ? new Deadline(typeRemoved)
                    : new Event(typeRemoved);
            if (isDone.equals("1")) {
                task.doTask();
            }
            tasks.add(task);
        }
    }

    public static void addTask(String task) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write("0" + " " + task + "\n");
        fw.close();
    }

    public static void deleteTask(int index) throws IOException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();

        while (s.hasNext()) {
            lines.add(s.nextLine());
        }
        lines.remove(index);
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write("");
        fw.close();

        fw = new FileWriter(FILE_PATH, true);

        for (String line : lines) {
            fw.write(line + "\n");
        }
        fw.close();
    }

    public static void doTask(int index) throws IOException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();

        while (s.hasNext()) {
            lines.add(s.nextLine());
        }
        lines.set(index, "1" + lines.get(index).substring(1));
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write("");
        fw.close();

        fw = new FileWriter(FILE_PATH, true);

        for (String line : lines) {
            fw.write(line + "\n");
        }
        fw.close();
    }
}
