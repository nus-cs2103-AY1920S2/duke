import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList dukeList = new ArrayList<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Greetings, I am\n" + logo);
        System.out.println("How may I be of assistance today?");

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < dukeList.size(); i++){
                    System.out.println(i + 1 + ". " + dukeList.get(i));
                }
                command = sc.nextLine();
                continue;
            }
            dukeList.add(command);
            System.out.println("added: " + command);
            command = sc.nextLine();
        }

        System.out.println("It was my pleasure to help you.\n");

    }
}
