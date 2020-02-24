import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<Task>();

    TaskList() {
        this.list = new ArrayList<Task>();
    }

    TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    ArrayList<Task> getList() {
        return this.list;
    }
}
