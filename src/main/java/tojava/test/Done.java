package tojava.test;
import tojava.action.Action;
import java.util.ArrayList;

public class Done extends Action {
    public Done(int num, ArrayList<Task> arrList) {
        super(num, arrList);
    }

    public String markDone() {
        arrList.get(num-1).markAsDone();
        return "Nice! I've marked this task as done: ";
    }
}
