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
     * Prints the arraylist of task.
     */
    public void printList() {
        for (int i = 0; i < mylist.size(); i++) {
            System.out.println(i + 1 + ". " + mylist.get(i));
        }
    }

    /**
     * Print the list of tasks with the keyword inside.
     * @param keyword String
     */
    public void findKeyword(String keyword) {
        int j = 1;
        for (int i = 0; i < mylist.size(); i++) {
            if (mylist.get(i).description.contains(keyword)) {
                System.out.println(j + ". " + mylist.get(i));
                j++;
            }
        }
    }
}