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
        String filePath = "C:\\NUS\\Semester 4\\CS2103\\duke\\data\\duke.txt";
        UserText tasks = new UserText(filePath);

        while(isListening) {
            String command = input.nextLine();
            String[] command_broken = command.split(" ",2);

            String action = command_broken[0];

            try {

                if (action.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again!");
                    tasks.saveTasks();
                    isListening = false;

                } else if (action.equalsIgnoreCase("list")) {
                    tasks.printInputs();

                } else if (action.equalsIgnoreCase("done")) {
                    String context = command_broken[1];
                    int taskNo = Integer.parseInt(context);
                    tasks.markDone(taskNo);

                } else if (action.equalsIgnoreCase("delete")) {
                    String context = command_broken[1];
                    int taskNo = Integer.parseInt(context);
                    tasks.removeTask(taskNo);

                } else if (action.equalsIgnoreCase(("deadline"))) {
                    System.out.println("Got it, I've added this task");
                    String context = command_broken[1];
                    String[] context_broken = context.split("/by", 2);
                    tasks.addInput(new Deadlines(context_broken[0], false ,context_broken[1]));

                } else if (action.equalsIgnoreCase(("todo"))) {
                    System.out.println("Got it, I've added this task");
                    if (command_broken.length == 1) {
                        throw new DukeException("Ooops! The description of a ToDo cannot be empty.");
                    }
                    tasks.addInput(new ToDos(command_broken[1], false));

                } else if (action.equalsIgnoreCase(("event"))) {
                    System.out.println("Got it, I've added this task");
                    String context = command_broken[1];
                    String[] context_broken = context.split("/at", 2);
                    tasks.addInput(new Events(context_broken[0], false,context_broken[1]));

                } else {
                    throw new DukeException("Ooops! I'm sorry, i don't know what it means");
                }

            } catch (DukeException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
