import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Storage {
    public static ArrayList<Task> generateTaskList() {
        ArrayList<Task> lst = new ArrayList<>();
        try (Scanner scan = new Scanner(new File("src/main/data/data.csv"))) {
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");
                Task task = Task.generateTask(String.join(" ", line[0], line[1]));
                if (line[2].equals("T")) {
                    task.setDone();
                }
                lst.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }

    public static void writeTask(ArrayList<Task> lst) {
        try (FileWriter writer = new FileWriter("src/main/data/data.csv")) {
            String str = lst.stream()
                    .map(task ->
                            String.join(",", task.getType(),
                                    task.getDescription().trim(), task.getStatus()))
                    .collect(Collectors.joining("\n"));
            writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
