import java.util.ArrayList;
import java.util.List;

public interface Task{
    static List<Task> taskList = new ArrayList<>();

    public static void addTask(String taskName){
        String taskType = taskName.split(" ", 2)[0];
        String taskDesc = taskName.split(" ", 2)[1];
        Task newTask;
        if (taskType.equals("todo")){
            newTask = new ToDo(taskDesc);
        } else if (taskType.equals("deadline")){
            String[] in = taskDesc.split("/");
            newTask = new Deadline(in[0], in[1]);
        } else {
            String[] in = taskDesc.split("/");
            newTask = new Event(in[0], in[1]);
        }
        taskList.add(newTask);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + newTask);
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void printList(){
        System.out.println("     Here are the tasks in your list:");
        int i = 1;
        for(Task task : Task.taskList){
            System.out.println("     " + i + ". " + task);
            i++;
        }
    }

    public static void printDone(int num){
        System.out.println("     Nice! I've marked this task as done: ");
        Task taskDone = taskList.get(num - 1);
        taskDone.markDone();
        String out =  "       " + taskDone;
        System.out.println(out);
    }

    public void markDone();
}