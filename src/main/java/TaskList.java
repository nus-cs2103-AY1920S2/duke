import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Deals with the functionality of the task list.
 */
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
     *
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
        return addMessage(temp);
    }

    /**
     * Creates output message when you add to tasklist.
     *
     * @param addedTask Task that was added to the tasklist.
     * @return message once task has been added.
     */
    public String addMessage(Task addedTask) {
        String output = "Got it. I've added this task:\n" + addedTask + "\n";
        output = output + "Now you have " + tasks.size() + " tasks in your list.\n";
        return output;
    }

    /**
     * Prints list of tasks.
     */
    public String list() {
        String output;
        int size = tasks.size();
        if (size == 0) {
            output = "There is nothing in your list.\n";
        } else {
            output = "Here are the tasks in your list:\n";
            assert size > 0 : "no tasks in list";
            for (int i = 0; i < size; ++i) {
                output = output + (i + 1) + ". " + tasks.get(i) + "\n";
            }
        }
        return output;
    }

    /**
     * Sorts the task list alphabetically.
     *
     * @return message to indicate successful sorting.
     */
    public String sortAlphabetically() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.description.compareTo(o2.description);
            }
        });
        String output = "I have sorted the tasks alphabetically.\n";
        output = output + "You can use the list command to view it.";
        return output;
    }

    /**
     * Sorts the task list chronologically.
     *
     * @return message to indicate successful sorting.
     */
    public String sortChronologically() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (o1 instanceof Deadline) {
                    if (o2 instanceof ToDo) {
                        return -1;
                    } else if (o2 instanceof Event) {
                        return ((Deadline) o1).due.compareTo(((Event) o2).at);
                    } else {
                        return ((Deadline) o1).due.compareTo(((Deadline) o2).due);
                    }
                } else if (o1 instanceof ToDo) {
                    if (o2 instanceof ToDo) {
                        return o1.description.compareTo(o2.description);
                    } else if (o2 instanceof Event) {
                        return 1;
                    } else {
                        return 1;
                    }
                } else {
                    if (o2 instanceof ToDo) {
                        return -1;
                    } else if (o2 instanceof Event) {
                        return ((Event) o1).at.compareTo(((Event) o2).at);
                    } else {
                        return ((Event) o1).at.compareTo(((Deadline) o2).due);
                    }
                }
            }
        });
        String output = "I have sorted the tasks chronologically.\n";
        output = output + "You can use the list command to view it.";
        return output;
    }


    /**
     * Finds tasks containing given string.
     *
     * @param str Substring to be found.
     */
    public String findAndPrint(String str) {
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
        if (cnt == 0) {
            output = "There are no matching items in your list.\n";
        }
        return output;
    }

    /**
     * Marks task as done.
     *
     * @param n Task to be marked as done.
     */
    public String markAsDoneAndPrint(int n) {
        String output;
        if (n > tasks.size() || n <= 0) {
            output = "There is no such task!\n";
        } else {
            assert n > 0 : "Number out of bounds";
            output = "Nice! I've marked this task as done:\n";
            tasks.get(n - 1).markAsDone();
            output = output + tasks.get(n - 1) + "\n";
        }
        return output;
    }

    /**
     * Task to be deleted.
     *
     * @param n Task to be deleted.
     */
    public String deleteAndPrint(int n) {
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
