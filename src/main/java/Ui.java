import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showWelcome() {
        String greeting = "Hello! I'm Woody and I'm always here to keep you company.\n"
                + "Let me know what you need and I'll be right on it.\n";
        System.out.println(greeting);
    }

    public void showLine() {
        System.out.println("_______________________________________________________________________");
    }

    public void showMsg(String msg) {
        System.out.println(msg);
    }

    public void showError(String errorMsg) {
        System.out.println("OOPS!!! " + errorMsg);
    }

    public void showLoadingError() {
        System.out.println("OOPS!!! Error when loading tasks from file to Woody.");
    }
}
