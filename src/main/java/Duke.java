import java.util.Scanner;

public class Duke {
    static String indentation = "       ";
    static int numOfIndentation = 7;
    static Database database;
    static ReplyFormat message;
    static String userInput;
    static Scanner user;
    static boolean dialogContinue;

    /**
     * Main program starts here.
     * @param args User's input from console
     */
    public static void main(String[] args) {
        initialise();
        greet();
        while (dialogContinue) {
            userInput = getUserInput();
            System.out.println(userInput);
            processUserInput(userInput);
        }
    }

    /**
     * Initialise scanner and reply format
     */
    public static void initialise() {
        message = new ReplyFormat();
        message.setIndentationInFront(7);
        database = new Database();
        user = new Scanner(System.in);
        userInput = "";
        dialogContinue = true;
    }

    /**
     * Loop until user give input
     * @return the sentence user inputs
     */
    public static String getUserInput() {
        while (true) {
            if (user.hasNext()) {
                return user.nextLine();
            }
        }
    }

    /**
     * Duke greet with logo
     */
    public static void greetWithLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Duke greet the user
     */
    public static void greet() {
        message.addSentence("Hello! I'm Duke");
        message.addSentence("What can I do for you?");
        System.out.print(message.replyMessage());
    }

    /**
     * Process the user Input as it should be
     * @param userInput string input by the user
     */
    public static void processUserInput(String userInput) {
        if (userInput != null) {
            if (!userInput.equals("bye")) {
                if (userInput.equals("list")) {
                    replyListing();
                } else {
                    database.addData(userInput);
                    reply("added: " + userInput);
                }
            } else {
                reply("Bye. Hope to see you again soon!");
                dialogContinue = false;
            }
        } else {
            System.out.println("Null encountered during user input!");
        }
    }

    /**
     * Duke reply the message given to the user
     * @param sentence message reply to user
     */
    public static void reply(String sentence) {
        message.clearMessage();
        message.addSentence(sentence);
        System.out.print(message.replyMessage());
    }

    /**
     * Reply all the data in the database in list format
     */
    public static void replyListing() {
        message.clearMessage();
        message.addList(database.getListing());
        System.out.print(message.replyMessage());
    }
}
