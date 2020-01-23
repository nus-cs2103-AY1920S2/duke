import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;
    public TaskList (ArrayList<Task> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        String output = "";
        int index = 1;
        for (Task t : list) {
            output += (index + ". " + t.toString() + "\n");
            index++;
        }
        return output;
    }
}