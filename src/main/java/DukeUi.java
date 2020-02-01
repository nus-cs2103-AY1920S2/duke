import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class DukeUi {
    private Scanner in;
    private PrintStream out;

    public DukeUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void greet() {
        out.println("Hello! I'm Duke");
        out.println("By default, your list of tasks will be saved to \"tasks.txt\".");
        out.println("What can I do for you?");
        out.println();
    }


    public void printByeMsg() {
        System.out.println("Bye. Hope to see you again soon! :)");
    }

}
