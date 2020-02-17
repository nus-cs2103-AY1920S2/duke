import java.util.ArrayList;

/**
 * Represent an Tasklist containing a list of Task objects.
 */
public class Tasklist {

    protected ArrayList<Task> mylist;

    /**
     * Constructor for Tasklist.
     * @param list An arraylist of Task
     */
    public Tasklist(ArrayList<Task> list) {
        this.mylist = list;
    }

    /**
     * Add a task into the tail of the list.
     * @param task a task to do
     */
    public void addTask(Task task) {
        mylist.add(task);
    }

    /**
     * Remove the task positioned in the index from the arraylist.
     * @param index the position
     */
    public void removeTask(int index) {
        mylist.remove(index - 1);
    }

    /**
     * Mark the task positioned in the index as done from the arraylist.
     * @param index the position
     */
    public void markDone(int index) {
        Task current = mylist.get(index - 1);
        current.markAsDone();
    }

    /**
     * Returns the task positioned in the index from the arraylist.
     * @param index the position
     * @return the selected task from the list
     */
    public Task getTask(int index) {
        return mylist.get(index - 1);
    }

    /**
     * Returns the size of arraylist.
     * @return int
     */
    public int getSize() {
        return mylist.size();
    }

    /**
     * Returns the arraylist.
     * @return arraylist
     */
    public ArrayList<Task> getList() {
        return mylist;
    }

    /**
     * Print the arraylist of task.
     * @return String of the list
     */
    public String printList() {
        String list = "";
        for (int i = 0; i < mylist.size(); i++) {
            list = list + (i + 1 + ". " + mylist.get(i) + "\n");
        }
        return list;
    }

    /**
     * Print a summary of task.
     * @return String of the summary
     */
    public String printStatistic() {
        String message = "";
        int todo = 0;
        int deadline = 0;
        int event = 0;
        int done = 0;
        for (Task current : mylist) {
            if (current instanceof Todo) {
                todo++;
            } else if (current instanceof Deadline) {
                deadline++;
            } else if (current instanceof Event) {
                event++;
            }
            if (current.isDone) {
                done++;
            }
        }
        message = message + String.format("___________SUMMARY__________\n");
        message = message + String.format("Currently you have\n");
        message = message + String.format("%d todo\n", todo);
        message = message + String.format("%d deadline\n", deadline);
        message = message + String.format("%d event\n", event);
        message = message + String.format("total task = %d, %d tasks done\n", mylist.size(), done);
        return message;
    }

    /**
     * Print the list of tasks with the keyword inside.
     * @param keyword String
     * @return String of the result
     */
    public String findKeyword(String keyword) {
        String result = "";
        int j = 1;
        for (int i = 0; i < mylist.size(); i++) {
            if (mylist.get(i).description.contains(keyword)) {
                result = result + (j + ". " + mylist.get(i) + "\n");
                j++;
            }
        }
        return result;
    }
}