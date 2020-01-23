import java.util.List;
import java.util.ArrayList;

public class Task {
    static List<Task> taskList = new ArrayList<>();
    String taskName;
    boolean isDone;

    public Task(String taskName){
        this.taskName = taskName;
        this.isDone = false;
    }

    public static String addTask(String taskName){
        Task newTask = new Task(taskName);
        taskList.add(newTask);
        return "     added: " + taskName;
    }

    public static void printList(){
        System.out.println("     Here are the tasks in your list:");
        int i = 1;
        for(Task task : Task.taskList){
            System.out.println("     " + i + ". " + task);
            i++;
        }
    }

    public static void markDone(int num){
        System.out.println("     Nice! I've marked this task as done: ");
        Task taskDone = taskList.get(num - 1);
        taskDone.isDone = true;
        String out =  "       " + taskDone;
        System.out.println(out);
    }

    private String taskStateString(){
        return (this.isDone) ? "[✓]" : "[✗]";
    }

    @Override
    public String toString(){
        return taskStateString() + " " + this.taskName;
    }
}
