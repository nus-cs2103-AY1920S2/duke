import java.util.Scanner;

public class Ui {

    Scanner sc;

    public Ui() {
        Scanner scanner = new Scanner(System.in);
        this.sc = scanner;
    }

    public void showWelcome() {
        String intro = "--------------------------------------------------\n" +
                "Wassup! Wo shi bot \n" +
                "You want what? \n" +
                "--------------------------------------------------\n";

        System.out.print(intro);
    }

    public void goodBye() {
        String goodbye = "--------------------------------------------------\n" +
                "Why you so ba...bot has been killed\n" +
                "--------------------------------------------------\n";

        System.out.print(goodbye);
    }

    public void showLoadingError() {
        System.out.println("Error loading file");
    }

    public void showError(Exception e) {
        System.out.println(e.toString());
    }

    public String readInput() {
        String input = sc.nextLine();
        return input;
    }
}
