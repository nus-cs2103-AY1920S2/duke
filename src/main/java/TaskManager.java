import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> arrTasks;

    public TaskManager() {
        this.arrTasks = new ArrayList<>();
    }

    public String addTask(Task t) {
        this.arrTasks.add(t);
        String response = "Got it. I've added this task:\n" + t + '\n';
        response += "Now you have " + this.arrTasks.size() + " tasks in the list.";
        return response;
    }

    public String markTaskAsDone(int index) {
        Task t = this.arrTasks.get(index);
        t.setDone();
        String response = "Nice! I've marked this task as done:\n" + t;
        return response;
    }

    public String showTasks() {
        String allTasks = "Here are the tasks in your list:\n";
        int counter = 1;
        for (Task t: arrTasks) {
            allTasks += (counter + "."); // Index of task
            allTasks += (" " + t + '\n'); // Description of task and whether it is done
            counter++;
        }
        return allTasks;
    }
}
