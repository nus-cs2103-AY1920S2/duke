import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Duke {
    //private static Scanner sc = new Scanner(System.in);

    /**
     * Greets the user. Passes control to parser to deal with user inputs until the user is done. Then says bye.
     * @param args
     */
    public static void main(String[] args) {
        Parser parser = new Parser();
        Ui.greet();

        parser.parse();

        Ui.sayBye();




    }
}
