import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    /**
     * Main method of Duke. It initialises the bot and reads user input.
     * @param args No args applicable.
     */
    public static void main(String[] args) {
        DukeBot duke = new DukeBot();
        duke.dukeHi();

        Scanner sc = new Scanner(System.in);

        while(duke.isActive()) {
            String command = sc.nextLine();
            duke.processCommand(command);
        }
    }
}
