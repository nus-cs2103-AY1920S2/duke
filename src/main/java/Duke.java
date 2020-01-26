import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I 'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________");

        Scanner myScanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        while (true) {

            String word = myScanner.nextLine();
            System.out.println("________________________________________");

            if (word.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("________________________________________");
                break;
            } else if (word.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i));
                }
                System.out.println("________________________________________");

            } else {

                list.add(word);
                System.out.println("added: " + word);
                System.out.println("________________________________________");
            }
        }
    }
}
