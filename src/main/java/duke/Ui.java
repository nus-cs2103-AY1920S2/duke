package duke;

import java.util.Scanner;

/** deals with interactions with the user */
public class Ui {
    Scanner in = new Scanner(System.in);
    String arguments;

    public boolean hasNextInput() {
        return in.hasNext();
    }

    public String getLine() {
        return in.nextLine();
    }

    public void out(String... ss) {
        String border = "    ____________________________________________________________";
        System.out.println(border);
        for (String s : ss) {
            System.out.println("    " + s);
        }
        System.out.println(border);
    }

    public void close() {
        in.close();
    }
}