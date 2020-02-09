package dukeApp.action;

import dukeApp.files.Task;
import java.util.ArrayList;

public class Action {
    protected int num;
    protected ArrayList<Task> arrList;

    public Action(int num, ArrayList<Task> arrList) {
        this.num = num;
        this.arrList = arrList;
    }

    /**
     * To check if the task number exist in the list.
     *
     * @return 0 if it does not exist, and 1 otherwise.
     */
    public boolean checkNum() {
        if (num > arrList.size() || num == 0) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Prints the task statement in the list
     *
     * @return task statement
     */
    @Override
    public String toString() {
        return "  [" +arrList.get(num-1).getType()+ "][" + arrList.get(num-1).getStatusIcon()
                + "]" + arrList.get(num-1).toString();
    }
}
