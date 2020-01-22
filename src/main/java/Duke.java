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
        message.setIndentationInFront(4);
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
        message.addSentence("Hello! I'm Duke", 1);
        message.addSentence("What can I do for you?", 1);
        System.out.print(message.replyMessage());
    }

    /**
     * Process the user Input as it should be
     * @param userInput string input by the user
     */
    public static void processUserInput(String userInput) {
        try {
            if (!userInput.equals("bye")) {
                if (userInput.equals("list")) {
                    replyListing();
                } else if (userInput.startsWith("done")) {
                    int num = Integer.parseInt(userInput.substring(5));
                    replyDone(num);
                } else {
                    database.addData(userInput);
                    replyAdded();
                }
            } else {
                reply("Bye. Hope to see you again soon!");
                dialogContinue = false;
            }
        } catch (DukeException e) {
            reply(e.getMessage());
        }
    }

    /**
     * Duke reply one sentence to the user
     * @param sentence message reply to user
     */
    public static void reply(String sentence) {
        message.clearMessage();
        message.addSentence(sentence, 1);
        System.out.print(message.replyMessage());
    }

    /**
     * Reply all the data in the database in list format
     */
    public static void replyListing() {
        message.clearMessage();
        message.addSentence("Here are the tasks in your list:", 1);
        message.addList(database.getListing());
        System.out.print(message.replyMessage());
    }

    /**
     * Reply the user that respective task has been added to the list
     */
    public static void replyAdded() {
        int AmtOfTask = database.getAmountOfTask();
        message.clearMessage();
        message.addSentence("Got it. I've added this task:", 1);
        message.addSentence(database.getTask(AmtOfTask).toString(), 3);
        if (AmtOfTask > 1) {
            message.addSentence("Now you have "+ AmtOfTask +" tasks in the list.", 1);
        } else {
            message.addSentence("Now you have 1 task in the list.", 1);
        }
        System.out.print(message.replyMessage());
    }

    /**
     * Reply the user that the task has marked done
     * @param num index where the task located
     */
    public static void replyDone(int num) {
        database.markDone(num);
        Task task = database.getTask(num);
        message.clearMessage();
        message.addSentence("Nice! I've marked this task as done:", 1);
        message.addSentence("  " + task.toString(), 3);
        System.out.print(message.replyMessage());
    }
}
