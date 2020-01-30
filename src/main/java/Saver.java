import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Saver {
    public static String taskParser(Task task) {
        String taskString;
        String isDone = task.getIsDone() ? "1" : "0";
        taskString = String.format(
                "%s|%s|%s|%s",
                task.getType(), isDone,
                task.getDescription(), task.getDate());
        return taskString;
    }

    public static void fullSaver(ArrayList<Task> taskList) throws IOException {
        try {
            File file = new File("savedata.txt");
            FileWriter writer = new FileWriter(file);

            for (Task task : taskList) {
                String data = taskParser(task);
                System.out.println(data);
                writer.write(data + "\n");
            }

            writer.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public static void loader(ArrayList<Task> taskList) throws IOException {
        try {
            File file = new File("savedata.txt");
            FileReader reader = new FileReader(file);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineSplit = line.split("|");
                Task task;

                switch (lineSplit[0]) {
                    case "T":
                        task = new Todo(lineSplit[2]);
                        break;
                    case "D":
                        task = new Deadline(lineSplit[2], lineSplit[3]);
                        break;
                    case "E":
                        task = new Event(lineSplit[2], lineSplit[3]);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + lineSplit[0]);
                }
                if (lineSplit[1].equals("1")) {task.markAsDone();}
                taskList.add(task);
            }
            scanner.close();

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

}
