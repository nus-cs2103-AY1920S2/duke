import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public static void showWelcome() {
        String logo = "      ____        _\n"
                + "     |  _ \\ _   _| | _____\n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        showLine();
        System.out.println(logo);
        showLine();
        System.out.println("    Hey there! I'm Duke");
        System.out.println("    What can I do for you?");
        showLine();
    }

    public static void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public boolean hasNextLine() {
        return sc.hasNextLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLoadingError() {
        System.out.println("     ☹ OOPS!!! File not loaded.");
    }

    public void showError(String error) {
        System.out.println("     ☹ OOPS!!! " + error);
    }
}