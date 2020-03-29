import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a stored object
 * @author kerwei
 * @version 1.0
 */
public class Storage {

    public String file;
    public ArrayList<String> tasksSaved;

    public Storage(String file, ArrayList<String> tasksSaved) {
        this.file = file;
        this.tasksSaved = tasksSaved;
    }

    /**
     * Returns the list of tasks saved and to be printed
     * @param file Name of file
     * @return List of string of tasks
     * @throws FileNotFoundException If file name is incorrect or does not exist
     */
    public static ArrayList<String> copySavedTasks(String file) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner scanIn = new Scanner(fileInputStream);
        ArrayList<String> tasksSaved = new ArrayList<>();
        while (scanIn.hasNext()) {
            tasksSaved.add(scanIn.nextLine());
        }
        return tasksSaved;
    }

    /**
     * Loads the task from file
     * @param file Name of file
     * @return List of tasks
     * @throws FileNotFoundException If file name is incorrect or does not exist
     */
    public static TaskList loadTask(String file) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        Scanner scanIn = new Scanner(fileInputStream);
        ArrayList<Task> list = new ArrayList<>();
        TaskList taskList = new TaskList(list);
        while (scanIn.hasNext()){
            String savedTask = scanIn.nextLine();
            String[] splitTask = savedTask.split(" /", 0);
            String task = splitTask[0];
            switch (task) {
                case ("todo"):
                    Todo todo = new Todo(splitTask[0], splitTask[2], "");
                    todo.isDone = Boolean.parseBoolean(splitTask[1]);
                    taskList.add(todo);
                    break;
                case ("deadline"):
                    Deadline deadline = new Deadline(splitTask[0], splitTask[2], splitTask[3]);
                    deadline.isDone = Boolean.parseBoolean(splitTask[1]);
                    taskList.add(deadline);
                    break;
                case ("event"):
                    Event event = new Event(splitTask[0], splitTask[2], splitTask[3]);
                    event.isDone = Boolean.parseBoolean(splitTask[1]);
                    taskList.add(event);
                    break;
            }

        }
        return taskList;

    }

    /**
     * Saves the task into file
     * @param file Name of file
     * @param tasksSaved List of string of task saved
     * @throws FileNotFoundException If file name is incorrect or does not exist
     */
    public static void saveTask(String file, ArrayList<String> tasksSaved) throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        PrintWriter writer = new PrintWriter(fileOutputStream);
        for (String s : tasksSaved) {
            writer.println(s);
        }
        writer.close();
    }
}
