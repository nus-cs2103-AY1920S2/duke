import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Tracker tracker = new Tracker();
        Scanner scanner = new Scanner(System.in);
        Command command = new Command(scanner.nextLine());

        while (command.isBye() == false) {
            if (command.isList()) {
                for (String item : tracker.showList()) {
                    System.out.println(item);
                }
            } else {
                tracker.add(command.getCmd());
                System.out.println("added: " + command.getCmd());
            }

            command = new Command(scanner.nextLine());
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}