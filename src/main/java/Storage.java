import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;
    private TaskList taskList;

    /**
     * creates new Storage.
     * @param filePath the path to the duke.txt file where previous user input
     *                 has been stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskList = Duke.taskList;
    }

    /**
     * retrieves all the tasks entered previously by the user from duke.txt and
     * adds them to the TaskList.
     */
    public void retrieveInfo() {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String[] arr  = scanner.nextLine().split("[|]");
                Task newTask = null;

                if  (arr[0].trim().equals("T")) {
                    newTask = new ToDo(arr[2].trim());
                } else if (arr[0].trim().equals("D")) {
                    newTask = new Deadline(arr[2].trim(), arr[3].trim(),
                            Task.FORMATTER);
                } else if (arr[0].trim().equals("E")) {
                    newTask = new Event(arr[2].trim(), arr[3].trim(),
                            Task.FORMATTER);
                }

                if (arr[1].trim().equals("Y")) {
                    newTask.markAsDone();
                }
                taskList.add(newTask, "");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            return;
        }
    }

    /**
     * stores all the tasks the user has entered into the Tasklist in duke.txt.
     */
    public  void  updateInfo() {
        String  fileString = "";
        for (int i = 0; i < taskList.size(); i += 1) {
            fileString += taskList.get(i).fileString() + "\n";
        }

        try {
            new File(filePath).createNewFile();
            FileWriter fw = new FileWriter(filePath);
            fw.write(fileString);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
