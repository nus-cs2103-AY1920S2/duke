import java.io.InputStream;
import java.util.Scanner;

/**
 * Represents an interface to facilitate user input and output.
 */
public class Ui {
    private Scanner sc;

    public Ui() {}

    /**
     * Prints string.
     *
     * @param s String to print.
     */
    public void print(String s) {
        System.out.println(s);
    }

    /**
     * Prints error message.
     *
     * @param e Exception to print.
     */
    public void showError(Exception e) {
        System.out.println("Something went wrong: " + e.getMessage());
    }

    /**
     * Sets input stream.
     *
     * @param in Input stream to set.
     */
    public void setInput(InputStream in) {
        sc = new Scanner(in);
    }

    /**
     * Returns next line from input stream.
     *
     * @return Next line from input stream.
     */
    public String getLine() {
        return sc.nextLine();
    }
}
