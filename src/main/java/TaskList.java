/**
 * The TaskList object handles all interaction between the user and the
 * array of tasks. It contains several methods that will adjust the list
 * depending on user input.
 */

import java.util.*;
import java.io.*;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * This method prints out the entire list in the given format.
     * */
    public void printList() {
        System.out.print("____________________________________________________________\n" +
                "Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.print("____________________________________________________________\n");
    }

    /**
     * This method takes in an index and marks the task in the ArrayList
     * with the corresponding index as 'done'
     * @param index Takes in an integer that and marks the corresponding task in
     *              the TaskList as 'done'.
     * */
    public void markTaskDone(int index) {
        tasks.get(index).markAsDone();
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n" + tasks.get(index).toString()
                + "\n____________________________________________________________\n");
    }

    /**
     * Takes in a task and appends it to the TaskList.
     * @param task adds the task to the back of the list.
     * */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the size of the list
     * */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the Task Object of the corresponding index in the TaskList
     * @param index is the index of the task in the TaskList
     * */
    public Task getIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task in the list according to the corresponding index in the TaskList
     * @param index is the index of the task in the TaskList
     * */
    public Task removeTask(int index) {
        Task task = tasks.remove(index);
        return task;
    }

    // Empty Constructor
    public TaskList() {}

    /**
     * Default Constructor.
     * @param f is a file object with the filepath of the saved data
     *          and reads it into the tasks array.
     * */
    public TaskList(File f) throws DukeException {
        try {
            readFile(f);
        }
        catch (Exception e){
            throw new DukeException("No file found");
        }
    }

    private void readFile(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String input = s.nextLine();
            char c = input.charAt(0);
            if (c == 'T') {
                String description = input.split("/")[2];
                boolean isDone;
                if (input.split("/")[1].equals(" O ")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                Task t = new Todo(description, isDone);
                tasks.add(t);
            } else if (c == 'D') {
                String description = input.split("/")[2];
                boolean isDone;
                if (input.split("/")[1].equals(" O ")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                String deadline = input.split("/")[3];
                Task t = new Deadline(description, isDone, deadline);
                tasks.add(t);
            } else if (c == 'E') {
                String description = input.split("/")[2];
                boolean isDone;
                if (input.split("/")[1].equals(" O ")) {
                    isDone = true;
                } else {
                    isDone = false;
                }
                String deadline = input.split("/")[3];
                Task t = new Event(description, isDone, deadline);
                tasks.add(t);
            }
        }
    }
}
