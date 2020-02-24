package liaomeng.duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The class that saves data into an external file, and load and convert data from it.
 */
public class Storage {
    private String path;
    private Ui ui;

    /**
     * Creates an instance of storage handler.
     *
     * @param filePath path where the data file is written into and read from.
     * @param ui ui component of Duke
     */
    public Storage(String filePath, Ui ui) {
        path = filePath;
        this.ui = ui;
    }

    /**
     * Reads the external data file and converts the data back to a list of Tasks.
     *
     * @throws DukeException exception indicating that IOException occurs when trying to read the file.
     */
    public List<Task> load() throws DukeException {
        try {
            List<Task> list = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                addTasksToList(list, line);
            }
            return list;
        } catch (IOException e) {
            throw new DukeException("Cannot find or read the file properly.");
        }
    }

    /**
     * Converts a line stored in the data file back into a Task and add all the task into the list of Tasks.
     *
     * @param list the list of Tasks.
     * @param line a line stored in the data file, which should a simple String representation of a Task.
     */
    private static void addTasksToList(List<Task> list, String line) {
        String[] lineByWord = line.split("//");
        boolean isDone;
        isDone = lineByWord[1].equals("T");
        PriorityLevel level;
        switch (lineByWord[2]) {
        case "t":
            level = PriorityLevel.TOP;
            break;
        case "h":
            level = PriorityLevel.HIGH;
            break;
        case "l":
            level = PriorityLevel.LOW;
            break;
        default:
            level = PriorityLevel.NORMAL;
        }
        switch (lineByWord[0]) {
        case "T":
            assert lineByWord.length == 4 : "A correct line description should contain 4 parts separated by \"//\"";
            list.add(new Todo(isDone, lineByWord[3], level));
            break;
        case "D":
            assert lineByWord.length == 5 : "A correct line description should contain 5 parts separated by \"//\"";
            list.add(new Deadline(isDone, lineByWord[3], LocalDate.parse(lineByWord[4]), level));
            break;
        case "E":
            assert lineByWord.length == 5 : "A correct line description should contain 5 parts separated by \"//\"";
            list.add(new Event(isDone, lineByWord[3], LocalDate.parse(lineByWord[4]), level));
            break;
        default:
            break;
        }
    }

    /**
     * Converts all the tasks contained in the list to simple String representations,
     * and write these Strings into the data file. The file is cleared before it is written.
     *
     * @param list the list of Tasks
     * @throws DukeException exception indicating that IOException occurs when trying to write into the file.
     */
    public void writeToFile(List<Task> list) throws DukeException {
        try {
            FileWriter writer = new FileWriter("NUS-Duke.txt");
            for (Task t : list) {
                writer.write(t.toSimplerString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            ui.throwIoException();
        }

    }
}
