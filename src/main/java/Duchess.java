import java.util.ArrayList;
import java.util.Scanner;

class Duchess {
    private ArrayList<String> strings;
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
            sayGoodbye();
            break;
        default:
            addToList(command);
            break;
        }
    }

    private void introduce() {
        String logo = " _____             _                   \n"
                + "|  __ \\           | |                  \n"
                + "| |  | |_   _  ___| |__   ___  ___ ___ \n"
                + "| |  | | | | |/ __| '_ \\ / _ \\/ __/ __|\n"
                + "| |__| | |_| | (__| | | |  __/\\__ \\__ \\\n"
                + "|_____/ \\__,_|\\___|_| |_|\\___||___/___/";
        System.out.println("\tHello from\n" + logo);
        System.out.println("\tMy name is Duchess, as you can see above.");
        System.out.println("\tHow may I help you?");
    }

    private void echo(String input) {
        System.out.println("\tOh? You said \"" + input + "\"? How interesting.");
        System.out.println("\tAnything else?");
    }

    private void addToList(String input) {
        echo(input);
        this.strings.add(input);
        awaitInput();
    }

    private void printList() {
        System.out.println("\tSighs... you never remember what you say, don't you.");
        System.out.println("\tYou said these:");
        for (int i = 0; i < this.strings.size(); i++) {
            System.out.println("\t\t" + (i + 1) + ".\t" + this.strings.get(i));
        }
        awaitInput();
    }

    private void sayGoodbye() {
        System.out.println("\tBye, is it? Shoo shoo then.");

    }

    void run() {
        introduce();
        awaitInput();
    }
}
