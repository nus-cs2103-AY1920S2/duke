import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> arrTask = new ArrayList<Task>();

    public TaskList(){
    }

    public TaskList(ArrayList<Task> tasks ){
        this.arrTask = tasks;
    }

    public void printTaskList(){
        for (int i = 0; i < arrTask.size() ; i++) {
            System.out.println((i + 1) + ". " + arrTask.get(i).toString());
        }
    }
}
