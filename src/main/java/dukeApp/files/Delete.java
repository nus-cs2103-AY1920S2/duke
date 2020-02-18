package dukeApp.files;

import dukeApp.action.Action;

import java.util.ArrayList;

public class Delete extends Action {
    public Delete(int num, ArrayList<Task> arrList) {
        super(num, arrList);
        assert num > 0 : "No negative or 0 index";
    }

    /**
     * Removes a task from the list.
     */
    public void deleteTask() {
        arrList.remove(num-1);
    }
}
