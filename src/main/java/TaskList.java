import java.util.ArrayList;

/**
 * Represents a TaskList that contains the task list.
 */
public class TaskList {

    private ArrayList<Task> record;

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
     * Method to get array list.
     * @return Array list
     */
    public ArrayList<Task> getRecord() {
        return record;
    }

    /**
     * Method to add to do task to list.
     * @param record description of to do task
     */
    public void addToDo(String record) {
        Task task = new ToDo(record);
        this.record.add(task);

        System.out.println("--------------------------------------------------");
        System.out.println("piaked in: " + record);
        System.out.println(" " + task);
        System.out.println("You has " + this.record.size() + " tasks in the list");
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Method to add deadline task to list.
     * @param record description of task
     * @param by deadline of task
     */
    public void addDeadline(String record, String by) {
        Task task = new Deadline(record, by);
        this.record.add(task);

        System.out.println("--------------------------------------------------");
        System.out.println("piaked in: " + record);
        System.out.println(" " + task);
        System.out.println("You has " + this.record.size() + " tasks in the list");
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Method to add event task to list.
     * @param record description of task
     * @param at location of task
     */
    public void addEvent(String record, String at) {
        Task task = new Event(record, at);
        this.record.add(task);

        System.out.println("--------------------------------------------------");
        System.out.println("piaked in: " + record);
        System.out.println(" " + task);
        System.out.println("You has " + this.record.size() + " tasks in the list");
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Method to list tasks added.
     */
    public void listRecord() {
        System.out.println("--------------------------------------------------");

        for(int i = 0; i < record.size(); i++) {
            int j = i + 1;
            Task current = record.get(i);
            System.out.print(j + "." + current + "\n");
        }

        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Method to delete task from list.
     * @param num number of the task to be deleted
     */
    public void delete(int num) {
        Task task = record.remove(num - 1);

        System.out.println("--------------------------------------------------");
        System.out.println("Destroyed: ");
        System.out.println(" " + task);
        System.out.println("You has " + this.record.size() + " tasks in the list");
        System.out.println("--------------------------------------------------\n");
    }

    /**
     * Method to set task as done.
     * @param num number of the task to be set to done
     */
    public void setDone(int num) {
        Task task = record.get(num - 1);

        task.setIsDone();

        System.out.println("--------------------------------------------------");
        System.out.println("Next time do yourself la! No paper meh?");
        System.out.println(task);
        System.out.println("--------------------------------------------------\n");
    }
}
