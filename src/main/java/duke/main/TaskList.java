package duke.main;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.exception.InvalidIndexException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    /**
     * Initialises the duke.main.TaskList according to a list of tasks that have been saved
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
    public String addDeadline(String args) {
        String[] descAndBy = args.split(" /by ");
        Deadline deadline = new Deadline(descAndBy[0], descAndBy[1]);
        tasks.add(deadline);

        String toReturn = "";
        toReturn += Ui.indentWithNewline("Acknowledge. I have added: ", 1);
        toReturn += Ui.indentWithNewline(deadline.toString(), 2);
        toReturn += Ui.getTaskCount(this);
        return toReturn;
    }

    /**
     * Adds an duke.task.Event object to the task list
     * @param args A String in the format of: "<desc> /at <date>"
     */
    public String addEvent(String args) {
        String[] descAndAt = args.split(" /at ");
        Event event = new Event(descAndAt[0], descAndAt[1]);
        tasks.add(event);

        String toReturn = "";
        toReturn += Ui.indentWithNewline("Acknowledge. I have added: ", 1);
        toReturn += Ui.indentWithNewline(event.toString(), 2);
        toReturn += Ui.getTaskCount(this);
        return toReturn;
    }

    /**
     * Add a duke.task.Todo object to the task list
     * @param args A String in the format of: "<desc>"
     */
    public String addTodo(String args) {
        Todo todo = new Todo(args);
        tasks.add(todo);

        String toReturn = "";
        toReturn += Ui.indentWithNewline("Acknowledge. I have added: ", 1);
        toReturn += Ui.indentWithNewline(todo.toString(), 2);
        toReturn += Ui.getTaskCount(this);
        return toReturn;
    }

    private void add(Task task) {
        tasks.add(task);
    }

    /**
     * The number of tasks in the duke.main.TaskList
     * @return The number of tasks in the duke.main.TaskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * The duke.task.Task at a the index specified by the param
     * @param idx 0-based indexing to get the duke.task.Task object
     * @return
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Mark a duke.task.Task (specified by the index) as done
     * @param taskNo A 1-based indexing of the position of the duke.task.Task in the list
     * @throws InvalidIndexException
     */
    public String markTaskAsDone(int taskNo) throws InvalidIndexException {
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(taskNo - 1); // The user starts counting from 1
        task.markAsDone();

        String toReturn = "";
        toReturn += Ui.indentWithNewline("Excellent! You have completed this task: ", 1);
        toReturn += Ui.indent(task.toString(), 2);
        return toReturn;
    }

    /**
     * Delete a duke.task.Task (specified by the index)
     * @param taskNo A 1-based indexing of the position of the duke.task.Task in the list
     * @throws InvalidIndexException
     */
    public String deleteTask(int taskNo) throws InvalidIndexException {
        if (taskNo < 1 || taskNo > tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(taskNo - 1); // The user starts counting from 1
        tasks.remove(taskNo - 1);

        String toReturn = "";
        toReturn += Ui.indentWithNewline("Understood. I have now removed this task: ", 1);
        toReturn += Ui.indentWithNewline(task.toString(), 2);
        toReturn += Ui.getTaskCount(this);
        return toReturn;
    }

    /**
     * Checks if the duke.main.TaskList is full (full is defined by 100 tasks)
     * @return true if duke.main.TaskList is full
     */
    public boolean isFull() {
        return tasks.size() == 100;
    }

    public String find(String args) {
        TaskList matches = new TaskList();
        for (Task task: tasks) {
            if (task.toString().contains(args)) matches.add(task);
        }

        if (matches.size() == 0)  {
            return Ui.indentWithNewline("No match found. ", 1);
        } else {
            return Ui.displayListInUi(matches, "We have found the following matches: ");
        }
    }

    // Temporary method for GUI.
    // TODO: Refactor this to something nicer
    public String findToString(String args) {

        TaskList matches = new TaskList();
        for (Task task: tasks) {
            if (task.toString().contains(args)) matches.add(task);
        }

        if (matches.size() == 0)  {
            return "No matches found";
        } else {
            return matches.toString();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            String current = (i + 1) + ". " + this.get(i).toString() + "\n";
            sb.append(current);
        }
        return sb.toString();
    }

}
