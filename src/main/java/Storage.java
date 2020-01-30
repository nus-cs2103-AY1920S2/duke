import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> generateTaskList() {
        ArrayList<Task> lst = new ArrayList<>();
        try (Scanner scan = new Scanner(new File(filePath))) {
            while (scan.hasNextLine()) {
                String[] line = scan.nextLine().split(",");
                boolean isDone = line[2].equals("T");
                String[] line2 = new String[]{line[0], line[1]};
                Task task = Task.generateTask(line2);
                if (isDone) {
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

    public void writeTask(ArrayList<Task> lst) {
        try (FileWriter writer = new FileWriter(filePath)) {
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
