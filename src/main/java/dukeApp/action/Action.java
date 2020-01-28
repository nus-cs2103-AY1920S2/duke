package dukeapp.action;
import dukeapp.files.*;
import java.util.ArrayList;

public class Action {
    protected int num;
    protected ArrayList<Task> arrList = new ArrayList<>();

    public Action(int num, ArrayList<Task> arrList) {
        this.num = num;
        this.arrList = arrList;
    }

    public int checkNum() {
        if (num > arrList.size() || num == 0) {
            return 0;
        }
        else {
            return 1;
        }
    }

    public String printAction() {
        return "  [" +arrList.get(num-1).getType()+ "][" + arrList.get(num-1).getStatusIcon()
                + "]" + arrList.get(num-1).getTask();
    }
}
