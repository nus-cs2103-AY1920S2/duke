import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        printGreeting();
        runDuke();
    }

    public static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        System.out.println("Hi, I'm Duke!");
        System.out.println("What can I do for you?\n");
    }

    public static String getUserInput() {
        System.out.print("> ");

        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    public static void runDuke() {
        // commands: bye, list, done
        // any other input will be added to tasks
        String input = getUserInput();
        while (!input.equals("bye")) {
            String command = input.split(" ")[0];

            if (command.equals("list")) {
                Task.printTasks();
            } else if (command.equals("done")) {
                int doneTaskNum = Integer.parseInt(input.split(" ")[1]);
                Task doneTask = Task.tasks[doneTaskNum - 1];
                doneTask.markAsDone();

                Task.printMarkedAsDone(doneTask);
            } else {
                Task.addTask(input);

                System.out.format("added: %s%n%n", input);
            }

            input = getUserInput();
        }

        System.out.println("Have a nice day!");
    }
}
