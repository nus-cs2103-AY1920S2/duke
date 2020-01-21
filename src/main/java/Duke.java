import java.util.Scanner;
import java.lang.Integer;

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
        UserText tasks = new UserText();

        while(isListening) {
            String command = input.nextLine();
            String[] command_broken = command.split(" ",2);

            String action = command_broken[0];

            if (action.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again!");
                isListening = false;

            } else if (action.equalsIgnoreCase("list")) {
                tasks.printInputs();

            } else if (action.equalsIgnoreCase("done")) {
                String context = command_broken[1];
                int taskNo = Integer.parseInt(context);
                tasks.markDone(taskNo);

            } else if (action.equalsIgnoreCase(("deadline"))){
                String context = command_broken[1];
                String[] context_broken = context.split("/by",2);
                tasks.addInput(new Deadlines(context_broken[0], context_broken[1]));
            } else if (action.equalsIgnoreCase(("todo"))){
                tasks.addInput(new ToDos(command_broken[1]));
            } else if (action.equalsIgnoreCase(("event"))){
                String context = command_broken[1];
                String[] context_broken = context.split("/at",2);
                tasks.addInput(new Events(context_broken[0], context_broken[1]));
            }
        }
    }
}
