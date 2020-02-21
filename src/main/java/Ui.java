import java.util.Scanner;

public class Ui {
    /**
     * Returns welcome message.
     */
    public static String sayHello() {
        return "Greetings, I am Duke.";
    }

    /**
     * Returns the input provided.
     * @param text Text to be replied by Duke.
     */
    public static String sendReply(String text) {
        assert text != null : "Should not print border without text";
        return text;
    }

    /**
     * Return farewell message.
     */
    public static String sayBye() {
        return "I believe this is farewell, my friend.";
    }

    /**
     * Return message for new user.
     */
    public static String greetNewUser() {
        return "Welcome, new user.\n\nHow can I help you today?";
    }

    /**
     * Prints message for old user.
     */
    public static String greetOldUser() {
        return "Welcome back.\n\nHow can I help you today?";
    }

    /**
     * Reads user input using a Scanner class.
     * @return User input.
     */
    public static String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
