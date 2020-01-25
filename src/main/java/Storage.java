import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    //private static Path defaultPath = Paths.get("src", "main", "java", "src" , "taskStore.txt");

    public static void storeIntoFile(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter("src/taskStore.txt");
        for(Task task : tasks) {
            fw.write(task.formatToStore() + "\n");
        }
        fw.close();

    }

    public static List<Task> readFromFile() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File("src/taskStore.txt");
        Scanner s1 = new Scanner(f);
        while(s1.hasNext()) {
            String taskDes = s1.nextLine();
            Scanner s2 = new Scanner(taskDes);
            String taskType = s2.next();
            String taskDescription = s2.nextLine();
            try {
                Task currentTask = TaskHandler.parseFromFile(taskType, taskDescription);
                tasks.add(currentTask);
            } catch (InvalidInputException e) {
                System.out.println(e);
            }


        }
        return tasks;
    }



}
