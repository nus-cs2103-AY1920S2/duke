import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the object that stores the user's task list as a static ArrayList of Task objects.
 * Supports operations to manipulate it's taskList.
 */
public class TaskList {
    private static ArrayList<Task> taskList;

    /**
     * Constructor for a TaskList object that initializes an ArrayList of Tasks.
     * @param taskListString ArrayList of Strings representing the task Strings that are read
     *                       from the save file.
     */
    public TaskList(ArrayList<String> taskListString) {
        taskList = new ArrayList<>();
        for (String taskString: taskListString) {
            Task task = taskFromString(taskString);
            taskList.add(task);
        }
    }

    /**
     * Adds the specified task object to it's taskList.
     * @param task The Task object to be added to the taskList.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Marks the given task number in the task list as done.
     * @param taskNumber the task number to be marked as done.
     */
    public Task markTaskDone(int taskNumber) throws DukeException {
        try {
            Task taskMarked = taskList.get(taskNumber - 1);
            taskMarked.setDone();
            return taskMarked;
        } catch (IndexOutOfBoundsException e) {
            String errString = "\t Sorry! Could not find the specified task number in your task list!";
            throw new DukeException(errString);
        }
    }

    /**
     * Removes the specified task from the taskList.
     * @param taskNumber The index number of the task to be deleted from the taskList.
     * @return The Task that was deleted.
     * @throws DukeException Exception thrown if the task does not exist.
     */
    public Task deleteTask(int taskNumber) throws DukeException {
        try {
            Task taskRemoved = taskList.get(taskNumber - 1);
            taskList.remove(taskRemoved);
            return taskRemoved;
        } catch (IndexOutOfBoundsException e) {
            String errString = "\t Your task list is empty! You can't delete from an empty list!";
            throw new DukeException(errString);
        }
    }

    /**
     * Generates and returns a task given a task string that is read from the save file.
     * Returns null if the task string is not in a valid format.
     * @param taskString a single line task in a String format read from file
     * @return Task generated from a TaskString
     */
    private Task taskFromString(String taskString) {
        Task taskToReturn = null;
        String[] strArr = taskString.split(" ");
        ArrayList<String> temp = new ArrayList<>();
        Collections.addAll(temp, strArr);
        char type = temp.get(0).charAt(1);
        //check if the char is Done symbol
        boolean isDone = temp.get(0).charAt(4) == '\u2713';

        switch (type) {
        case 'T':
            // case for ToDo
            StringBuilder sbt = new StringBuilder();
            for (int i = 1; i < temp.size(); i++) {
                sbt.append(temp.get(i));
                if (i != (temp.size() - 1)) {
                    sbt.append(" ");
                }
            }
            String descT = sbt.toString();
            taskToReturn = new ToDo(descT);
            break;
        case 'D':
            // case for Deadline
            StringBuilder sbd = new StringBuilder();
            ArrayList<String> date = new ArrayList<>();
            for (int i = 1; i < temp.size(); i++) {
                if (i != (temp.size() - 1)) {
                    String curr = temp.get(i);
                    if (curr.equals("by:")) {
                        //if equals to colon, replace it with /, don't add space behind
                        sbd.append("/by ");
                    } else {
                        if (i >= (temp.size() - 3)) {
                            date.add(temp.get(i));
                        } else {
                            sbd.append(curr);
                            sbd.append(" ");
                        }
                    }
                } else {
                    // Reached the end of the temp array, means also that date array is complete
                    date.add(temp.get(i));
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < date.size(); j++) {
                        sb.append(date.get(j));
                        if (j != (date.size() - 1)) {
                            sb.append(" ");
                        }
                    }
                    String dateString = sb.toString();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                    LocalDate d = LocalDate.parse(dateString, formatter);
                    sbd.append(d.format(DateTimeFormatter.ofPattern("yyyy-MM-d")));
                }
            }
            String descD =  sbd.toString();
            taskToReturn = new Deadline(descD);
            break;
        case 'E':
            // case for Deadline
            StringBuilder sbe = new StringBuilder();
            ArrayList<String> day = new ArrayList<>();
            for (int i = 1; i < temp.size(); i++) {
                if (i != (temp.size() - 1)) {
                    String curr = temp.get(i);
                    if (curr.equals("at:")) {
                        //if equals to colon, replace it with /, don't add space behind
                        sbe.append("/at ");
                    } else {
                        if (i >= (temp.size() - 3)) {
                            day.add(temp.get(i));
                        } else {
                            sbe.append(curr);
                            sbe.append(" ");
                        }
                    }
                } else {
                    // Reached the end of the temp array, means also that date array is complete
                    day.add(temp.get(i));
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < day.size(); j++) {
                        sb.append(day.get(j));
                        if (j != (day.size() - 1)) {
                            sb.append(" ");
                        }
                    }
                    String dateString = sb.toString();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                    LocalDate d = LocalDate.parse(dateString, formatter);
                    sbe.append(d.format(DateTimeFormatter.ofPattern("yyyy-MM-d")));
                }
            }
            String descE =  sbe.toString();
            taskToReturn = new Event(descE);
            break;
        default:
            break;

        }

        if (isDone) {
            assert taskToReturn != null;
            taskToReturn.setDone();
        }
        return taskToReturn;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
