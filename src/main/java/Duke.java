import java.io.*;
import java.util.ArrayList;

public class Duke {

    private final static String filepath =
            "..\\..\\..\\duke.txt";

    public static void main(String[] args) throws DukeException{

        // DukeUserInterface acts as a driver class for Duke
        DukeUserInterface UI = new DukeUserInterface(filepath);
        UI.runDuke();
    }
}
