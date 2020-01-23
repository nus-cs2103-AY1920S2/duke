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
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskLength; i++) {
                    System.out.println(i + 1 + ". " + taskList[i]);
                }
            } else if ((command.length() > 3) && (command.substring(0, 4).equals("done"))) {
                //Mark task as done
                int taskNumber = Integer.parseInt(command.substring(5));
                taskList[taskNumber-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList[taskNumber-1]);
            } else {
                //Add task
                taskList[taskLength] = new Task(command);
                taskLength++;
                System.out.println("added: " + command);
            }
        }
    }
}
