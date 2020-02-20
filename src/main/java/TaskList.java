import java.util.ArrayList;

/**
 * Represents a TaskList that contains the task list.
 */
public class TaskList {

    private ArrayList<Task> record;
    private Command latestCommand;

    /**
     * Constructor for TaskList object if there is no pre-made array list.
     */
    public TaskList() {
        ArrayList<Task> list = new ArrayList<>();

        this.record = list;
    }

    /**
     * Constructor for TaskList object if there is no pre-made array list.
     * @param record pre-made array list to be used to create TaskList object
     */
    public TaskList(ArrayList<Task> record) {
        this.record = record;
    }

    /**
     * Gets array list.
     * @return Array list
     */
    public ArrayList<Task> getRecord() {
        return record;
    }

    /**
     * Returns last called command.
     * @return last called command
     */
    public Command getLastCommand() {
        return latestCommand;
    }

    /**
     * Sets last command called.
     * @param command last command called
     */
    public void setLastCommand(Command command) {
        this.latestCommand = command;
    }

    /**
     * Adds to do task to list.
     * @param record description of to do task
     */
    public String addToDo(String record, int num) {
        boolean numSpecified = num != -1;

        Task task = new ToDo(record);

        if (numSpecified) {
            this.record.add(num, task);

        } else {
            this.record.add(task);

        }

        assert this.record.size() > 0 : "error";

        String output = "--------------------------------------------------\n"
                + "piaked in: " + record + "\n" + task + "\n"
                + "You has " + this.record.size() + " tasks in the list\n"
                + "--------------------------------------------------\n";

        return output;
    }

    /**
     * Adds deadline task to list.
     * @param record description of task
     * @param by deadline of task
     */
    public String addDeadline(String record, String by, int num) {
        boolean numSpecified = num != -1;

        Task task = new Deadline(record, by);

        if (numSpecified) {
            this.record.add(num, task);

        } else {
            this.record.add(task);

        }

        assert this.record.size() > 0 : "error";

        String output = "--------------------------------------------------\n"
                + "piaked in: " + record + "\n" + task + "\n"
                + "You has " + this.record.size() + " tasks in the list\n"
                + "--------------------------------------------------\n";

        return output;
    }

    /**
     * Adds event task to list.
     * @param record description of task
     * @param at location of task
     */
    public String addEvent(String record, String at, int num) {
        boolean numSpecified = num != -1;

        Task task = new Event(record, at);

        if (numSpecified) {
            this.record.add(num, task);

        } else {
            this.record.add(task);

        }

        assert this.record.size() > 0 : "error";

        String output = "--------------------------------------------------\n"
                + "piaked in: " + record + "\n" + task + "\n"
                + "You has " + this.record.size() + " tasks in the list\n"
                + "--------------------------------------------------\n";

        return output;
    }

    /**
     * Lists tasks added.
     */
    public String listRecord() {
        String str = "";
        String output = "--------------------------------------------------\n";

        for (int i = 0; i < record.size(); i++) {
            int j = i + 1;
            Task current = record.get(i);
            str = str + (j + "." + current + "\n");
        }

        assert this.record.size() >= 0 : "error";

        output = output + str + "--------------------------------------------------\n";

        return output;
    }

    /**
     * Deletes task from list using num to specify.
     * @param num number of the task to be deleted
     */
    public String delete(int num) {
        Task task = record.remove(num - 1);

        assert this.record.size() >= 0 : "error";

        String output = "--------------------------------------------------\n"
                + "Destroyed:\n" + " " + task + "\n"
                + "You has " + this.record.size() + " tasks in the list\n"
                + "--------------------------------------------------\n";

        return output;
    }


    /**
     * Sets task as done.
     * @param num number of the task to be set to done
     */
    public String setDone(int num) {
        Task task = record.get(num - 1);

        task.setIsDone();

        String output = "--------------------------------------------------\n"
                + "Next time do yourself la! No paper meh?\n" + task + "\n"
                + "--------------------------------------------------\n";

        return output;
    }

    /**
     * Sets task as done.
     * @param num number of the task to be set to done
     */
    public String setUnDone(int num) {
        Task task = record.get(num - 1);

        task.setUnDone();

        String output = "--------------------------------------------------\n"
                + "Next time do yourself la! No paper meh?\n" + task + "\n"
                + "--------------------------------------------------\n";

        return output;
    }

    /**
     * Finds tasks containing the description given.
     * @param description The keyword used to find tasks
     * @return A string containing the list of tasks found
     */
    public String find(String description) {
        String list = "";

        for (int i = 0; i < record.size(); i++) {
            Task current = record.get(i);

            if (current.getDescription().contains(description)) {
                list = list + (i + 1) + "." + current.toString() + "\n";
            } else {
                //do nothing
            }
        }

        String output = "--------------------------------------------------\n"
                + "Here are the matching tasks in your list: \n" + list
                + "--------------------------------------------------\n";

        return output;
    }
}
