import java.util.Scanner;

/**
 * Duke class to contain the main function for execution.
 */
public class Duke {
    public static void main(String[] args) {
        ChatBot duke = new ChatBot();
        Scanner sc = new Scanner(System.in);
        // run the chat-bot
        duke.runChatBot(sc);
    }
}
