import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    protected ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    public int getSize() {
        return this.lst.size();
    }

    public Task getTask(int index) {
        Task task = null;
        try {
            task = this.lst.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("    Task index out of range.");
        }
        return task;
    }

    public void addTask(Task task) {
        this.lst.add(task);
    }

    public boolean doneTask(int index) {
        boolean isDone = false;
        try {
            Task currTask = lst.get(index);
            currTask.setDone(true);
            isDone = true;
        } catch (NumberFormatException e) {
            System.err.println("    Please input an integer.");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("    Please try again, your number is out of range.");
        }
        return isDone;
    }

    public boolean deleteTask(int index) {
        boolean isDone = false;
        try {
            Task currTask = lst.get(index);
            lst.remove(index);
            isDone = true;
        } catch (NumberFormatException e) {
            System.err.println("    Please input an integer.");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("    Please try again, your number is out of range.");
        }
        return isDone;
    }

    /**
     * Determine and return Tasklist containing tasks with keywords specified.
     * @param toFind keyword to find in tasks' descriptions
     * @return TaskList with all tasks that contains toFind
     */
    public TaskList findMatchingTasks(String toFind) {
        TaskList tempLst = new TaskList();
        for (Task task : lst) {
            if (task.getDesc().contains(toFind)) {
                tempLst.addTask(task);
            }
        }
        return tempLst;
    }
}
