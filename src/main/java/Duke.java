import java.util.Scanner;

public class Duke {

    public static int counter = 0; // this has to be a global variable

    public static void main(String[] args) {

        Task[] tasks = new Task[100]; // declare array for dealing with tasks
        //int counter = 0;

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine(); // read in the user input
        while (!user_input.toLowerCase().equals("bye")) {
            String[] splitstr = user_input.split(" "); // split the string first

            if (user_input.toLowerCase().equals("list")) { // list
                list(tasks, counter);
            }
            else if (splitstr[0].toLowerCase().equals("done")) { // done
                markCompleted(tasks, Integer.parseInt(splitstr[1])-1); // whichever task is marked completed
            }
            else {
                String[] time_split = user_input.split("/"); // the 2nd half contains the deadline
                if (splitstr[0].toLowerCase().equals("todo")) {
                    try {
                        addTodo(tasks, user_input, time_split, splitstr);
                    }
                    catch (DukeException exception) {
                        System.out.println(exception);
                    }
                }
                else if (splitstr[0].toLowerCase().equals("deadline")) {
                    try {
                        addDeadline(tasks, user_input, time_split, splitstr);
                    }
                    catch (DukeException exception) {
                        System.out.println(exception);
                    }
                }
                else if (splitstr[0].toLowerCase().equals("event")) {
                    try {
                        addEvent(tasks, user_input, time_split, splitstr);
                    }
                    catch (DukeException exception) {
                        System.out.println(exception);
                    }
                }
                else {
                    System.out.println("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                //System.out.println("Now you have " + Integer.toString(counter) + " tasks in the list.");
            }
            user_input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void addTodo(Task[] tasks, String user_input, String[] time_split, String[] splitstr) throws DukeException {
        if (splitstr.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
        }
        String task_description = user_input.replace(splitstr[0], "");

        tasks[counter] = new Todo(task_description, "[T]"); // no deadline
        Task current_task = tasks[counter]; // we just use this for reference
        counter++;
        System.out.println("Got it. I've added this task:");
        System.out.println(current_task.getTaskType() + current_task.getCompletionStatus()
                + current_task.getDescription());
        System.out.println("Now you have " + Integer.toString(counter) + " tasks in the list.");
    }

    public static void addDeadline(Task[] tasks, String user_input, String[] time_split, String[] splitstr) throws DukeException {
        if (splitstr.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The description of a deadline cannot be empty.");
        }
        String task_description = time_split[0].replace(splitstr[0], "");
        String deadline = time_split[1].replace("by ","");

        tasks[counter] = new Deadline(task_description, "[D]", deadline);
        Task current_task = tasks[counter];
        counter++;
        System.out.println("Got it. I've added this task:");
        System.out.println(current_task.getTaskType() + current_task.getCompletionStatus()
                + current_task.getDescription() + " (by: " + deadline + ")");
        //print something else here
        System.out.println("Now you have " + Integer.toString(counter) + " tasks in the list.");
    }

    public static void addEvent(Task[] tasks, String user_input, String[] time_split, String[] splitstr) throws DukeException {
        if (splitstr.length == 1) {
            throw new DukeException("\u2639 OOPS!!! The description of an event cannot be empty.");
        }
        String task_description = time_split[0].replace(splitstr[0], "");
        String deadline = time_split[1].replace("at ","");

        tasks[counter] = new Event(task_description, "[E]", deadline);
        Task current_task = tasks[counter];
        counter++;
        System.out.println("Got it. I've added this task:");
        System.out.println(current_task.getTaskType() + current_task.getCompletionStatus()
                + current_task.getDescription() + " (at: " + deadline + ")");
        // print something else here
        System.out.println("Now you have " + Integer.toString(counter) + " tasks in the list.");
    }

    public static void list(Task[] tasks, int counter) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < counter; i++) {
            String status = tasks[i].checkIfComplete();
            System.out.println(Integer.toString(i+1) + ". " + tasks[i].getTaskType() + "[" + status +
                    "] " + tasks[i].getDescription() + tasks[i].getDeadline());
        }
    }

    public static void markCompleted(Task[] tasks, int taskNum) {
        System.out.println("Nice! I've marked this task as done:");
        tasks[taskNum].updateisDone(true);
        System.out.println("[" + tasks[taskNum].checkIfComplete() + "] " + tasks[taskNum].getDescription());
    }

}