import java.util.List;
import java.util.ArrayList;

public class Dude {
    protected List<Task> userInputList;
    protected String logo = "  ____\n" + " (.__.)\n" + "<|>\n" + " /\\" + "\n_  _";
    protected boolean isActivated = false;

    Dude() {
        userInputList = new ArrayList<>();

    }

    public void start() {
        isActivated = true;
        System.out.println(logo);
        System.out.println("☛ dude, what do you want? \n☛ give me a command!");
    }

    public void end() {
        isActivated = false;
        System.out.println("☛ dude, stop wasting my time! shoo!");
    }

    public boolean isRunning() {
        return isActivated;
    }

    public void passInput(String input) {
        if (input.equals("bye")) {
            this.end();
        }
        else if (input.equals("list")) {
            this.listTasks();
        }
        else if (input.startsWith("done")) {
            doneTask(input);
        }
        else if (input.startsWith("delete")) {
            deleteTask(input);
        }
        else {
            createTask(input);
        }
    }



    public void listTasks() {
        System.out.println("☛ you asked to:");
        for (int i = 0; i < userInputList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + userInputList.get(i));
        }
    }

    public void doneTask(String userInput) {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
            userInputList.get(taskNumber).markAsDone();
            System.out.println("☛ dude, nicely! you're done with:");
            System.out.println("\t" + userInputList.get(taskNumber));
        }
        catch(IndexOutOfBoundsException e) { // catch exception - trying to access number higher than number of tasks
            System.out.println(new DukeException("done"));
        }
    }

    public void deleteTask(String userInput) {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
            Task t = userInputList.get(taskNumber);
            System.out.println("☛ dude. deleting:");
            System.out.println("\t" + userInputList.get(taskNumber));
            userInputList.remove(t);
        }
        catch(IndexOutOfBoundsException e) { // catch exception - trying to access number higher than number of tasks
            System.out.println(new DukeException("delete"));
        }

    }

    public void createTask(String userInput) {
        try {
            Task task;
            String[] split = userInput.split(" ", 2);
            String taskType = split[0];
            if (split.length <= 1) {
                throw new DukeException("task", taskType); // throw an exception if user passes only one command
            }
            String taskLine = split[1];
            if (taskType.equals("todo")) { // to add todos (tasks with no date/time attached)
                task = new ToDo(taskLine);
            } else if (userInput.startsWith("deadline")) { // to add deadlines (tasks that must be done by specific date/time)
                task = new Deadline(taskLine);
            } else if (userInput.startsWith("event")) { // to add event (tasks that start at a specific time and ends at a specific time)
                task = new Event(taskLine);
            }
            else {
                throw new DukeException("task", taskType); // throw an exception if user tries to create a task with type that is nonexistent
            }
            userInputList.add(task);
            System.out.println("☛ fine, I will take note of: " + task);
            System.out.println("☛ you made me remember " + userInputList.size() + " task(s)");
        }
        catch(DukeException e) {
            System.out.println(e);
        }
    }

}


