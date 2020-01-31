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
            String[] arr = currentline.split(" ", 3);

            String expression = arr[0];

            // For every data extracted from the .txt file
            // figure out the type of object and add it into
            // the list.
            switch (expression) {
            case "[E]":
                Tasklist.add(new Event(arr[1], arr[2]));
                break;
            case "[D]":
                Tasklist.add(new Deadline(arr[1], arr[2]));
                break;
            case "[T]":
                Tasklist.add(new Todo(arr[1]));
                break;
            }

            currentline = reader.readLine();
        }
        reader.close();

        return Tasklist;
    }

    /**
     * After the user exits, the remaining tasks in the list will be kept inside a .txt file.
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
                    line = "[T] " + task.getDesc();
                } else {
                    // Deadline/Event objects
                    line = task.getType() + " " + task.getDesc() + " " + task.getDate();
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
