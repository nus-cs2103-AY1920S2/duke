package dukeapp.files;
import dukeapp.parse.Parse;
import java.util.Scanner;

public class Ui {
    Scanner sc;
    String errorMsg;

    public Ui() {}

    public void input(TaskList tasks) {
        sc = new Scanner(System.in);
        Parse parse = new Parse(sc.nextLine());

        while (!parse.decode(tasks)) {
            parse = new Parse(sc.nextLine());
        }
    }

    public void showLoadingError() {
        System.out.println("File not found");
    }
}
