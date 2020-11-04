import java.util.ArrayList;
import java.time.LocalDate;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Default class constructor. Used when there is no file of existing saved Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Used when there is a file of existing saved Tasks.
     * @param tasks List of existing saved Tasks, loaded by the Storage class.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // Getter methods ========================================================================================
    /**
     * Retrieves the size of the TaskList
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Gets the Task at the specified index.
     * @param index Index of the Task which you want.
     * @return The specified Task instance.
     */
    public Task get(int index) {
        return tasks.get(index);
    }


    // Adder methods =========================================================================================

    /**
     * Adds a new Todo task into the Task List.
     * @param description Description of the Todo task
     */
    public void addTodo(String description) {
        Todo new_todo = new Todo(description);
        tasks.add(new_todo);
    }

    /**
     * Adds a new Deadline task into the Task List.
     * @param description Description of the Deadline task
     * @param date Date object representing due date of the task
     */
    public void addDeadline(String description, LocalDate date) {
        Deadline new_deadline = new Deadline(description, date);
        tasks.add(new_deadline);
    }

    /**
     * Adds a new Event task into the Task List.
     * @param description Description of the Event task
     * @param date Date object representing due date of the event task
     */
    public void addEvent(String description, LocalDate date) {
        Event new_event = new Event(description, date);
        tasks.add(new_event);
    }

    // Deletion methods ======================================================================================

    /**
     * Deletes the Task at the specified index in the TaskList.
     * @param index Index of the specific Task we want to delete.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    // Other methods =========================================================================================

    /**
     * Finds the Tasks whose descriptions contain the keyword provided and prints them out.
     * @param keyword Keyword which we have to search for among the existing tasks.
     */
    public String findTask(String keyword) {
        int counter = 1;
        String lst = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < getTaskListSize(); i++) {
            if (get(i).getDescription().contains(keyword)) {
                String completion_status = get(i).getCompletionStatusAsString();
                String task_type = get(i).getTaskType();
                String description = get(i).getDescription();
                String line = completion_status + " | [" + task_type + "] | " + description + "\n";
                if (task_type.contains("E") || task_type.contains("D")) {
                    line = completion_status + " | [" + task_type + "] | " + description + " | " + get(i).getDate() + "\n";
                }
                line = counter + ". " + line;
                counter++;
                lst = lst + line;
            }
        }
        return lst;
    }

}
