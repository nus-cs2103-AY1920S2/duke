import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    /**
     * Initialises the TaskList according to a list of tasks that have been saved
     * @param savedTasks Tasks that have been saved represented as a List<List<String>>
     */
    public TaskList(List<List<String>> savedTasks) {
        tasks = new ArrayList<>();
        for (List<String> savedTask : savedTasks) {
            String type = savedTask.get(0);
            Task taskObject;
            if (type.equals("D")) {
                taskObject = new Deadline(savedTask.get(2), savedTask.get(3));
            } else if (type.equals("E")) {
                taskObject = new Event(savedTask.get(2), savedTask.get(3));
            } else {
                // type equals ("T")
                taskObject = new Todo(savedTask.get(2));
            }
            tasks.add(taskObject);

            if (savedTask.get(1).equals("1")) {
                // That means task was initially done
                taskObject.markAsDone();
            }
        }
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets the concrete representation of the tasks
     * @return A List of Tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a deadline object to the task list
     * @param args A String in the format of: "<desc> /by <date>"
     */
    public void addDeadline(String args) {
        String[] descAndBy = args.split(" /by ");
        Deadline deadline = new Deadline(descAndBy[0], descAndBy[1]);
        tasks.add(deadline);

        Ui.printLine();
        Ui.indent("Acknowledged. I have added: ");
        Ui.doubleIndent( deadline.toString());
        Ui.printTaskCount(this);
        Ui.printLine();
    }

    /**
     * Adds an Event object to the task list
     * @param args A String in the format of: "<desc> /at <date>"
     */
    public void addEvent(String args) {
        String[] descAndAt = args.split(" /at ");
        Event event = new Event(descAndAt[0], descAndAt[1]);
        tasks.add(event);

        Ui.printLine();
        Ui.indent("Acknowledged. I have added: ");
        Ui.doubleIndent(event.toString());
        Ui.printTaskCount(this);
        Ui.printLine();
    }

    /**
     * Add a Todo object to the task list
     * @param args A String in the format of: "<desc>"
     */
    public void addTodo(String args) {
        Todo todo = new Todo(args);
        tasks.add(todo);
        Ui.printLine();
        Ui.indent("Acknowledged. I have added: ");
        Ui.doubleIndent(todo.toString());
        Ui.printTaskCount(this);
        Ui.printLine();
    }

    /**
     * The number of tasks in the TaskList
     * @return The number of tasks in the TaskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * The Task at a the index specified by the param
     * @param idx 0-based indexing to get the Task object
     * @return
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Mark a Task (specified by the index) as done
     * @param taskNo A 1-based indexing of the position of the Task in the list
     * @throws InvalidIndexException
     */
    public void markTaskAsDone(int taskNo) throws InvalidIndexException {
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(taskNo - 1); // The user starts counting from 1
        task.markAsDone();

        Ui.printLine();
        Ui.indent("Excellent! You have completed this task: ");
        Ui.doubleIndent(task.toString());
        Ui.printLine();
    }

    /**
     * Delete a Task (specified by the index)
     * @param taskNo A 1-based indexing of the position of the Task in the list
     * @throws InvalidIndexException
     */
    public void deleteTask(int taskNo) throws InvalidIndexException {
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(taskNo - 1); // The user starts counting from 1
        tasks.remove(taskNo - 1);

        Ui.printLine();
        Ui.indent("Understood. I have now removed this task: ");
        Ui.doubleIndent(task.toString());
        Ui.printTaskCount(this);
        Ui.printLine();
    }

    /**
     * Checks if the TaskList is full (full is defined by 100 tasks)
     * @return true if TaskList is full
     */
    public boolean isFull() {
        return tasks.size() == 100;
    }

}
