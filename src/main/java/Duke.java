import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        ArrayList<String> storage = new ArrayList<>();

        final String ExitCommand = "bye";
        final String ListCommand = "list";

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

            switch (command) {
                case ExitCommand:
                    System.out.println("\t Bye. Hope to see you again soon!");
                    System.out.println("\t____________________________________________________________");
                    break;
                case ListCommand:
                    for(int i = 0; i < storage.size(); i++){
                        System.out.println("\t " + i + ". " + storage.get(i));
                    }
                    break;

                default:
                    System.out.println("\t added: " + line);
                    storage.add(line);
            }

            System.out.println("\t____________________________________________________________");
        }
    }
}
