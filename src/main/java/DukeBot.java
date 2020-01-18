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

        try {
            switch (commandArray[0]) {
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
                    addTask(command, TaskType.TODO);
                    break;
                case "deadline":
                    addTask(command, TaskType.DEADLINE);
                    break;
                case "event":
                    addTask(command, TaskType.EVENT);
                    break;
                default:
                    System.out.println("Oh no! I have no idea what you're trying to tell me :(((, try again?");
            }
        } catch(DukeException e) {
            System.out.println(e);
        } catch(Exception e) {
            e.printStackTrace();
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

    public void addTask(String description, TaskType taskType) throws DukeException {

        String[] descArray = description.split(" ", 2);

        Task task = new Task("");
        switch(taskType) {
            case TODO:
                if(descArray.length < 2) {
                    throw new DukeException("OOPS!! The description of \"todo\" cannot be empty!");
                }
                task = new TodoTask(descArray[1]);
                break;
            case DEADLINE:
                if(descArray.length < 2) {
                    throw new DukeException("OOPS!! The description of \"deadline\" cannot be empty!");
                } else if(!descArray[1].contains("/by")) {
                    throw new DukeException("OOPS!! You didn't set a deadline for this deadline task!");
                }
                String[] deadlineArray = descArray[1].split(" /by ", 2);
                task = new DeadlineTask(deadlineArray[0], deadlineArray[1]);
                break;
            case EVENT:
                if(descArray.length < 2) {
                    throw new DukeException("OOPS!! The description of \"event\" cannot be empty!");
                } else if(!descArray[1].contains("/at")) {
                    throw new DukeException("OOPS!! You didn't set a timing for this event!");
                }
                String[] eventArray = descArray[1].split(" /at ", 2);
                task = new EventTask(eventArray[0], eventArray[1]);
                break;
        }

        System.out.println("Great! I've added the following task to your list: ");
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
