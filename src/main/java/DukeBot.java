import java.util.ArrayList;
import java.util.List;

public class DukeBot {

    private List<Task> taskList;
    private boolean isActive;

    public DukeBot() {
        taskList = new ArrayList<Task>();
        isActive = true;
    }

    public void processCommand(String command) {
        String[] commandArray = command.split(" ");
        switch(commandArray[0]) {
            case "bye":
                dukeBye();
                break;
            case "list":
                printTaskList();
                break;
            case "done":
                dukeDone(Integer.parseInt(commandArray[1]));
                break;
            default:
                Task task = new Task(command);
                addTask(task);
        }
    }

    public void dukeHi() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I'm your personal chat bot assistant! How may I be of service today?");
    }

    public void dukeBye() {
        System.out.println("Bye! Hope to see you again soon :)");
        disableDuke();
    }

    public void dukeDone(int taskNumber) {
        int taskIndex = taskNumber - 1;
        Task selectedTask = taskList.get(taskIndex);
        selectedTask.completeTask();
        System.out.println("Nice! I've marked this task as done: " + selectedTask);
    }

    public void addTask(Task task) {
        taskList.add(task);
        System.out.println("added: " + task.getDescription());
    }

    public void printTaskList() {
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println((i+1) + ". " + taskList.get(i));
        }
    }

    public void disableDuke() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }
}
