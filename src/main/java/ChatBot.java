import java.util.Scanner;
import java.util.ArrayList;

public class ChatBot {
   // class for the chat-bot for the Duke Project

    private String decoration = "\t************************"; //decoration for the response
    private String greetings =  " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    private ArrayList<Task> tasks = new ArrayList<>(); // store the list of tasks from the user

    /**
     * Function to greet user
     */
    public void greetUser() {
        System.out.println(this.greetings);
        System.out.println("Hello! I am Duke, and I am pleased to serve you!");
        System.out.println("Here are the following commands (case sensitive):");
        System.out.println("1) list: List out all the tasks added");
        System.out.println("2) done x: Set the task number (x) to done");
        System.out.println("3) Any other text: add that text to the task list!");
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
        // split the string
        String[] inputCommand = command.split(" ");
        switch(inputCommand[0]) {
            case "list":
                int counter = 1;
                String line = "";
                for (Task t : this.tasks) {
                    if (t.getDone()) {
                        line += counter + " [" + "\u2713" + "] " + t.getTaskname();
                    } else {
                        line += counter + " [" + "X" + "] "  + t.getTaskname();
                    }
                    if (counter != this.tasks.size()) {
                        line += "\n";
                        line += "\t";
                    }
                    counter++;
                }
                // now we do the pretty printing to get the right output
                this.prettyPrinting(line);
                break;
            case "bye":
                this.prettyPrinting("Bye. Hope to see you again soon!");
                return false;
            case "done":
                int taskToBeDone = Integer.parseInt(inputCommand[1]); // all assuming correct input
                try {
                    Task t = this.tasks.get(Integer.parseInt(inputCommand[1]) - 1);
                    if (t.getDone()) {
                        this.prettyPrinting("Task already set done!");
                    } else {
                        t.setDone();
                        this.prettyPrinting("Task set to done!");
                    }
                } catch (IndexOutOfBoundsException e) {
                    this.prettyPrinting("I believe you gave an incorrect task number! Please try again!");
                }
                break;
            default:
                Task t = new Task(command);
                this.tasks.add(t);
                this.prettyPrinting("added: " + command);
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
        while (sc.hasNextLine()) {
            inputCommand = sc.nextLine();
            continueRunning = respondToUser(inputCommand);
            if (!continueRunning) {
                break;
            }
        }
    }

}
