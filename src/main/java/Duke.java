import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("May I know your name?");

        String name = scanner.nextLine();
        ArrayList<String> storedInfo = new ArrayList<>();

        while (true) {
            System.out.println("How may I help you " + name + "?");
            String command = scanner.nextLine();
            if (command.toLowerCase().equals("list")) {
                for (int currNum = 1; currNum <= storedInfo.size(); currNum++) {
                    System.out.println(currNum + ". " + storedInfo.get(currNum - 1));
                }
            }
            else if (command.toLowerCase().equals("bye")) {
                System.out.println("Adios amigo. It was my pleasure assisting you. Keep smiling " + name + ".");
                break;
            } else {
                storedInfo.add(command);
                System.out.println("added: " + command);
            }
        }
    }
}
