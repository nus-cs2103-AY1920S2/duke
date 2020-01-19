import java.util.Scanner;

public class Driver {
    public static String readCommand(Scanner scanner) {
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();

        System.out.println("------------------------");
        System.out.println("Hello I'm Duke!");
        System.out.println("Add your command here!\n");

        String command = readCommand(scanner);
        System.out.println("------------------------");
        while (!command.equals("bye")) {
            try {
                duke.processCommand(command);
            } catch(DukeException e) {
                System.err.println(e);
            }

            System.out.println("------------------------");
            command = readCommand(scanner);
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------");
    }
}
