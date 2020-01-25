import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private ArrayList<Task> arrTasks;
    private HashMap<String, String> taskToRegex;

    public TaskManager() {
        this.arrTasks = new ArrayList<>();
        this.taskToRegex = new HashMap<>();
        this.taskToRegex.put("todo", "\n");
        this.taskToRegex.put("deadline", " /by ");
        this.taskToRegex.put("event", " /at ");
    }

    private String getRegex(String task) {
        return this.taskToRegex.get(task);
    }

    private String addTask(Task t) {
        this.arrTasks.add(t);
        String response = "Got it. I've added this task:\n" + t + '\n';
        response += "Now you have " + this.arrTasks.size() + " tasks in the list.";
        return response;
    }

    public String addTask(String command, String desc) {
        String response = "";
        String descTimeArr[] = desc.split(this.getRegex(command), 2);
        switch (command) {
            case "todo":
                response = this.addTask(new ToDo(descTimeArr[0]));
                break;
            case "deadline":
                response = this.addTask(new Deadline(descTimeArr[0], descTimeArr[1]));
                break;
            case "event":
                response = this.addTask(new Event(descTimeArr[0], descTimeArr[1]));
                break;
        }
        return response;
    }

    public String removeTask(int i) {
        Task t = this.arrTasks.get(i);
        this.arrTasks.remove(i);
        String response = "Noted. I've removed this task:\n" + t + '\n';
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
