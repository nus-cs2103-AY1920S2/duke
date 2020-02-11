package duke;
import java.lang.reflect.Array;
import java.util.ArrayList;

/*
Represents the task list that stores the tasks of Duke
 */
public class TaskList {
    private ArrayList<Task> taskArrayList;
    private Ui ui = new Ui();

    public TaskList(ArrayList<Task> tasks) {
        this.taskArrayList = tasks;
    }

    public TaskList() {
        //initiate new list
        this.taskArrayList = new ArrayList<Task>();
    }

    public Task getTaskByIndex(int index) {
        return this.taskArrayList.get(index);
    }

    public void addTask(String type, String description){
        Task task = new Task("sample");
        if(type.equals("event")) {
            //handle event
            try {
                String[] args = description.split("/", 2);
                task = new Event(args[0], args[1]);
                this.taskArrayList.add(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.timeErrorMessage();
            }
        } else if (type.equals("deadline")) {
            //handle deadline
            try {
                String[] args = description.split("/", 2);
                task = new Deadline(args[0], args[1]);
                this.taskArrayList.add(task);
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.timeErrorMessage();
            }
        } else {
            task = new ToDo(description);
            taskArrayList.add(task);
        }
        Ui.promptUser("Got it. I've added this task:");
        Ui.promptUser(task.toString());
        Ui.promptUser("Now you have " + taskArrayList.size() + " tasks in your list.");
    }

    public ArrayList<Task> getTaskList() {
        return this.taskArrayList;
    }
    /*
    Marks a task as done and informs the user about the same
     */
    public void markDone(int index) {
        if (index > taskArrayList.size()) {
            Ui.promptUser("There is no such task in the list. Please try again...");
        } else {
            Ui.promptUser("Nice! I've marked this task as done: ");
            taskArrayList.get(index-1).markAsDone();
            Ui.promptUser(taskArrayList.get(index-1).toString());
        }
    }

    /*
    Lists all the currently stored tasks on the system output
     */
    public void printList() {
        int n = taskArrayList.size();
        Ui.promptUser("Here are the tasks in your list:");
        for(int i = 0; i < n; i++) {
            String stringNew = String.format("%d. %s", i + 1, this.taskArrayList.get(i));
            Ui.promptUser(stringNew);
        }
    }

    /*
    Deletes the task at specified index if it exists. Otherwise displays error message to user
     */
    public void deleteTaskByIndex(int index) {
        if (index > taskArrayList.size()) {
            Ui.promptUser("There is no such task in the list. Please try again...");
        } else {
            Task deleted = taskArrayList.get(index - 1);
            this.taskArrayList.remove(index - 1);
            Ui.promptUser("Noted. I've removed this task:");
            Ui.promptUser(deleted.toString());
            Ui.promptUser(String.format("Now you have %d tasks in your list", taskArrayList.size()));
        }
    }

}
