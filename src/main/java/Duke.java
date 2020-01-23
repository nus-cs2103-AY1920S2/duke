import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Task[] taskList = new Task[100];
        int taskLength = 0;

        //Greet the user
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        //Echo
        Scanner s = new Scanner(System.in);
        String command = "";
        while (s.hasNext()) {
            command = s.nextLine();
            if (command.equals("bye")) {
                //Exit
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                //List all tasks
                for (int i = 0; i < taskLength; i++) {
                    System.out.println(i + 1 + ". " + taskList[i]);
                }
            } else {
                //Add task
                taskList[taskLength] = new Task(command);
                taskLength++;
                System.out.println("added: " + command);
            }
        }
    }
}
