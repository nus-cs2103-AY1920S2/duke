package dukeApp.files;

import dukeApp.action.Action;

import java.util.ArrayList;

public class Done extends Action {
    public Done(int num, ArrayList<Task> arrList) {
        super(num, arrList);
        assert num > 0 : "No negative or 0 index";
    }

    /**
     * Mark a task as done
     */
    public void markDone() {
        arrList.get(num-1).markAsDone();
    }
}
