package dukeApp.files;
import dukeApp.action.Action;
import java.util.ArrayList;

public class Delete extends Action {
    public Delete(int num, ArrayList<Task> arrList) {
        super(num, arrList);
    }

    /**
     * Removes a task from the list.
     *
     * @return a String statement indicating a task has been removed
     */
    public void deleteTask() {
        arrList.remove(num-1);
    }
}
