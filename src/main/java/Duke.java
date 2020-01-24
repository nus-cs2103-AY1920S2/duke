import java.util.ArrayList;
import java.util.Scanner;

abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}

class TodoTask extends Task {

    public TodoTask(String description) {
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}

class DeadlineTask extends Task {

    private String by;

    public DeadlineTask(String description, String deadline) {
        super(description);
        this.by = deadline;
    }

    @Override
    public String toString(){
        return "[D] " + super.toString() + " (by: "+ this.by + ")";
    }
}

class EventTask extends Task {

    private String at;

    public EventTask(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString(){
        return "[D] " + super.toString() + " (by: "+ this.at + ")";
    }
}

public class Duke {
    public static void addTaskReport(Task task, int numOfTasks){
        System.out.println("\t Got it. I've added this task: \n" +
                "\t\t"+ task +"\n" +
                "\t Now you have "+ numOfTasks+ " tasks in the list.");
    }

    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();

        final String ExitCommand = "bye";
        final String ListCommand = "list";
        final String DoneCommand = "done";
        final String TodoCommand = "todo";
        final String DeadlineCommand = "deadline";
        final String EventCommand = "event";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");


        Scanner scanner = new Scanner(System.in);
        Task task;
        String desc = "";
        String timestamp = "";
        String[] taskInfo;

        main:
        while (scanner.hasNextLine()) {
            System.out.println("\t____________________________________________________________");
            String line = scanner.nextLine().trim();
            String[] seperatedLine = line.split(" ", 2);
            String command = seperatedLine[0];
            String parameters = seperatedLine.length > 1 ? seperatedLine[1] : "";

            switch (command) {
                case ExitCommand:
                    System.out.println("\t Bye. Hope to see you again soon!");
                    System.out.println("\t____________________________________________________________");
                    break main;
                case ListCommand:
                    for(int i = 1; i <= tasks.size(); i++){
                        System.out.println("\t " + i + ". " + tasks.get(i-1));
                    }
                    break;
                case DoneCommand:
                    int taskId = Integer.parseInt(line.split(" ")[1]);
                    task = tasks.get(taskId  - 1);
                    task.markAsDone();
                    System.out.println("\t Nice! I've marked this task as done: ");
                    System.out.println("\t\t" + task);
                    break;

                case TodoCommand:
                    task = new TodoTask(parameters);
                    tasks.add(task);
                    addTaskReport(task, tasks.size());
                    break;
                case DeadlineCommand:
                    taskInfo = parameters.split("/by");
                    desc = "";
                    timestamp = "";
                    if (taskInfo.length > 0){
                        desc = taskInfo[0].trim();
                    }
                    if (taskInfo.length > 1 ){
                        timestamp = taskInfo[1].trim();
                    }
                    task = new DeadlineTask(desc, timestamp);
                    tasks.add(task);
                    addTaskReport(task, tasks.size());
                    break;

                case EventCommand:
                    taskInfo = parameters.split("/at");

                    if (taskInfo.length > 0){
                        desc = taskInfo[0].trim();
                    }
                    if (taskInfo.length > 1 ){
                        timestamp = taskInfo[1].trim();
                    }
                    task = new EventTask(desc, timestamp);
                    tasks.add(task);
                    addTaskReport(task, tasks.size());
                    break;

                default:
                    System.out.println("\t Unknown command! Please try again!");
            }

            System.out.println("\t____________________________________________________________");
        }
    }
}
