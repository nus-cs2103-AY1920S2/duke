import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you?");

        Scanner input = new Scanner(System.in);

        boolean isListening = true;
        UserText inputs = new UserText();

        while(isListening) {
            String command = input.nextLine();

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                isListening = false;
            } else if (command.equalsIgnoreCase("list")) {
                inputs.printInputs();
            } else {
                System.out.println(command);
                inputs.addInput(command);
            }
        }
    }
}
