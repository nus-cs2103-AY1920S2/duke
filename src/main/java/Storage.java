import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private static String FILE_PATH = "data/duke.txt";

    /**
     * Adds all tasks found in storage to input tasks.
     * @param tasks TaskList to add read tasks to
     * @throws FileNotFoundException Thrown when file is not found
     */
    public static void readFile(TaskList tasks) throws FileNotFoundException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            Parser parse = new Parser(line, true);
            Task task = parse.createTask();
            tasks.add(task);
        }
    }

    /**
     * Writes task to storage.
     * @param task Task to store in storage
     * @throws IOException Thrown when I/O error occurs
     */
    public static void addTask(String task) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write("0" + " " + task + "\n");
        fw.close();
    }

    /**
     * Delete task from storage.
     * @param index Index of task to remove from storage
     * @throws IOException Thrown when I/O error occurs
     */
    public static void deleteTask(int index) throws IOException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();

        while (s.hasNext()) {
            lines.add(s.nextLine());
        }
        lines.remove(index);

        deleteContent();

        FileWriter fw = new FileWriter(FILE_PATH, true);

        for (String line : lines) {
            fw.write(line + "\n");
        }
        fw.close();
    }

    /**
     * Perform task in storage.
     * @param index Index of the task to perform
     * @throws IOException Thrown when I/O error occurs
     */
    public static void doTask(int index) throws IOException {
        File f = new File(FILE_PATH);
        Scanner s = new Scanner(f);
        ArrayList<String> lines = new ArrayList<>();

        while (s.hasNext()) {
            lines.add(s.nextLine());
        }
        lines.set(index, "1" + lines.get(index).substring(1));

        deleteContent();

        FileWriter fw = new FileWriter(FILE_PATH, true);

        for (String line : lines) {
            fw.write(line + "\n");
        }
        fw.close();
    }

    private static void deleteContent() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write("");
        fw.close();
    }
}
