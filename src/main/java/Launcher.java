import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        String logo = "                _        _____                                 _           \n" +
                "     /\\        | |      |  __ \\                               | |          \n" +
                "    /  \\  _   _| |_ ___ | |__) |___  ___ _ __   ___  _ __   __| | ___ _ __ \n" +
                "   / /\\ \\| | | | __/ _ \\|  _  // _ \\/ __| '_ \\ / _ \\| '_ \\ / _` |/ _ \\ '__|\n" +
                "  / ____ \\ |_| | || (_) | | \\ \\  __/\\__ \\ |_) | (_) | | | | (_| |  __/ |   \n" +
                " /_/    \\_\\__,_|\\__\\___/|_|  \\_\\___||___/ .__/ \\___/|_| |_|\\__,_|\\___|_|   \n" +
                "                                        | |                                \n" +
                "                                        |_|                                ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What do you wish to do?");

        AutoResponder ar = AutoResponder.initialise();
        Scanner sc = new Scanner(System.in);
        String input = "";

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            System.out.println("--------------------------------------------");
            if (input.matches("bye\\s*")) {
                System.out.println("Farewell. Thank you for using AutoResponder.");
                System.out.println("--------------------------------------------");
                break;
            }
            try {
                ar = ar.readInput(input).printToConsole();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("--------------------------------------------");
        }
    }
}
