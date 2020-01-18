import java.util.Scanner;

public class Driver {
    public static String readCommand(Scanner scanner) {
        return scanner.next();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Add your command here!");
        System.out.println("");

        String command = readCommand(scanner);
        while (!command.equals("bye")) {
            System.out.println("------------------------");
            duke.processCommand(command);
            System.out.println("------------------------");
            command = readCommand(scanner);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------");
    }
}
