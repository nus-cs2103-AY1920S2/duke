import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage class.
 * This class handles the data from a text file. It only has two methods : store and load.
 *
 * @author Amos Cheong
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * When the user starts the program, it will load the previous tasks into the current list.
     *
     * @return List<Task> List of Tasks.
     * @throws IOException Error opening the file.
     */
    public List<Task> load() throws IOException {
        List<Task> Tasklist = new ArrayList<Task>();

        BufferedReader reader = new BufferedReader(new FileReader(this.filePath));

        String currentline = reader.readLine();

        while (currentline != null) {
            String[] arr = currentline.split(" ", 4);

            String expression = arr[0];

            // For every data extracted from the .txt file
            // figure out the type of object and add it into
            // the list.
            switch (expression) {
            case "[E]":
                Event eventTask = new Event(arr[2], arr[3]);
                if (arr[1].equals("Y"))
                    eventTask.taskIsDone();
                Tasklist.add(eventTask);
                break;
            case "[D]":
                Deadline deadlineTask = new Deadline(arr[2], arr[3]);
                if (arr[1].equals("Y"))
                    deadlineTask.taskIsDone();
                Tasklist.add(deadlineTask);
                break;
            case "[T]":
                Todo TodoTask = new Todo(arr[2]);
                if (arr[1].equals("Y"))
                    TodoTask.taskIsDone();
                Tasklist.add(TodoTask);
                break;
            }

            currentline = reader.readLine();
        }
        reader.close();

        return Tasklist;
    }

    /**
     * Whenever the tasklist is added or deleted, the store method will update the .txt file.
     * The information stored in the .txt file are the tasks in the list.
     *
     * @param ui       Ui object for printing to user.
     * @param alltasks TaskList object as argument.
     */
    public void store(TaskList alltasks, Ui ui) {
        try {
            String line = "";
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));

            // Iterate through the list and put data in .txt file
            for (Task task : alltasks.getListOfTask()) {
                if (task.getType().equals("[T]")) {
                    // Todo objects
                    line = "[T] " + task.getStatusIcon() + " " + task.getDesc();
                } else {
                    // Deadline/Event objects
                    line = task.getType() + " " + task.getStatusIcon() + " " +
                            task.getDesc() + " " + task.getDate();
                }

                writer.write(line);
                writer.newLine();
                line = "";
            }
            writer.close();
        } catch (IOException ioex) {
            ui.showErrorMessage(ioex.getMessage());
        }
    }

}
