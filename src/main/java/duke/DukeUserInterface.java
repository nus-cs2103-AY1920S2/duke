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
    private static final String FAREWELL_CLOSING = "Poyo! (Bye. Hope to see you again soon! UwU)";
    private static final String STARTUP_GREETING = "Poyo! (Hi, I'm Kirby! :3 How may I help you?)";
    private static final String POYO_GREETING = "Poyo!";
    private static final String PIKA_GREETING = "Poyo Poyo! (Pika Pika!)";
    private static final String HELP_MESSAGE = "Here are the list of commands in KirbyLog:\n\n" +
            "    To view the list of tasks:\n    list\n\n" +
            "    To add a Todo Task:\n    todo <description>\n\n" +
            "    To add a Deadline Task:\n    deadline <description> /by <DD/MM/YYYY 2359>\n\n" +
            "    To add an Event Task:\n    event <description> /by <DD/MM/YYYY 2359>\n\n" +
            "    To delete a Task:\n    delete <index of task>\n\n" +
            "    To mark a Task as done:\n    done <index of task>\n\n" +
            "    To find tasks of a certain keyword:\n    find <keyword>";

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

            if (input.equals("bye")) {
                responseMessage = FAREWELL_CLOSING;
            } else if (input.equals("help")) {
                responseMessage = HELP_MESSAGE;
            } else if (input.equals("Greeting")) {
                responseMessage = STARTUP_GREETING;
            } else if (input.equals("poyo")) {
                responseMessage = POYO_GREETING;
            } else if (input.equals("pika")) {
                responseMessage = PIKA_GREETING;
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
