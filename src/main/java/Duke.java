import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        ArrayList<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        printLogo();
        printGreeting();

        while (true) {
            String[] input = sc.nextLine().split(" ");
            printDivider();
            if (input[0].toLowerCase().equals("bye")) {
                break;
            } else if (input[0].toLowerCase().equals("list")) {

                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + "." + taskList.get(i));
                }
            } else if (input[0].equals("done")) {
                System.out.println("> Another task off the list. Good job!");
                taskList.get(Integer.parseInt(input[1]) - 1).markAsDone();
                System.out.println("  " + taskList.get(Integer.parseInt(input[1]) - 1));
            } else {
                StringBuilder builder = new StringBuilder(input[0]);
                for (int i = 1; i < input.length; i++) {
                    builder.append(" ");
                    builder.append(input[i]);
                }
                taskList.add(new Task(builder.toString()));
                System.out.println("> Added: " + builder.toString());
            }
            printDivider();
        }
        System.out.println("> It's nice talking to you. See you soon! ;)");
    }

    private static void printDivider() {
        System.out.println("--------------------------------------------");
    }

    private static void printGreeting() {
        System.out.println("Hi! I'm Aelita, guardian of Lyoko.");
        System.out.println("How can I help you?");
        printDivider();
    }

    private static void printLogo() {
        final String logo = "     __             _   _     _\n"
                + "    /  \\           | | / \\   | |\n"
                + "   / /\\ \\     ___  | | \\_/ __| |__   ___ _\n"
                + "  / /__\\ \\   / _ \\ | |  _ |__   __| / _ | |\n"
                + " / ______ \\ |  __/ | | | |   | |   | |_|  |\n"
                + "/_/      \\_\\ \\___\\ |_| |_|   |_|    \\___/\\_\\";
        System.out.println(logo);
        System.out.println("============================================");
    }
}
