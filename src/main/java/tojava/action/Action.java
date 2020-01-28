package tojava.action;
import tojava.test.*;
import java.util.ArrayList;

public class Action {
    protected int num;
    protected ArrayList<Task> arrList = new ArrayList<>();

    public Action(int num, ArrayList<Task> arrList) {
        this.num = num;
        this.arrList = arrList;
    }

    /**
     * To check if the task number exist in the list.
     *
     * @return 0 if it does not exist, and 1 otherwise.
     */
    public int checkNum() {
        if (num > arrList.size() || num == 0) {
            return 0;
        }
        else {
            return 1;
        }
    }

    /**
     * Prints the task statement in the list
     *
     * @return task statement
     */
    public String printAction() {
        return "  [" +arrList.get(num-1).getType()+ "][" + arrList.get(num-1).getStatusIcon() + "]" + arrList.get(num-1).getTask();
    }
}
