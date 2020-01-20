import java.util.ArrayList;
import java.util.Scanner;

class Duchess {
    private ArrayList<String> stringList;
    private Scanner scanner;
    private String command;

    Duchess() {
        this.stringList = new ArrayList<String>(100);
        this.scanner = new Scanner(System.in);
    }

    private void awaitInput() {
        this.command = scanner.nextLine();
        switch (command.toLowerCase()) {
            case "list":
                printList();
                break;
            case "bye":
                goodbye();
                break;
            default:
                addToList(command);
                break;
        }
    }

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
        System.out.println("Oh? You said \"" + input + "\"? How interesting.");
        System.out.println("Anything else?");
    }

    private void addToList(String input) {
        echo(input);
        this.stringList.add(input);
        awaitInput();
    }

    private void printList() {
        System.out.println("Sighs... you never remember what you say, don't you.");
        System.out.println("You said these:");
        for (int i = 0; i < this.stringList.size(); i++) {
            System.out.println((i + 1) + ".\t" + this.stringList.get(i));
        }
        awaitInput();
    }

    private void goodbye() {
        System.out.println("Bye, is it? Shoo shoo then.");

    }

    void run() {
        introduction();
        awaitInput();
    }
}
