import java.util.Scanner;

public class Ui {
    Scanner in = new Scanner(System.in);
    String arguments;

    public boolean hasNext() {
        return in.hasNext();
    }

    public String getCommand() {
        String[] command = in.nextLine().split(" ", 2);
        arguments = command[1];
        return command[0];
    }

    public String getArguments() {
        String arg = arguments;
        arguments = null;
        return arg;
    }

    public void close() {
        in.close();
    }
}