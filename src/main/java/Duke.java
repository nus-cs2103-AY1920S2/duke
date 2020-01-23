import java.util.*;

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

    static void printReply(Task task) {
        //TODO: Update this method
        System.out.print(
                "____________________________________________________________\n" +
                "Got it! I've added the task: \n" + task.toString() + "\nNow you have " + Integer.toString(tasks.size()) +
                        " tasks in the list." +
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
            System.out.println(Integer.toString(i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.print("____________________________________________________________\n");
    }

    static void markTaskDone(Task task){
        task.markAsDone();
        System.out.println("____________________________________________________________\n"
            + "Nice! I've marked this task as done:\n" + task.toString()
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
                if (input.split(" ")[0].equals("todo")){
                    Task task = new Todo(input.split(" ",2)[1]);
                    tasks.add(task);
                    printReply(task);
                } else if (input.split(" ")[0].equals("deadline")){
                    Task task = new Deadline(input.split("/by",2)[0].split(" ", 2)[1], input.split("/by",2)[1]);
                    tasks.add(task);
                    printReply(task);
                } else if (input.split(" ")[0].equals("event")){
                    Task task = new Event(input.split("/at",2)[0].split(" ", 2)[1], input.split("/at",2)[1]);
                    tasks.add(task);
                    printReply(task);
                } else {}
            }
            input = sc.nextLine();
        }
        printGoodbye();
    }

}