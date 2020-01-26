import java.util.Scanner;

public class Ui {
    private String divider = "    ____________________________________________________________";
    private String welcomeMessage = "";
    private String exitMessage = "";
    Scanner scanner = new Scanner(System.in);

    public Ui(String welcomeMessage, String exitMessage) {
        this.welcomeMessage = welcomeMessage;
        this.exitMessage = exitMessage;
        Scanner scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void printWelcomeMessage() {
        printDividerLine();
        System.out.println(welcomeMessage);
        printDividerLine();
    }

    public void printExitMessage() {
        printDividerLine();
        System.out.println(exitMessage);
        printDividerLine();
    }

    public void printMessage(String message) {
        printDividerLine();
        System.out.print(message);
        printDividerLine();
    }

    public void printMessageNoDivider(String message) {
        System.out.print(message);
    }

    public void printTask(Task task) {
        System.out.println(task);
    }

    public void printException(DukeException e) {
        printDividerLine();
        System.out.println(e.getMessage());
        printDividerLine();
    }

    public void printDividerLine() {
        System.out.println(divider);
    }
}
