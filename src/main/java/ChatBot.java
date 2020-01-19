import java.util.Date;
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
        StringBuilder taskname = new StringBuilder();
        StringBuilder DateTime = new StringBuilder();
        int index_found = 0;
        switch(inputCommand[0]) {
            case "todo":
                for (int i = 1; i < inputCommand.length; i++) {
                    taskname.append(inputCommand[i]);
                    if (i != inputCommand.length - 1) {
                        taskname.append(" ");
                    }
                }
                ToDo t = new ToDo(taskname.toString());
                this.tasks.add(t);
                this.prettyPrinting(taskname.toString() + " added!");
                break;
            case "deadline":
                index_found = this.grabTaskName(taskname, inputCommand, "/by");
                this.grabDateTime(index_found, inputCommand ,DateTime);
                Deadline d = new Deadline(taskname.toString(), DateTime.toString());
                this.tasks.add(d);
                this.prettyPrinting(taskname.toString() + " added!");
                break;
            case "event":
                index_found = this.grabTaskName(taskname, inputCommand, "/at");
                this.grabDateTime(index_found, inputCommand, DateTime);
                Event e = new Event(taskname.toString(), DateTime.toString());
                this.tasks.add(e);
                this.prettyPrinting(taskname.toString() + " added!");
                break;
            case "bye":
                this.prettyPrinting("Bye. Hope to see you again soon!");
                return false;
            case "done":
                int taskToBeDone = Integer.parseInt(inputCommand[1]); // all assuming correct input
                try {
                    Task T = this.tasks.get(Integer.parseInt(inputCommand[1]) - 1);
                    if (T.getDone()) {
                        this.prettyPrinting("Task already set done!");
                    } else {
                        T.setDone();
                        this.prettyPrinting("Task set to done!");
                    }
                } catch (IndexOutOfBoundsException E) {
                    this.prettyPrinting("I believe you gave an incorrect task number! Please try again!");
                }
                break;
            case "list":
                int counter = 1;
                String listings = "";
                for (Task task : this.tasks) {
                    listings += counter + "." + task.toString();
                    if (counter != this.tasks.size()) {
                        listings += "\n\t";
                    }
                    counter++;
                }
                this.prettyPrinting(listings);
                break;
            default:
                this.prettyPrinting("Invalid command! Please try again!");
        }
        return true;
    }

    /**
     * Function to split the input query and grab the task name, also returns the index where the at/by will be at
     * @param taskname
     * @param inputCommand
     * @param delimiter
     * @return index for the by/at depending on the type of task, not applicable for to-do tasks
     */
    public int grabTaskName(StringBuilder taskname, String[] inputCommand, String delimiter) {
        int index_found = 0; //find the index for the delimiter
        for (int i = 1; i < inputCommand.length - 1; i++) {
            if (inputCommand[i].equals(delimiter)) {
                index_found = i;
                break;
            } else {
                taskname.append(inputCommand[i]);
                if (inputCommand[i + 1].equals(delimiter)) {
                    index_found = i + 1;
                    break;
                } else {
                    taskname.append(" ");
                }
            }
        }
        return index_found;
    }

    /**
     * Function to grab and get the date time for the event/deadline
     * @param index_found
     * @param inputCommand
     * @param DateTime
     */
    public void grabDateTime(int index_found, String[] inputCommand, StringBuilder DateTime) {
        for (int i = index_found + 1; i < inputCommand.length; i++) {
            DateTime.append(inputCommand[i]);
            if (i != inputCommand.length - 1) {
                DateTime.append(" ");
            }
        }
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
