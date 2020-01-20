import java.util.Scanner;

class Duchess {

    private void introduction() {
        String logo = " _____             _                   \n"
                + "|  __ \\           | |                  \n"
                + "| |  | |_   _  ___| |__   ___  ___ ___ \n"
                + "| |  | | | | |/ __| '_ \\ / _ \\/ __/ __|\n"
                + "| |__| | |_| | (__| | | |  __/\\__ \\__ \\\n"
                + "|_____/ \\__,_|\\___|_| |_|\\___||___/___/";
        System.out.println("Hello from\n" + logo);
        System.out.println("My name is Duchess, as you can see above.");
        System.out.println("How may I help you?");
    }

    private void echo(String input) {
        System.out.println("Oh? You said \"" + input + "\"? Interesting.");
        System.out.println("Anything else?");
    }

    private void goodbye() {
        System.out.println("Bye, is it? Shoo shoo then.");

    }

    void run() {
        introduction();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.toLowerCase().equals("bye")) {
            echo(input);
            input = scanner.nextLine();
        }
        goodbye();
    }
}
