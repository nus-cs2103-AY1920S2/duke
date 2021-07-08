package duke.ui;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * The type Ui.
 */
public class Ui {

    /**
     * The Lines.
     */
    private static final String LINES = "        ________________________________________________________";
    /**
     * The Space.
     */
    private static final String SPACE = "        ";

    /**
     * Instantiates a new Ui.
     */
    public Ui() {
    }

    /**
     * Print bye.
     *
     * @return the string
     */
    public String printBye() {
        return LINES + System.lineSeparator() + "        Bye. Hope to see you again soon"
                + System.lineSeparator() + LINES;
    }


    /**
     * Print list.
     *
     * @param taskList the task list
     * @param storage  the storage
     * @return the string
     * @throws FileNotFoundException the file not found exception
     */
    public String printList(TaskList taskList, Storage storage) throws FileNotFoundException {

        String finalList = "";

        int numberOfTasks = storage.getNumberOfTasks();
        String storedItems = storage.getStoredItems();
        String[] storedArrays = storedItems.split(System.lineSeparator());

        finalList = formatFromStorage(storedArrays);

        return LINES + System.lineSeparator() + "There are " + numberOfTasks + " items in the list "
                + System.lineSeparator() + finalList + LINES + System.lineSeparator();
    }

    /**
     * Print found list from FindCommand.
     *
     * @param taskList    the task list
     * @param storageList the storage list
     * @return the string
     */
    public String printFoundList(TaskList taskList, ArrayList<String> storageList) {
        String finalList = "";
        String fromStorageList = storageList.stream().collect(Collectors.joining(System.lineSeparator()));
        String[] fromStorageLists = fromStorageList.split(System.lineSeparator());

        finalList = formatFromStorage(fromStorageLists);

        return LINES + System.lineSeparator() + finalList
                + taskList.print_elements() + LINES + System.lineSeparator();


    }

    /**
     * Invalid number exception.
     *
     * @throws DukeException the duke exception
     */
    // When the user enters a number which is larger than the taskList
    public void invalidNumberException() throws DukeException {
        throw new DukeException("You have entered an invalid number!");
    }

    /**
     * Invalid add task exception string.
     *
     * @return the string
     */
    public String invalidAddTaskException() {
        return "You have entered a wrong AddTaskCommand";
    }

    /**
     * Print tasks marked as done. Only used when reading tasks from .txt file
     *
     * @param storageElements the storage elements
     * @param storage         the storage
     * @param modifiedString  the modified string
     * @return the string
     * @throws IOException the io exception
     */
    public String printDone(ArrayList<String> storageElements,
                            Storage storage, String modifiedString) throws IOException {

        writeToFile(storageElements, storage);
        String markedAsDone = "";
        if (modifiedString.contains("1")) {
            markedAsDone = modifiedString.substring(0, 6) + getStatusIcon(true) + modifiedString.substring(7);
        } else {
            markedAsDone = modifiedString.substring(0, 6) + getStatusIcon(false) + modifiedString.substring(7);
        }
        return LINES + System.lineSeparator() + SPACE + "Nice! I've marked this task as done"
                + System.lineSeparator() + SPACE + markedAsDone
                + System.lineSeparator() + LINES + System.lineSeparator();
    }

    /**
     * Print tasks.
     *
     * @param task    the task
     * @param list    the list
     * @param storage the storage
     * @return the string
     * @throws FileNotFoundException the file not found exception
     */
    public String printTasks(Task task, ArrayList<Task> list, Storage storage) throws FileNotFoundException {
        return LINES + System.lineSeparator() + SPACE
                + " Got it. I've added this task: " + System.lineSeparator()
                + SPACE + task + System.lineSeparator() + SPACE + " Now you have "
                + storage.getNumberOfTasks() + " tasks in the list."
                + System.lineSeparator() + LINES;
    }

    /**
     * Print delete string.
     *
     * @param list        the list
     * @param storage     the storage
     * @param deletedTask the deleted task
     * @return the string
     * @throws IOException the io exception
     */
    public String printDelete(ArrayList<String> list, Storage storage, String deletedTask) throws IOException {

        writeToFile(list, storage);
        return SPACE + LINES + System.lineSeparator()
                + SPACE + "Noted. I've removed this task:" + System.lineSeparator() + SPACE + deletedTask
                + System.lineSeparator() + SPACE + "Now you have " + storage.getNumberOfTasks() + " tasks in the list."
                + System.lineSeparator() + LINES + System.lineSeparator();
    }


    /**
     * Read command string.
     *
     * @param command the command
     * @return the string
     * @throws DukeException the duke exception
     */
    // Reads the command
    public String readCommand(String command) throws DukeException {
        if (command.contains("todo") || command.contains("deadline")
                || command.contains("event")) {
            return "duke.command.AddCommand";
        } else if (command.contains("bye")) {
            return "duke.command.ByeCommand";
        } else if (command.contains("delete")) {
            return "duke.command.DeleteCommand";
        } else if (command.contains("list")) {
            return "duke.command.ListCommand";
        } else if (command.contains("done")) {
            return "duke.command.DoneCommand";
        } else if (command.contains("find")) {
            return "duke.command.FindCommand";
        } else {
            throw new DukeException("You have entered a wrong command");
        }
    }

    private void writeToFile(ArrayList<String> tasks, Storage storage) throws IOException {
        File file = new File(storage.getFilePath());
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(file);

        for (String s : tasks) {
            fw.write(s + System.lineSeparator());
        }
        fw.close();
    }

    private String getStatusIcon(boolean isDone) {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    private String formatFromStorage(String[] storedArrays) {
        String finalList = "";

        for (String s : storedArrays) {
            if (s.substring(6, 7).equals("1")) {
                finalList += s.substring(0, 6) + getStatusIcon(true) + s.substring(7) + System.lineSeparator();
            } else {
                finalList += s.substring(0, 6) + getStatusIcon(false) + s.substring(7) + System.lineSeparator();
            }
        }

        return finalList;
    }



}
