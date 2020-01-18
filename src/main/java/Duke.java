import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        ArrayList<String> arrayList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        printLogo();
        printGreeting();

        while (true) {
            String input = sc.nextLine();
            printDivider();
            if (input.toLowerCase().equals("bye")) {
                break;
            } else if (input.toLowerCase().equals("list")) {

                for(int i = 0; i < arrayList.size(); i++) {
                    System.out.println((i + 1) + ". " + arrayList.get(i));
                }
            } else {
                arrayList.add(input);
                System.out.println("> Added: " + input);
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
