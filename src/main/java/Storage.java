import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage class.
 * This class handles the data from a text file. It only has two methods : store and load.
 *
 * @author Amos Cheong
 */
public class Storage {

    public static String filePath;
    protected String home;
    protected Path parent;
    public Storage(String filePath) {
        this.parent = Paths.get(".", filePath).normalize().toAbsolutePath();
        this.filePath = this.parent.toString();

        try {
            Files.createDirectories((this.parent).getParent());
        } catch(IOException ioex) {
            ioex.printStackTrace();
        }

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
            String[] arr = currentline.split(" ", 3);

            String expression = arr[0];

            // For every data extracted from the .txt file
            // figure out the type of object and add it into
            // the list.
            switch (expression) {
            case "[E]":
                // Collect description and date/time separately
                int limit = arr[2].lastIndexOf("%");
                String desc = arr[2].substring(0, limit);
                String dateTime = arr[2].substring(limit + 1);

                // Put in Event Task
                Event eventTask = new Event(desc, dateTime);
                if (arr[1].equals("Y")) {
                    eventTask.taskIsDone();
                }
                Tasklist.add(eventTask);
                break;
            case "[D]":
                // Collect description and date/time separately
                int index = arr[2].lastIndexOf("%");
                String deadlineDesc = arr[2].substring(0, index);
                String deadlineDateTime = arr[2].substring(index + 1);

                // Put in Deadline Task
                Deadline deadlineTask = new Deadline(deadlineDesc, deadlineDateTime);
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
    public void store(TaskList alltasks, Ui ui) throws IOException {
        String line = "";
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath));

        // Iterate through the list and put data in .txt file
        for (Task task : alltasks.getListOfTask()) {
            if (task.getType().equals("[T]")) {
                // Todo objects
                line = "[T] " + task.getStatusIcon() + " " + task.getDesc();
            } else {
                // Deadline/Event objects
                // Add a "%" as a boundary between the description
                // and the date/time. Will be used to get the two separately.
                line = task.getType() + " " + task.getStatusIcon() + " " +
                        task.getDesc() + "%" + task.getDate();
            }

            writer.write(line);
            writer.newLine();
            line = "";
        }
        writer.close();

    }

    /**
     * Updates the txt file
     *
     * @param list List object that contains the list of tasks
     * @param ui A Ui object which has methods that return message
     *           after executing a specific command
     */
    public void updateFile(TaskList list, Ui ui) {
        try {
            this.store(list, ui);
        } catch (IOException ioex) {
            ui.showErrorMessage(ioex.getMessage());
        }
    }
}
