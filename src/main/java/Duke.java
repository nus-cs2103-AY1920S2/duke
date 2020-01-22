import java.util.*;

class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getDescription(){
        return this.description;
    }

    public String printTask(){
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    public void markAsDone(){
        this.isDone = true;
    }
}

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<Task>();
    static Scanner sc = new Scanner(System.in);

    static void printIntro() {
        System.out.print(
                "____________________________________________________________\n" +
                        "Hello! I'm Duke\n" +
                        "What can I do for you?\n" +
                        "____________________________________________________________\n\n");
    }

    static void printReply(String input) {
        System.out.print(
                "____________________________________________________________\n" +
                "added: " + input +
                "\n____________________________________________________________\n");
    }

    static void printGoodbye() {
        System.out.print(
                "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!" +
                        "\n____________________________________________________________\n");
    }

    static void printList(ArrayList<Task> tasks){
        System.out.print("____________________________________________________________\n" +
                "Here are the tasks in your list:\n");
        for (int i =  0; i < tasks.size(); i++){
            System.out.println(Integer.toString(i + 1) + ". " + tasks.get(i).printTask());
        }
        System.out.print("____________________________________________________________\n");
    }

    static void markTaskDone(Task task){
        task.markAsDone();
        System.out.println("____________________________________________________________\n"
            + "Nice! I've marked this task as done:\n" + task.printTask()
                + "\n____________________________________________________________\n");

    }

    public static void main(String[] args) {
        printIntro();
        String input = sc.nextLine();
        while (!input.toLowerCase().equals("bye")) {
            if (input.toLowerCase().equals("list")) {
                printList(tasks);
            } else if (input.split(" ")[0].equals("done")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                markTaskDone(tasks.get(taskNumber));
            }
            else {
                Task task = new Task(input);
                tasks.add(task);
                printReply(input);
            }
            input = sc.nextLine();
        }
        printGoodbye();
    }
    
}
