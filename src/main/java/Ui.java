import java.util.Scanner;

/** deals with interactions with the user */
public class Ui {
    Scanner in = new Scanner(System.in);
    String arguments;

    public boolean hasNextInput() {
        return in.hasNext();
    }

    public String getCommand() {
        String[] command = in.nextLine().split(" ", 2);
        if (command.length > 1) {
            arguments = command[1];
        }
        return command[0];
    }

    public String getArguments() {
        if (arguments == null) {
            return "";
        } else {
            String arg = arguments;
            arguments = null;
            return arg;
        }
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