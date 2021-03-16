package duke;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import duke.command.Parser;
import duke.task.TaskList;

/**
 * Driver class for the Duke program.
 * All classes needed for the execution of Duke comes from the DukeUserInterface.
 * Also reads in user input and determines when Duke terminates.
 *
 * @author Dargo
 */
public class DukeUserInterface {

    private static final String LINE = "    ____________________________________________________________";
    private static final String OPENING_GREETING = "     Hello! I'm Duke\n" + "     What can I do for you?";
    private static final String FAREWELL_CLOSING = "Bye. Hope to see you again soon! UwU";
    private static final String STARTUP_GREETING = "Hi, I'm Kirby!\n" +
            "For a list of available commands, enter 'help' :3" + "\n";
    private static final String POYO_GREETING = "Poyo!";
    private static final String PIKA_GREETING = "Pika Pika :3";
    private static final String UWU_GREETING = "UwU";
    private static final String HELLO_GREETING = "Hello :3";
    private static final String BADWORD_GREETING = "BAD WORD! >:(";
    private static final String BENG_GREETING = "eh nbcb knn use app properly lah oi";
    private static final String PCK_GREETING = "SARS is the virus, that I just want to minus.\n" +
            "Aiyah, it's not SARS anymore.\nDon't pray pray please wash your hands with soap hor!!\n";
    private static final String HELP_MESSAGE = "Here are the list of commands in KirbyLog:\n\n" +
            "    To view the list of tasks:\n    list\n\n" +
            "    To add a Todo Task:\n    todo <description>\n\n" +
            "    To add a Deadline Task:\n    deadline <description> /by <DD/MM/YYYY 2359>\n\n" +
            "    To add an Event Task:\n    event <description> /by <DD/MM/YYYY 2359>\n\n" +
            "    To delete a Task:\n    delete <index of task>\n\n" +
            "    To mark a Task as done:\n    done <index of task>\n\n" +
            "    To find tasks of a certain keyword:\n    find <keyword>\n\n" +
            "    To change the priority (high, medium, low) of a task:\n    priority <<index of task> <priority>>\n\n" +
            "    To clear all the tasks in the list:\n    clear\n";

    private InputStreamReader stringReader;
    private BufferedReader commandReader;

    private Storage internalStorage;
    private Parser commandParser;
    private TaskList listOfTasks;

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    /**
     * Serves as the driver class for the Duke program.
     * Contains the data, the parser, and reads in inputs from the user.
     *
     * @param filePath Filepath of saved data in Duke
     * @throws DukeException If commands given in command line are not valid
     */
    public DukeUserInterface(String filePath) throws DukeException{
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE + "\n" + OPENING_GREETING + "\n" + LINE);

        this.stringReader = new InputStreamReader(System.in);
        this.commandReader = new BufferedReader(this.stringReader);
        this.internalStorage = new Storage(filePath);
        this.listOfTasks = new TaskList(this.internalStorage.load());
        this.commandParser = new Parser(this.listOfTasks);
    }

    /**
     *  Runs the Duke program.
     *  Reads in input Strings from the user.
     *  Terminates the program when "bye" command is given.
     */
    public void runDuke() {

        try {
            String input = commandReader.readLine();

            while (!input.equals("bye")) {

                String response = commandParser.executeCommand(input);
                input = commandReader.readLine();
            }

            this.internalStorage.save(this.listOfTasks.getData());
            System.out.println(FAREWELL_CLOSING);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Runs the Duke program with GUI.
     * Reads in input Strings from the user.
     *
     * @param input Input command of the user.
     * @return Response message of the Duke program.
     */
    public String runDukeWithInterface(String input) {

        String responseMessage = "";

        try {

            if (input.toLowerCase().equals("bye")) {
                responseMessage = FAREWELL_CLOSING;
            } else if (input.toLowerCase().equals("help")) {
                responseMessage = HELP_MESSAGE;
            } else if (input.equals("Greeting")) {
                responseMessage = STARTUP_GREETING;
            } else if (input.toLowerCase().equals("poyo")) {
                responseMessage = POYO_GREETING;
            } else if (input.toLowerCase().equals("pika")) {
                responseMessage = PIKA_GREETING;
            } else if (input.toLowerCase().equals("uwu")) {
                responseMessage = UWU_GREETING;
            } else if (input.toLowerCase().equals("hello")) {
                responseMessage = HELLO_GREETING;
            } else if (input.toLowerCase().equals("fuck")) {
                responseMessage = BADWORD_GREETING;
            } else if (input.toLowerCase().equals("nbcb")) {
                responseMessage = BENG_GREETING;
            } else if (input.toLowerCase().equals("wuhan") || input.toLowerCase().equals("coronavirus")) {
                responseMessage = PCK_GREETING;
            } else {
                responseMessage = commandParser.executeCommand(input);
                this.internalStorage.save(this.listOfTasks.getData());
            }

            return responseMessage;

        } catch (Exception e) {
            return e.toString();
        }
    }
}
