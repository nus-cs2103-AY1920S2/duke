import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {}

    public void print(String s) {
        System.out.println(s);
    }

    public void showError(Exception e) {
        System.out.println("Something went wrong: " + e.getMessage());
    }

    public void setInput(InputStream in) {
        sc = new Scanner(in);
    }

    public String getLine() {
        return sc.nextLine();
    }
}
