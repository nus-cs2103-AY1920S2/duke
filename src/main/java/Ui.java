import java.util.Scanner;

public class Ui {
    public static void firstRun() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Welcome! What can I do for you today?");
    }

    public static boolean askBeforeQuitting(Scanner scanner, TaskList tasklist) {
        System.out.println("Save before Quitting?");
        System.out.println("Y: Save Changes");
        System.out.println("N: Discard Changes");
        System.out.println("Press any other key to Cancel and Return");
        String choice = scanner.nextLine();

        if (choice.equals("Y") || choice.equals("y")) {
            Storage.save(tasklist);
            System.out.println("Changes saved!");
            return false;
        } else if (choice.equals("N") || choice.equals("n")) {
            return false;
        } else {
            return true;
        }


    }

    public static String getNextCommand(Scanner scanner, TaskList tasklist) {
        System.out.print("> ");
        String input = scanner.nextLine().strip();
        String[] command = Parser.parseInput(input);

        return Parser.processCommand(command, tasklist);
    }

    public static void exitMessage() {
        System.out.println("Thank you for using Duke.\nHave a nice day!\n");
    }

}