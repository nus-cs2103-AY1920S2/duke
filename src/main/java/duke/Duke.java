package seedu.duke;

import java.util.Scanner;

public class Duke {
    private Scanner sc;
    public static final String separator = "--------------------------------";

    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may duke serve the master today?\n");
        Duke bot = new Duke();
        bot.start();
    }

    public Duke() {
        this.sc = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye"))
                break;
            echo(input);
        }
        echo("Bye. Hope to see you again soon!");
    }

    private void echo(String toEcho) {
        System.out.println(Duke.separator);
        System.out.println(toEcho);
        System.out.println(Duke.separator);
    }

}
