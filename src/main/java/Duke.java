import java.util.Scanner;

public class Duke {

    //public final static String LINE = "__________________________________";
    //public final static String INDENT = "     ";

    public static void main(String[] args) {

        // declare an array to store stuff
        Task[] tasks = new Task[100];
        int counter = 0;
        for (int i = 0; i < 100; i++) {
            tasks[i] = new Task("empty"); // populate with emptys
        }

        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); */
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine(); // read in the user input
        while (!user_input.toLowerCase().equals("bye")) {
            String[] splitstr = user_input.split(" ");

            if (user_input.toLowerCase().equals("list")) { // list
                list(tasks);
            }
            else if (splitstr[0].toLowerCase().equals("done")) { // done
                markCompleted(tasks, Integer.parseInt(splitstr[1])-1); // whichever task is marked completed
            }
            else {
                // adding stuff to the array
                tasks[counter].updateDescription(user_input);
                counter++; // OMG GURL U COULD HAVE JUST USED THE COUNTER, OTHERWISE CREATE VAR FOR WHAT
                System.out.println("added: " + user_input);
            }
            user_input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void list(Task[] tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < 100; i++) {
            if (tasks[i].getDescription().equals("empty")) {
                break;
            } else { //need to add a status variable
                String status = tasks[i].checkIfComplete();
                System.out.println(Integer.toString(i+1) + ". [" + status + "] " + tasks[i].getDescription());
            }
        }
    }

    public static void markCompleted(Task[] tasks, int taskNum) {
        System.out.println("Nice! I've marked this task as done:");
        tasks[taskNum].updateisDone(true);
        System.out.println("[" + tasks[taskNum].checkIfComplete() + "] " + tasks[taskNum].getDescription());

    }

}

class Task {

    protected String description;
    protected boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void updateDescription(String update) {
        this.description = update;
    }

    public void updateisDone(boolean update) {
        this.isDone = update;
    }

    public String checkIfComplete() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or x symbols
    }

}
