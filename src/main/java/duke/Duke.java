package duke;

import duke.filemanager.FileManager;
import duke.task.Task;

import java.util.List;
import java.util.Scanner;

public class Duke {
    static int numOfIndentation = 4;
    static Database database;
    static ReplyFormat message;
    static String userInput;
    static Scanner user;
    static boolean dialogContinue;
    static FileManager fileManager;

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
        closeScanners();
    }

    /**
     * Initialise scanner and reply format
     */
    public static void initialise() {
        message = new ReplyFormat();
        message.setIndentationInFront(numOfIndentation);
        database = new Database();
        user = new Scanner(System.in);
        userInput = "";
        dialogContinue = true;
        try {
            fileManager = new FileManager();
            fileManager.getTaskListing(database);
        } catch(DukeException e) {
            reply(e.getMessage());
        }
    }

    /**
     * Close all scanners if available
     */
    public static void closeScanners() {
        user.close();
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
     * duke.Duke greet the user
     */
    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        message.clearMessage();
        message.addSentence("Hello! I'm ", 1);
        message.addParagraph(logo, 15);
        message.addEmptyLine();
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
                } else if (userInput.startsWith("delete")) {
                    try {
                        int num = Integer.parseInt(userInput.substring(7).strip());
                        Task task = database.deleteTask(num);
                        fileManager.saveTasks(database);
                        replyDelete(task);
                    } catch(NumberFormatException e) {
                        throw new DukeException("Please input in this format: delete [number]");
                    }
                }else if (userInput.startsWith("done")) {
                    try{
                        int num = Integer.parseInt(userInput.substring(5).strip());
                        database.markDone(num);
                        fileManager.saveTasks(database);
                        replyDone(num);
                    } catch(NumberFormatException e) {
                        throw new DukeException("Please input in this format: done [number]");
                    }
                } else {
                    database.addData(userInput);
                    fileManager.saveTasks(database);
                    replyAdded();
                }
            } else {
                reply("Alright! See you next time!");
                dialogContinue = false;
            }
        } catch (DukeException e) {
            reply(e.getMessage());
        }
    }

    /**
     * duke.Duke reply one sentence to the user
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
        List<Task> tasks = database.getListing();
        message.clearMessage();
        if (tasks.isEmpty()) {
            message.addSentence("Horray! You do not have any task now!", 1);
        } else {
            message.addSentence("Here are the tasks in your list:", 1);
            message.addList(tasks);
        }
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
        Task task = database.getTask(num);
        message.clearMessage();
        message.addSentence("Nice! I've marked this task as done:", 1);
        message.addSentence("  " + task.toString(), 3);
        System.out.print(message.replyMessage());
    }

    /**
     * Reply the user that the task has deleted
     * @param task task that being deleted
     */
    public static void replyDelete(Task task) {
        message.clearMessage();
        message.addSentence("Noted. I've removed this task:", 1);
        message.addSentence("  " + task.toString(), 3);
        System.out.print(message.replyMessage());
    }
}
