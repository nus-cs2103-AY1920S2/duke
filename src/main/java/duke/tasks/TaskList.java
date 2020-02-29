package duke.tasks;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private Storage storage = new Storage();

    /**
     * Contructs a taskList object.
     * @param t arrayList of tasks
     */
    public TaskList(ArrayList<Task> t) throws IOException, DukeException {
        tasks = t;
    }

    /**
     * Executes instructions based on user input from CLI.
     * @param s String that is input by the user
     */
    public String run(String s) {
        try {
            if (s.equals("list")) {
                return list();
            } else if (s.length() > 5 && s.substring(0, 4).equals("done")) {
                return done(Integer.parseInt(s.substring(5)));
            } else if (s.length() > 6 && s.substring(0, 6).equals("delete")) {
                return delete(Integer.parseInt(s.substring(7)));
            } else if (s.length() > 4 && s.substring(0, 4).equals("find")) {
                return find(s);
            } else if (s.length() < 5 || !s.contains(" ") || s.stripTrailing().length() < 9) {
                return error(s);
            } else {
                return add(s);
            }
        } catch (DukeException e) {
            System.out.println(e.toString());
            return e.toString();
        } catch (StringIndexOutOfBoundsException e) {
            String outOfBoundsMsg = Ui.setBorder("idk what you're saying bud");
            System.out.println(outOfBoundsMsg);
            return outOfBoundsMsg;
        }
    }

    /**
     * Prints out current list of tasks.
     */
    public String list() {
        String outputList = Ui.LINE_DIVIDER + "Here are the tasks in your list:\n";
        int entryno = 1;
        for (Task ls : tasks) {
            outputList = outputList + entryno + ". " + ls + "\n";
            entryno++;
        }
        outputList = outputList + Ui.LINE_DIVIDER;
        System.out.println(outputList);
        return outputList;
    }

    /**
     * Adds a task to the current taskList.
     * @param s String that is input by the user
     */
    public String add(String s) {
        int whitespaceidx = s.indexOf(" ");
        String taskType = s.substring(0, whitespaceidx);
        String outputAdd = "";
        if (taskType.equals("todo")) {
            String theTask = s.substring(whitespaceidx + 1);
            Todo t = new Todo(theTask);

            tasks.add(t);
            outputAdd = Ui.LINE_DIVIDER + "Got it. I've added this task:\n " + t
                    + "\nNow you have " + tasks.size() + " tasks in the list.\n" + Ui.LINE_DIVIDER;
            System.out.println(outputAdd);
        } else {
            try {
                int taskIdx = s.indexOf("/");
                String theTask = s.substring(whitespaceidx + 1, taskIdx - 1);
                String date = s.substring(taskIdx + 1);
                if (taskType.equals("deadline")) {
                    Deadline t = new Deadline(theTask, date);
                    tasks.add(t);
                    outputAdd = Ui.LINE_DIVIDER + "Got it. I've added this task:\n " + t
                            + "\nNow you have " + tasks.size() + " tasks in the list.\n" + Ui.LINE_DIVIDER;
                    System.out.println(outputAdd);
                } else if (taskType.equals("event")) {
                    Event t = new Event(theTask, date);
                    tasks.add(t);
                    outputAdd = Ui.LINE_DIVIDER + "Got it. I've added this task:\n " + t
                            + "\nNow you have " + tasks.size() + " tasks in the list.\n" + Ui.LINE_DIVIDER;
                    System.out.println(outputAdd);
                }
            } catch (DukeException e) {
                System.out.println(e.toString());
                return e.toString();
            }
        }
        storage.save(tasks);
        return outputAdd;
    }

    /**
     * Marks a task as done.
     * @param n index of task in taskList to be marked as done
     */
    public String done(int n) throws DukeException {
        try {
            tasks.get(n - 1).makeDone();
            String outputDone = Ui.LINE_DIVIDER + "Nice! I've marked this task as done:\n" + tasks.get(n - 1)
                    + "\n" + Ui.LINE_DIVIDER;
            System.out.println(outputDone);
            storage.save(tasks);
            return outputDone;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I can't find that entry, bud");
        }
    }

    /**
     * Deletes a task from the current taskList.
     * @param n index of task to be deleted
     */
    public String delete(int n) throws DukeException {
        try {
            Task rm = tasks.remove(n - 1);
            String outputDelete = Ui.LINE_DIVIDER + "Noted. I've removed this task:\n" + rm + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.\n" + Ui.LINE_DIVIDER;
            System.out.println(outputDelete);
            storage.save(tasks);
            return outputDelete;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I can't find that entry, bud.");
        }
    }

    /**
     * Prints error messages for common errors based on invalid user input.
     * @param s String that is input by user
     */
    public String error(String s) {
        System.out.println(Ui.LINE_DIVIDER);
        String outputError = "";
        if (s.length() >= 4 && (s.stripTrailing().equals("todo")
                || s.stripTrailing().equals("deadline") || s.stripTrailing().equals("event"))) {
            if (s.stripTrailing().equals("todo")) {
                outputError = Ui.setBorder("BRUH the description of a todo cannot be empty.");
                System.out.println(outputError);
            } else if (s.stripTrailing().equals("deadline")) {
                outputError = Ui.setBorder("BRUH the description of a deadline cannot be empty.");
                System.out.println(outputError);
            } else if (s.stripTrailing().equals("event")) {
                outputError = Ui.setBorder("BRUH the description of an event cannot be empty.");
                System.out.println(outputError);
            }
        } else {
            outputError = Ui.setBorder("BRUH I only understand these commands:"
                    + "\nlist\ndelete\ndone\nfind\ntodo\ndeadline\nevent");
            System.out.println(outputError);
        }
        return outputError;
    }

    /**
     * finds matching task(s) in taskList given a keyword.
     * @param s keyword given to find matching taskList entries
     */
    public String find(String s) {
        String keyword = s.substring(5).strip();
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String outputFind = "";
        for (Task t : this.tasks) {
            if (t.getDescription().equals(keyword)) {
                matchingTasks.add(t);
            }
        }
        if (matchingTasks.isEmpty()) {
            outputFind = Ui.LINE_DIVIDER + "No matching tasks were found lol\n";
            System.out.println(outputFind + Ui.LINE_DIVIDER);
        } else {
            outputFind = Ui.LINE_DIVIDER + "Here are the matching tasks in your list:\n";
            System.out.println(outputFind);
            int entryno = 1;
            for (Task t : matchingTasks) {
                outputFind = outputFind + entryno + ". " + t + "\n";
                System.out.println(entryno + ". " + t);
                entryno++;
            }
        }
        outputFind = outputFind + Ui.LINE_DIVIDER;
        return outputFind;
    }

}
