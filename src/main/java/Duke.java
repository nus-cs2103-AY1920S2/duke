import java.util.Scanner;

public class Duke {
    private static String line = "    ____________________________________________________________";
    /*
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
     */

    // Main Function of Duke
    public static void main(String[] args) {
        DukeManager dm = new DukeManager();
        dm.run();
    }
}
