import java.util.Scanner;

/**
 * Duke class to contain the main function for execution.
 */
public class Duke {

    /**
     * Main driver method.
     * @param args arguments.
     */
    public static void main(String[] args) {
        // add a new chatbot for users
        ChatBot duke = new ChatBot();
        Scanner sc = new Scanner(System.in);
        // run the chat-bot
        duke.runChatBot(sc);
    }
}
