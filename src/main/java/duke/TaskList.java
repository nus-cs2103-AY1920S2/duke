package duke;

import java.util.ArrayList;

/**
 * Represents the TaskList object that stores and retrieves the tasks to/from the hard drive.
 */
public class TaskList {

    private ArrayList<Task> taskArrayList;
    private Ui ui = new Ui();

    /**
     * Creates a Tasklist object with given ArrayList of Tasks.
     *
     * @param tasks is an arraylist of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskArrayList = tasks;
    }

    /**
     * Creates a TaskList object with an empty tasks ArrayList.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * Gets a Task object at the specified index in the Task List.
     *
     * @param index of the task in the list
     * @return a Task object
     */
    public Task getTaskByIndex(int index) {
        return this.taskArrayList.get(
                index - 1); // -1 because indexing for user starts from 1, but 0 for arraylist.
    }

    /**
     * Adds task to the tasklist.
     *
     * @param type        of the task to be added
     * @param description of the task to be added
     * @return a string message of confirmation
     */
    public String addTask(String type, String description) {
        Task task = new Task("sample");
        String output = "";
        if (type.equals(Duke.EVENT)) {
            try {
                String[] args = description.split("/", 2);
                task = new Event(args[0], args[1]);
                this.taskArrayList.add(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.timeErrorMessage();
            }
        } else if (type.equals(Duke.DEADLINE)) {
            try {
                String[] args = description.split("/", 2);
                task = new Deadline(args[0], args[1]);
                this.taskArrayList.add(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.timeErrorMessage();
            }
        } else if (type.equals(Duke.TODO)) {
            task = new ToDo(description);
            taskArrayList.add(task);
        } else {
            return "Incorrect task added";
        }
        output += "Got it. I've added this task:" + "\n";
        output += task.toString() + "\n";
        output += "Now you have " + taskArrayList.size() + " tasks in your list." + "\n";
        return output;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskArrayList;
    }

    /**
     * Marks a task as done and informs the user about the same.
     */
    public String markDone(int index) {
        if (index > taskArrayList.size() || index < 0) {
            return "There is no such task in the list. Please try again...";
        } else {
            String output = "Nice! I've marked this task as done: " + "\n";
            taskArrayList.get(index - 1).markAsDone();
            output += taskArrayList.get(index - 1).toString() + "\n";
            return output;
        }
    }

    /**
     * Lists all the currently stored tasks on the system output.
     */
    public String printList() {
        int n = taskArrayList.size();
        String output = "";
        output += "Here are the tasks in your list:" + "\n";
        for (int i = 0; i < n; i++) {
            String stringNew = String.format("%d. %s", i + 1, this.taskArrayList.get(i));
            output += stringNew + "\n";
        }
        return output;
    }

    /**
     * Finds all matching cases of a string and prints those cases to console.
     *
     * @param input keyword
     */
    public String find(String input) {
        int n = taskArrayList.size();
        String concat = "";
        int matches = 1;
        for (int i = 0; i < n; i++) {
            if (taskArrayList.get(i).description.contains(input)) {
                String representation = String.format("%d. %s", matches, this.taskArrayList.get(i));
                concat += representation + "\n";
                matches++;
            }
        }
        if (matches > 1) {
            concat = "Here are the matching tasks in your list:" + concat;
        } else {
            concat = "No tasks with given search keyword found. Please try again";
        }
        return concat;
    }

    /**
     * Deletes the task at specified index if it exists. Otherwise displays error message to user.
     */
    public String deleteTaskByIndex(int index) {
        if (index > taskArrayList.size() || index < 0) {
            return "There is no such task in the list. Please try again...";
        } else {
            Task deleted = taskArrayList.get(index - 1);
            this.taskArrayList.remove(index - 1);
            String output = "Noted. I've removed this task:" + "\n";
            output += deleted.toString() + "\n";
            output += String.format("Now you have %d tasks in your list", taskArrayList.size()) + "\n";
            return output;
        }
    }
}
