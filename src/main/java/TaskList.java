import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {

    public ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList tasks) {
        this.tasks = tasks;
    }
    
    public void addTask(Task task){
        tasks.add(task);
    }
    public int getTaskListSize(){
        return tasks.size();
    }
    
    public Task getTask(int taskNumber){
        return tasks.get(taskNumber);
    }
    
    public void deleteTask(int taskNumber){
        tasks.remove(taskNumber);
    }
    

}
