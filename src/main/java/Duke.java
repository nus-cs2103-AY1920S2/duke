import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greet the user
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        //Echo
        Scanner s = new Scanner(System.in);
        String command = "";
        while (!command.equals("bye")) {
            command = s.next();
            //Exit
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(command);
            }
        }
    }
}
