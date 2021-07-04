import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * TaskList class to hold Task objects and perform necessary actions on them.
 */

public class TaskList {
    public ArrayList<Task> arrList;

    /**
     * Constructor. To create and bind an arrayList.
     */
    public TaskList() {
        arrList = new ArrayList<Task>();
    }

    /**
     * Delete task at index taskNum.
     * @param taskNum index at which to delete task
     */
    public void deleteTask(int taskNum) {
        Task removedTask = arrList.get(taskNum - 1);
        arrList.remove(taskNum - 1);
        Ui.printDelete(removedTask, arrList);
    }

    /**
     * Marks task at index taskNum as done.
     * @param taskNum index at which to mark the task
     */
    public void taskDone(int taskNum) {
        Task doneTask = arrList.get(taskNum - 1);
        doneTask.toggleDone();
        Ui.printDone(doneTask, arrList);
    }

    /**
     * Adds a Task to TaskList.
     * @param task Task to be added
     */
    public void addList(Task task) {
        arrList.add(task);
        sortTasks();
        Ui.printAdd(task, arrList);
    }

    /**
     * Finds tasks with taskSubString in their name.
     * @param taskSubString String to be found in Task name
     */
    public void findTasks(String taskSubString) {
        sortTasks();
        ArrayList<Task> filteredList = new ArrayList<Task>();
        for (Task t : arrList) {
            if (t.getName().contains(taskSubString)) {
                filteredList.add(t);
            }
        }
        Ui.printFilteredList(filteredList);
    }

    /**
     * Sort the arrList bound to this object.
     */
    public void sortTasks() {
        Collections.sort(arrList, new Comparator<Task>() {
            public int compare(Task t1, Task t2) {
                String thisClass = t1.getClassName();
                String otherClass = t2.getClassName();
                if (thisClass.equals(otherClass)) {
                    if (thisClass.equals("deadline")) {
                        return t1.getDateObj().compareTo(t2.getDateObj());
                    }
                } else {
                    if (thisClass.equals("todo")) {
                        return -1;
                    } else if (thisClass.equals("event") && otherClass.equals("deadline")) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                return 0;
            }
        });
    }
}
