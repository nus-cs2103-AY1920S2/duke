import jdk.jfr.Event;

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
        String[] commandArray = command.split(" ", 2);
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
            case "todo":
                addTask(commandArray[1], TaskType.TODO);
                break;
            case "deadline":
                addTask(commandArray[1], TaskType.DEADLINE);
                break;
            case "event":
                addTask(commandArray[1], TaskType.EVENT);
                break;
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

    public void addTask(String description, TaskType taskType) {
        System.out.println("Great! I've added the following task to your list: ");
        Task task = new Task("");
        switch(taskType) {
            case TODO:
                task = new TodoTask(description);
                break;
            case DEADLINE:
                String[] deadlineArray = description.split(" /by ", 2);
                task = new DeadlineTask(deadlineArray[0], deadlineArray[1]);
                break;
            case EVENT:
                String[] eventArray = description.split(" /at ", 2);
                task = new EventTask(eventArray[0], eventArray[1]);
                break;
        }

        taskList.add(task);
        System.out.println(task);
        System.out.println(String.format("You now have %d tasks in your list.", taskList.size()));
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
