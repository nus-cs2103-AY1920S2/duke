import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Task list that stores all tasks specified by user.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();;
    }

    /**
     * Creates a task list.
     * @param list list of tasks to be stored in the task list.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds task to the task list.
     * @param task task to be added to the task list.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Returns task in task list requested by user.
     * @param index index position of task in the task list.
     * @return Task requested by user.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Return formatted string that contains all tasks in the task list.
     * @return formatted string message containing all tasks in task list.
     */
    public String saveList() {
        String toSave = "";
        for (int i = 0; i < list.size(); i++) {
            toSave += list.get(i).toPrint();
            toSave += "\n";
        }
        return toSave;
    }

    /**
     * Returns number of tasks in the task list.
     * @return integer representing number of tasks in task list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Prints all tasks in the task list.
     */
    public void printList() {
        if (list.size() <= 0) {
            System.out.println("     There are no tasks in your list.");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("     " + (i + 1) + ". " + list.get(i));
            }
        }
    }

    /**
     * Returns formatted list of all tasks in the task list.
     * @return string indicating completion of the list command.
     */
    public String printListString() {
        String printString = "";
        if (list.size() <= 0) {
            printString += "There are no tasks in your list.\n";
        } else {
            printString += "Here are the tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                printString += (i + 1) + ". " + list.get(i) + "\n";
            }
        }
        return printString;
    }

    /**
     * Prints the number of tasks present in task list.
     */
    public void printSize() {
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Returns a string indicating size of task list.
     * @return string containing size of task list.
     */
    public String printSizeString() {
        return "Now you have " + list.size() + " tasks in the list.";
    }

    /**
     * Marks task in task list as completed.
     * @param index index position of task in task list to be marked as completed.
     * @param storage makeshift database for tasks.
     * @return string indicating completion of the done command.
     * @throws IOException named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be open for any other reason.
     */
    public String done(int index, Storage storage) throws IOException {
        list.get(index).markDone();
        storage.writeToFile(saveList());
        String doneResult = "Nice! I've marked this task as done:\n"
                + list.get(index);
        return doneResult;
    }

    /**
     * Deletes task from task list.
     * @param index index position of task to be deleted from task list.
     * @param storage makeshift database for tasks.
     * @return string indicating completion of the delete command.
     * @throws IOException named file exists but is a directory rather than a regular file,
     *     does not exist but cannot be created, or cannot be open for any other reason.
     * @throws DukeException if user input does not follow input format.
     */
    public String delete(int index, Storage storage) throws IOException, DukeException {
        if (size() <= index) {
            throw new DukeException("☹ OOPS!!! There is no task " + (index + 1) + ".");
        }
        String deleteResult = "";
        deleteResult += "Noted. I've removed this task:\n";
        deleteResult += list.get(index) + "\n";
        list.remove(index);
        storage.writeToFile(saveList());
        return deleteResult;

    }

    /**
     * Updates task with new description.
     * @param index index position of task to be updated.
     * @param newDescription new description of tasks.
     * @param storage makeshift database for tasks.
     * @return string indicating completion of the update command.
     * @throws DukeException named file exists but is a directory rather than a regular file,
     *      does not exist but cannot be created, or cannot be open for any other reason.
     * @throws IOException if user input does not follow input format.
     */
    public String update(int index, String newDescription, Storage storage) throws DukeException, IOException {
        if (size() <= index) {
            throw new DukeException("☹ OOPS!!! There is no task " + (index + 1) + ".");
        }
        String updateResult = "";
        list.get(index).update(newDescription);
        updateResult += "Noted. I've updated this task:\n";
        updateResult += list.get(index) + "\n";
        storage.writeToFile(saveList());
        return updateResult;
    }

    /**
     * Searches and returns a list of tasks containing a keyword.
     * @param keyword keyword specified by user.
     * @return string indicating completion of the find command.
     */
    public String find(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains(keyword)) {
                matchedTasks.add(list.get(i));
            }
        }

        String findResult = "";
        if (matchedTasks.isEmpty()) {
            findResult += "There are no matching tasks in your list.\n";
        } else {
            findResult += "Here are the matching tasks in your list:\n";
            for (int i = 0; i < matchedTasks.size(); i++) {
                findResult += (i + 1) + ". " + matchedTasks.get(i) + "\n";
            }
        }
        return findResult;

    }
}