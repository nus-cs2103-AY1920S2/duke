import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private ArrayList<Task> arrTasks;
    private HashMap<String, String> taskToRegex;
    private TaskWriter w;
    private static final String filePath = "../../../data/duke.txt";

    public TaskManager() {
        try {
            this.arrTasks = new TaskReader().getTasksFromFile(filePath);
        } catch (FileNotFoundException e) {
            this.arrTasks = new ArrayList<>();
        }
        this.taskToRegex = new HashMap<>();
        this.taskToRegex.put("todo", "\n");
        this.taskToRegex.put("deadline", " /by ");
        this.taskToRegex.put("event", " /at ");
        this.w = new TaskWriter();
    }

    private String getRegex(String task) {
        return this.taskToRegex.get(task);
    }

    private String generateInvalidIdxMsg() {
        String msg = "OOPS!!! The index you have entered is invalid.\n";
        if (this.arrTasks.size() == 0) {
            msg += "There are no tasks in the list. Please add a task.";
        } else {
            msg += "Please enter a number between 1 and " + this.arrTasks.size() + ".";
        }
        return msg;
    }

    private String addTask(Task t) {
        this.arrTasks.add(t);
        String response = "Got it. I've added this task:\n" + t + '\n';
        response += "Now you have " + this.arrTasks.size() + " tasks in the list.";
        return response;
    }

    public String addTask(String command, String desc) throws MissingInfoException {
        String response = "";
        String descTimeArr[] = desc.split(this.getRegex(command), 2);
        boolean hasDateTime = false;
        if (descTimeArr.length == 2)
            hasDateTime = true;
        switch (command) {
            case "todo":
                response = this.addTask(new ToDo(descTimeArr[0]));
                break;
            case "deadline":
                if (!hasDateTime)
                    throw new MissingInfoException(command, true);
                response = this.addTask(new Deadline(descTimeArr[0], descTimeArr[1]));
                break;
            case "event":
                if (!hasDateTime)
                    throw new MissingInfoException(command, true);
                response = this.addTask(new Event(descTimeArr[0], descTimeArr[1]));
                break;
        }
        this.writeTasks();
        return response;
    }

    public String removeTask(String s) {
        try {
            int i = Integer.parseInt(s) - 1;
            Task t = this.arrTasks.get(i);
            this.arrTasks.remove(i);
            this.writeTasks();
            String response = "Noted. I've removed this task:\n" + t + '\n';
            response += "Now you have " + this.arrTasks.size() + " tasks in the list.";
            return response;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return this.generateInvalidIdxMsg();
        }
    }

    public String markTaskAsDone(String s) {
        try {
            int i = Integer.parseInt(s) - 1;
            Task t = this.arrTasks.get(i);
            t.setDone();
            this.writeTasks();
            String response = "Nice! I've marked this task as done:\n" + t;
            return response;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return this.generateInvalidIdxMsg();
        }
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

    private void writeTasks() {
        try {
            this.w.writeTasks(filePath, this.arrTasks);
        } catch (IOException e) {
            System.out.println("IOException: There is a problem when trying to write to the file.");
        }
    }
}
