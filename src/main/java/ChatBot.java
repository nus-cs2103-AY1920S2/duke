import java.util.Scanner;

public class ChatBot {
   // class for the chat-bot for the Duke Project

    private String decoration = "\t************************"; //decoration for the response
    private String greetings =  " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    /**
     * Function to greet user
     */
    public void greetUser() {
        System.out.println(this.greetings);
        System.out.println("Hello! I am Duke, and I am pleased to serve you!");
    }

    /**
     * Function to print any message using some decorations
     * @param message
     */
    public void prettyPrinting(String message) {
        System.out.println(this.decoration);
        System.out.println("\t" + message);
        System.out.println(this.decoration);
    }

    /**
     * The function that allows Chat-bot to respond to user based on response
     * @param command inputted command of the user
     * @return true or false, true means continue chat-bot, false means exit chat-bot
     */
    public boolean respondToUser(String command) {
        switch(command) {
            case "bye":
                this.prettyPrinting("Bye. Hope to see you again soon!");
                return false;
            default:
                this.prettyPrinting(command);
        }
        return true;
    }

    /**
     * Function to run chat-bot and terminate when needed
     * @param sc scanner object that gets the input
     */
    public void runChatBot(Scanner sc) {
        String inputCommand;
        boolean continueRunning = true;
        this.greetUser();
        while (sc.hasNext()) {
            inputCommand = sc.next();
            continueRunning = respondToUser(inputCommand);
            if (!continueRunning) {
                break;
            }
        }
    }

}
