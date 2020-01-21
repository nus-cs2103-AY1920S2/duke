import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Boxer sword = new Boxer();
        sword.greet();

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        do {
            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ");

            /*
            At this moment switch block cannot catch invalid done cases
            eg "done work" will be treated as done block rather than default (add)
             */
            switch (inputSplit[0]) {
                case "bye":
                    sword.exit();
                    loop = false;
                    break;
                case "list":
                    sword.list();
                    break;
                case "done":
                    int index;
                    index = Integer.parseInt(inputSplit[1]);
                    sword.done(index);
                    break;
                default:
                    sword.add(input);
                    break;
            }
        } while (loop);

    }
}
