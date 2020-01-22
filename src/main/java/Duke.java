import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        final String ExitCommand = "bye";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________");


        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            System.out.println("\t____________________________________________________________");
            String line = scanner.nextLine().trim();
            String command = line.split(" ")[0];


            if (command.equals(ExitCommand)) {
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________");
                break;
            }

            System.out.println("\t" + line);
            System.out.println("\t____________________________________________________________");
        }
    }
}
