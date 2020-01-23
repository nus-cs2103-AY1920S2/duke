import java.util.Scanner;

public class Duke {

    private Scanner sc;

    public Duke() {
        this.sc = new Scanner(System.in);
    }

    private void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    private void echo(String input) {
        System.out.println("    " + input);
        this.waitInput();
    }

    private void waitInput() {
        String input = sc.nextLine();
        switch (input) {
            case "bye":
                System.out.println("    Bye. Hope to see you again soon!");
                break;
            default:
                echo(input);
                break;
        }
    }

    public void run() {
        this.intro();
        this.waitInput();
    }
}
