package duke.tasks;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.util.Storage;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private String linedivider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    private Storage storage = new Storage();

    /**
     * Contructs a taskList object.
     * @param t arrayList of tasks
     */
    public TaskList(ArrayList<Task> t) {
        tasks = t;
    }

    /**
     * Executes instructions based on user input from CLI.
     * @param s String that is input by the user
     */
    public void run(String s) {
        if (s.equals("list")) {
            list();
        } else if (s.length() > 5 && s.substring(0,4).equals("done")) {
            done(Integer.parseInt(s.substring(5)));
        } else if (s.length() > 6 && s.substring(0,6).equals("delete")) {
            delete(Integer.parseInt(s.substring(7)));
        } else if (s.length() > 4 && s.substring(0,4).equals("find")) {
            find(s);
        } else if (s.length() < 5 || !s.contains(" ") || s.stripTrailing().length() < 9) {
            error(s);
        } else {
            add(s);
        }
    }

    /**
     * Prints out current list of tasks.
     */
    public void list() {
        System.out.println(linedivider + "Here are the tasks in your list:");
        int entryno = 1;
        for (Task ls : tasks) {
            System.out.println(entryno + ". " + ls);
            entryno++;
        }
        System.out.println(linedivider);
    }

    /**
     * Adds a task to the current taskList.
     * @param s String that is input by the user
     */
    public void add(String s) {
        int whitespaceidx = s.indexOf(" ");
        String taskType = s.substring(0, whitespaceidx);
        if (taskType.equals("todo")) {
            String theTask = s.substring(whitespaceidx + 1);
            Todo t = new Todo(theTask);
            tasks.add(t);
            System.out.println(linedivider + "Got it. I've added this task:\n " + t
                    + "\nNow you have " + tasks.size() + " tasks in the list.\n" + linedivider);
        } else {
            int taskIdx = s.indexOf("/");
            String theTask = s.substring(whitespaceidx + 1, taskIdx - 1);
            String date = s.substring(taskIdx + 1);
            if (taskType.equals("deadline")) {
                Deadline t = new Deadline(theTask, date);
                tasks.add(t);
                System.out.println(linedivider + "Got it. I've added this task:\n " + t
                        + "\nNow you have " + tasks.size() + " tasks in the list.\n" + linedivider);
            } else if (taskType.equals("event")) {
                Event t = new Event(theTask, date);
                tasks.add(t);
                System.out.println(linedivider + "Got it. I've added this task:\n " + t
                        + "\nNow you have " + tasks.size() + " tasks in the list.\n" + linedivider);
            }

        }
        storage.save(tasks);
    }

    /**
     * Marks a task as done.
     * @param n index of task in taskList to be marked as done
     */
    public void done(int n) {
        tasks.get(n - 1).makeDone();
        System.out.println(linedivider + "Nice! I've marked this task as done:\n" + tasks.get(n - 1)
                + "\n" + linedivider);
        storage.save(tasks);
    }

    /**
     * Deletes a task from the current taskList.
     * @param n index of task to be deleted
     */
    public void delete(int n) {
        Task rm = tasks.remove(n - 1);
        System.out.println(linedivider + "Noted. I've removed this task:\n" + rm + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.\n" + linedivider);
        storage.save(tasks);
    }

    /**
     * Prints error messages for common errors based on invalid user input.
     * @param s String that is input by user
     */
    public void error(String s) {
        System.out.println(linedivider);
        if (s.length() >= 4 && (s.stripTrailing().equals("todo")
                || s.stripTrailing().equals("deadline") || s.stripTrailing().equals("event"))) {
            if (s.stripTrailing().equals("todo")) {
                System.out.println(":( OOPS!!! The description of a todo cannot be empty.");
            } else if (s.stripTrailing().equals("deadline")) {
                System.out.println(":( OOPS!!! The description of a deadline cannot be empty.");
            } else if (s.stripTrailing().equals("event")) {
                System.out.println(":( OOPS!!! The description of an event cannot be empty.");
            }
        } else {
            System.out.println(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        System.out.println(linedivider);
    }

    public void find(String s) {
        String keyword = s.substring(5).strip();
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task t : this.tasks) {
            if (t.getDescription().equals(keyword)) {
                matchingTasks.add(t);
            }
        }
        if (matchingTasks.isEmpty()) {
            System.out.println(linedivider + "No matching tasks were found lol\n" + linedivider);
        } else {
            System.out.println(linedivider + "Here are the matching tasks in your list:");
            int entryno = 1;
            for (Task t : matchingTasks) {
                System.out.println(entryno + ". " + t);
                entryno++;
            }
            System.out.println(linedivider);
        }
    }

}
