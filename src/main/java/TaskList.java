import CustomException.DukeException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents the task list in memory. A <code>TaskList</code> object corresponds to a task list
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<String> contents) throws DukeException {
        list = new ArrayList<>();
        load(contents);
    }

    /**
     * Parse all information read from the database into memory
     *
     * @param contents the array of Strings representing per line in the database
     * @throws DukeException if there's anything wrong with loading from the contents into memory
     */
    private void load(ArrayList<String> contents) throws DukeException {
        try {
            for (String str : contents) {
                char type = str.charAt(0);
                boolean isDone = Boolean.parseBoolean(str.substring(4, 5));
                String description;
                String byAt;

                // To Do
                if (type == 'T') {
                    description = str.substring(8);
                    list.add(new ToDo(description, isDone));
                } else { // Deadline or Event
                    int lastIndex = findThirdStrike(str) - 1;
                    description = str.substring(8, lastIndex);
                    byAt = str.substring(lastIndex + 3);

                    if (type == 'D') {
                        list.add(new Deadline(description, isDone, LocalDate.parse(byAt)));
                    } else if (type == 'E') {
                        list.add(new Event(description, isDone, LocalDate.parse(byAt)));
                    }
                }
            }
        } catch (Exception e) {
            throw new DukeException();
        }
    }

    /**
     * Writes back to the database
     *
     * @param storage class representing the storage database
     * @throws IOException if unable to write back
     */
    public void save(Storage storage) throws IOException {
        storage.save(list);
    }

    /**
     * Finds the index of the 3rd "|" in the database file
     *
     * @param str each line of input in the database
     * @return the index of the 3rd "|" in the database file
     */
    private int findThirdStrike(String str) {
        int count = 3;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '|') {
                count--;
            }

            if (count == 0) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Returns the string of the list of tasks
     *
     * @return the string of the list of tasks
     */
    public String displayGUI() {
        StringBuilder ret = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < list.size(); i++) {
            ret.append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
        }

        return ret.toString();
    }

    /**
     * Returns the string of the list of tasks sorted by certain order
     *
     * @param key indicator of what criteria to sort by
     * @return the string of the sorted list of tasks
     */
    public String displayGUI(char key) {
        if (key == 'n') {
            list.sort(new NameComp());
        } else if (key == 'd') {
            list.sort(new DateComp());
        }

        StringBuilder ret = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < list.size(); i++) {
            ret.append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
        }

        return ret.toString();
    }

    /**
     * Returns the matching lists of tasks when user searches by keyword
     *
     * @param indices array of indices of the tasks that should be displayed
     * @return the matching list of tasks when user searches by keyword
     */
    public String displayGUI(ArrayList<Integer> indices) {
        StringBuilder ret = new StringBuilder("Here are the matching tasks in your list:\n");

        for (int i = 0; i < indices.size(); i++) {
            ret.append(i + 1).append(". ").append(list.get(indices.get(i)).toString()).append("\n");
        }

        return ret.toString();
    }

    /**
     * Adds a new Todo task
     *
     * @param description description of the task
     * @param isDone whether the task is done or not
     */
    public void add(String description, boolean isDone) {
        list.add(new ToDo(description, isDone));
    }

    /**
     * Adds a new Deadline or Event task
     *
     * @param type indicator of whether is the task a Deadline or Event task
     * @param description description of the task
     * @param isDone whether the task is done or not
     * @param date date of the task (by or at)
     */
    public void add(char type, String description, boolean isDone, LocalDate date) {
        if (type == 'D') {
            list.add(new Deadline(description, isDone, date));
        } else if (type == 'E') {
            list.add(new Event(description, isDone, date));
        } else {
            System.out.println("Error: type must be 'D' or 'E'");
        }
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }

    public void markAsDone(int index) {
        list.get(index).markAsDone();
    }

    public Task delete(int index) {
        return list.remove(index);
    }

    /**
     * Finds all tasks that matches a given keyword and returns a String representation of all these tasks
     *
     * @param key the keyword the user searches for
     * @return a String of all the matching tasks
     */
    public String find(String key) {
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            String desc = list.get(i).getDescription();
            if (desc.contains(key)) {
                indices.add(i);
            }
        }

        return displayGUI(indices);
    }
}
