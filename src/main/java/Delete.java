import java.util.ArrayList;

public class Delete extends Action {
    public Delete(int num, ArrayList<Task> arrList) {
        super(num, arrList);
    }

    public String deleteTask() {
        arrList.remove(num-1);
        return "Noted. I've removed this task:";
    }
}
