import java.io.FileNotFoundException;

/**
 * Duke is our main client class. Duke allows for extensibility by allowing
 * multiple ChatBots to be run if required.
 */
public class Duke {
    public static void main(String[] args) throws FileNotFoundException {
        new ChatBot().run();
    }
}
