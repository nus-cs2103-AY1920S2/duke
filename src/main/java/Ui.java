import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {
    public static final String LOGO = ""
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String INDENTATION = "    ";
    public static final String WELCOME_MESSAGE = String.format(
            "Hello from \n%s",
            Ui.LOGO
    );
    public static final String GOODBYE_MESSAGE = "Goodbye!";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public boolean hasNextInput() {
        return sc.hasNext();
    }

    public String readInput() throws DukeNoSuchInputException {
        try {
            return sc.nextLine();
        } catch (NoSuchElementException e) {
            throw new DukeNoSuchInputException();
        }
    }

    public void print(String message) {
        System.out.println(message);
    }

    public void printWelcome() {
        System.out.println(Ui.WELCOME_MESSAGE);
    }

    public void printException(Exception e) {
        System.out.println(e);
    }

    public void printGoodbye() {
        System.out.println(Ui.GOODBYE_MESSAGE);
    }

    public void close() {
        sc.close();
    }
}