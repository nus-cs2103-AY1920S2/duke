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

    /**
     * Attempts to store a list of tasks into a txt file.
     * @param tasks list of tasks to store
     * @throws IOException if file can't be found
     */
    public static void storeIntoFile(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter("src/taskStore.txt");
        for(Task task : tasks) {
            fw.write(task.formatToStore() + "\n");
        }
        fw.close();

    }

    /**
     * Attempts to read from a file which is used to save previous iterations of the list of tasks
     * and load it into a new list of tasks
     * @return a list of tasks if successfully loaded else returns an empty task list
     */
    public static List<Task> readFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File f = new File("src/taskStore.txt");
            Scanner s1 = new Scanner(f);
            while (s1.hasNext()) {
                String taskDes = s1.nextLine();
                Scanner s2 = new Scanner(taskDes);
                String taskType = s2.next();
                String taskDescription = s2.nextLine();
                try {
                    Task currentTask = TaskHandler.parseFromFile(taskType, taskDescription);
                    tasks.add(currentTask);
                } catch (InvalidInputException e) {
                    System.out.println("Cannot parse from text file");
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("File unable to be found");
        }
        return tasks;
    }



}
