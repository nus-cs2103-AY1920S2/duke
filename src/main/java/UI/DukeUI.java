package UI;

import DukeExceptions.DukeException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class DukeUI {
    private final Scanner in;
    private final PrintStream out;
    private final String line = "    ____________________________________________________________";
    private final String logo =
            "     ____        _        \n"
            + "    |  _ \\ _   _| | _____ \n"
            + "    | | | | | | | |/ / _ \\\n"
            + "    | |_| | |_| |   <  __/\n"
            + "    |____/ \\__,_|_|\\_\\___|\n";
    public DukeUI() {
        this(System.in, System.out);
    }

    public DukeUI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printLine() {
        System.out.println(line);
    }

    public void printCustomMessage(String customM) {
        System.out.println(customM);
    }

    public void showWelcomeMessage() {
        printLine();
        printCustomMessage(logo);
        System.out.println("    Hello I'm Duke");
        System.out.println("    What can I do for you?");
        printLine();
    }

    public String readCommandString() {
        return in.nextLine();
    }

    public void showErrorMessage(DukeException e) {
        System.out.println("    " + e.getMessage());
        printLine();
    }
}
