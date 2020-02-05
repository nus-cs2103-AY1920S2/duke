package dukeApp.files;

import dukeApp.action.Action;

import java.util.ArrayList;

public class Done extends Action {
    public Done(int num, ArrayList<Task> arrList) {
        super(num, arrList);
    }

    /**
     * Mark a task as done
     *
     * @return a statement indicating a task has been marked as done
     */
    public void markDone() {
        arrList.get(num-1).markAsDone();
    }
}
