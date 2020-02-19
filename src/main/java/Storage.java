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
    private static final String TASK_FILE = "taskStore.txt";
    private static final String TAG_FILE = "tagStore.txt";

    private static final String NEXT_TASK_TAG = "NEXT_TASK";
    /**
     * Attempts to store a list of tasks into a txt file.
     *
     * @param tasks list of tasks to store
     * @throws IOException if file can't be found
     */
    public static void storeIntoFile(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(TASK_FILE);
        FileWriter tagWriter =  new FileWriter(TAG_FILE);
        for (Task task : tasks) {
            fw.write(task.formatToStore() + "\n");
            String tagToStore = "";
            for(Tag t : task.getTags()){
               tagToStore += t.toString() + "\n";
            }
            tagToStore += NEXT_TASK_TAG + "\n";
            tagWriter.write(tagToStore);
        }
        fw.close();
        tagWriter.close();

    }

    /**
     * Attempts to read from a file which is used to save previous iterations of the list of tasks
     * and load it into a new list of tasks
     *
     * @return a list of tasks if successfully loaded else returns an empty task list
     */
    public static List<Task> readFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File f = new File(TASK_FILE);
            File tagFile = new File(TAG_FILE);
            try {
                f.createNewFile();
                tagFile.createNewFile();
            } catch (IOException e){

            }
            Scanner s1 = new Scanner(f);
            Scanner tagScan = new Scanner(tagFile);
            while (s1.hasNext()) {
                String taskDes = s1.nextLine();
                Scanner s2 = new Scanner(taskDes);
                String taskType = s2.next();
                String taskDescription = s2.nextLine();
                try {
                    Task currentTask = TaskHandler.parseFromFile(taskType, taskDescription);
                    Storage.readTags(currentTask, tagScan);
                    tasks.add(currentTask);
                } catch (InvalidInputException e) {
                    System.out.println("Cannot parse from text file");
                }
            }
        } catch (FileNotFoundException e) {

            System.out.println("File unable to be found");
        }
        return tasks;
    }

    public static void readTags(Task myTask, Scanner sc){
        String nextLine = sc.nextLine();
        while(!nextLine.equals(NEXT_TASK_TAG)){
            myTask.addTag(nextLine);
            nextLine = sc.nextLine();
        }
    }


}
