import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;
    Ui ui = new Ui();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }

    /**
     * Adds task to tasklist.
     * @param taskType Type of task to be added.
     * @param taskDescription Description of task to be added.
     */
    public String add(String taskType, String taskDescription) {
        Task temp = new Task("random");
        if (taskType.equals("deadline")) {
            try {
                temp = new Deadline(taskDescription.split("/", 2));
                tasks.add(temp);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.descriptionError();
            }
        } else if (taskType.equals("event")) {
            try {
                temp = new Event(taskDescription.split("/", 2));
                tasks.add(temp);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.descriptionError();
            }
        } else {
            temp = new ToDo(taskDescription);
            tasks.add(temp);
        }
        String output = "Got it. I've added this task:\n";
        output = output + temp + "\n";
        output = "Now you have " + tasks.size() + " tasks in your list.\n";
        return output;
    }

    /**
     * Prints list of tasks.
     */
    public String list() {
        String output;
        int size = tasks.size();
        output = "Here are the tasks in your list:\n";
        for (int i = 0; i < size; ++i) {
            output = output + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return output;
    }


    /**
     * Finds tasks containing given string.
     *
     * @param str Substring to be found.
     */
    public String find(String str) {
        String output;
        int size = tasks.size();
        int cnt = 0;
        output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < size; ++i) {
            Task temp = tasks.get(i);
            if (temp.description.contains(str)) {
                output = output + ++cnt + ". " + temp + "\n";
            }
        }
        return output;
    }

    /**
     * Marks task as done.
     *
     * @param n Task to be marked as done.
     */
    public String done(int n) {
        String output;
        if (n > tasks.size()) {
            output = "There is no such task\n";
        } else {
            output = "Nice! I've marked this task as done:\n";
            tasks.get(n - 1).markAsDone();
            output = tasks.get(n - 1) + "\n";
        }
        return output;
    }

    /**
     * Task to be deleted.
     * @param n Task to be deleted.
     */
    public String delete(int n) {
        String output;
        if (n > tasks.size() || n < 1) {
            output = "There is no such task\n";
        } else {
            output = "Noted. I have removed this task:\n";
            output = output + tasks.get(n - 1) + "\n";
            tasks.remove(n - 1);
            output = output + "You now have " + tasks.size() + " tasks in the list.\n";
        }
        return output;
    }

}
