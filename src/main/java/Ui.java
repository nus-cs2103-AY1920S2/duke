import java.util.Scanner;

public class Ui {
    static Scanner sc = new Scanner(System.in);

    /**
     * Prints output string into console.
     * @param output string to be printed
     */
    public void printToConsole(String output) {
        System.out.println("--------------------------------------------");
        System.out.print(output);
        System.out.println("--------------------------------------------");
    }

    /**
     * Prints landing page with name of program.
     */
    public void printLandingPage() {
        String logo = "                _        _____                                 _           \n"
                + "     /\\        | |      |  __ \\                               | |          \n"
                + "    /  \\  _   _| |_ ___ | |__) |___  ___ _ __   ___  _ __   __| | ___ _ __ \n"
                + "   / /\\ \\| | | | __/ _ \\|  _  // _ \\/ __| '_ \\ / _ \\| '_ \\ / _` |/ _ \\ '__|\n"
                + "  / ____ \\ |_| | || (_) | | \\ \\  __/\\__ \\ |_) | (_) | | | | (_| |  __/ |   \n"
                + " /_/    \\_\\__,_|\\__\\___/|_|  \\_\\___||___/ .__/ \\___/|_| |_|\\__,_|\\___|_|   \n"
                + "                                        | |                                \n"
                + "                                        |_|                                ";
        System.out.println("Hello from\n" + logo);
        System.out.println("What do you wish to do?");
    }

    public String receiveCommand() {
        return sc.nextLine();
    }

    public boolean hasCommand() {
        return sc.hasNextLine();
    }

    /**
     * Prints goodbye lines before shutting down program.
     */
    public void printGoodbye() {
        System.out.println("--------------------------------------------");
        System.out.println("Farewell. Thank you for using AutoResponder.");
        System.out.println("--------------------------------------------");
    }
}
