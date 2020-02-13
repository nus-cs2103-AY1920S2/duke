package duke;

// packages import
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;
import duke.ui.Ui;

// java imports
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Provides an interface between Duke chat bot and the save file.
 * Allows for saving to and retrieval from a save file.
 */
public class Storage {

    /** File name of save file. */
    private String saveFile = "save_file.txt";

    /**
     * Saves into saveFile.
     */
    public Storage() {
    }

    /**
     * Saves into file provided by path.
     *
     * @param path Relative path of save file.
     */
    public Storage(String path) {
        this.saveFile = path;
    }

    /**
     * Converts the task list into a convertible format and saves it into the save file.
     *
     * @param list Task list to be saved.
     * @throws IOException  When issues with input/output occurs.
     */
    public void save(TaskList list) throws IOException {
        FileWriter file = new FileWriter(saveFile, false);
        BufferedWriter writer = new BufferedWriter(file);

        String text = list.toSaveFormat();
        writer.write(text);
        writer.close();
    }

    /**
     * Converts text from save file back to respective tasks and adds them into task list.
     *
     * @param list Task list for adding tasks into.
     * @throws FileNotFoundException When file is not found.
     */
    public void readSaveFile(TaskList list) throws FileNotFoundException {
        FileReader file = new FileReader(saveFile);
        BufferedReader reader = new BufferedReader(file);

        try {
            String text = reader.readLine();

            while (text != null) {
                String[] fields = text.split(" \\| ");
                Task newTask;

                // Create corresponding specific task
                if (fields[0].equals("T")) {
                    newTask = new Todo(fields[2], tagFields(fields, 3));
                } else if (fields[0].equals("E")) {
                    newTask = new Event(fields[2], fields[3], fields[4], tagFields(fields, 5));
                } else {
                    newTask = new Deadline(fields[2], fields[3], fields[4],  tagFields(fields, 5));
                }

                // Set isDone status
                if (newTask != null && Integer.parseInt(fields[1]) == 1) {
                    newTask.markAsDone();
                }

                list.save(newTask);
                assert list.size() > 0: "At least one task should have been added";
                text = reader.readLine();
            }

            reader.close();
        } catch (IOException ex) {
            Ui.printOutput("Corrupted Task");
        }
    }

    private String[] tagFields(String[] fields, int fromIndex) {
        return Arrays.copyOfRange(fields, fromIndex, fields.length);
    }
}
