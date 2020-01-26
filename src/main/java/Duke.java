import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Duke {
    //private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Ui.greet();

        Parser.parse();

        Ui.sayBye();




    }
}
