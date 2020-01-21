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
            String[] inputSplit = input.split(" ", 2);

            /*
            At this moment switch block cannot catch invalid cases
            eg "done work" will be treated as done block rather than default (add)
             */
            switch (inputSplit[0]) {
                case "todo":
                    sword.todo(inputSplit[1]);
                    break;
                case "deadline":
                    sword.deadline(inputSplit[1]);
                    break;
                case "event":
                    sword.event(inputSplit[1]);
                    break;
                case "list":
                    sword.list();
                    break;
                case "done":
                    int index;
                    index = Integer.parseInt(inputSplit[1]);
                    sword.done(index);
                    break;
                case "bye":
                    sword.exit();
                    loop = false;
                    break;
                default:
                    sword.print("Uhh... You're gonna have to say that again, Red.");
                    break;
            }
        } while (loop);

    }
}
