import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);
    String entry = "";
    final String linedivider = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";

    public String input() {
        return sc.nextLine();
    }


    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(linedivider + "Hello! I'm Duke\nWhat can I do for you?\n" + linedivider);
    }

    public void bye() {
        System.out.println(linedivider + "Bye. Hope to see you again soon!" + "\n" + linedivider);
    }
}
