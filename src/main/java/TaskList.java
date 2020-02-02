/**
 * a class to store, delete and add new tasks
 */
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;

    TaskList(ArrayList<Task> taskList) { // initialise the task list with data loaded from storage
        this.taskList = taskList;
    }

    public Task deleteTask(int index) throws DukeException { // deletes task at the index provided, and returns the deleted task
        try {
            return taskList.remove(index);
        }
        catch (IndexOutOfBoundsException e) { // throw exception if the task does not exist at index provided
            throw new DukeException(DukeError.NUMBER);
        }
    }

    public Task doneTask(int index) throws DukeException{ // marks the task at the index provided as done, and returns the done task
        try {
            Task task;
            task = taskList.get(index);
            task.markAsDone();
            return task;
        }
        catch (IndexOutOfBoundsException e) { // throw exception if the task does not exist at index provided
            throw new DukeException(DukeError.NUMBER);
        }
    }

    public boolean addTask(Task task) {
        return taskList.add(task);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getListSize() {
        return taskList.size();
    }
}
