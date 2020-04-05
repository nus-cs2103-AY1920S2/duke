package sharadhr.duke;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javafx.application.Platform;
import sharadhr.duke.command.Command;
import sharadhr.duke.exception.DukeEmptyDetailException;
import sharadhr.duke.exception.DukeInvalidArgumentException;
import sharadhr.duke.exception.DukeInvalidCommandException;
import sharadhr.duke.exception.DukeInvalidDateTimeException;
import sharadhr.duke.io.Input;
import sharadhr.duke.io.Output;
import sharadhr.duke.io.Storage;
import sharadhr.duke.task.TaskList;

/**
 *
 */
public class Duke
{
    public static TaskList tasks;
    public static Input input;
    public static Output output;
    public static Storage fileRW;
    public static PrintStream outputPS;
    public static ByteArrayInputStream baIS;
    public static ByteArrayOutputStream baOS;

    public Duke(String... filePath)
    {
        fileRW = new Storage(filePath);
        assert fileRW != null || input != null || output != null;
        
        baOS = new ByteArrayOutputStream();
        try
        {
            outputPS = new PrintStream(baOS, true, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            ;
        }

        System.setOut(outputPS);
        output = new Output(outputPS);

        input = new Input();

        tasks = fileRW.loadFromFile();
    }

    /**
     * Runs the main program loop.
     *
     * @return {@code false} when the user says 'bye'; otherwise, never returns.
     */
    public static boolean programLoop() {
        boolean isExit = false;
        while (!isExit) {
            try {
                Optional<Command> possibleCommand = input.nextLine().getCommand();
                if (!possibleCommand.isPresent()) {
                    output.say("Input cannot be empty; please enter a command.");
                    continue;
                }
                possibleCommand.get().execute(tasks, fileRW, output);
                isExit = possibleCommand.get().willTerminate();
            }
            catch (DukeInvalidArgumentException | DukeInvalidCommandException | DukeEmptyDetailException | DukeInvalidDateTimeException e) {
                output.sayError(e);
            }
        }
        return isExit;
    }

    /**
     * Cleans up objects and quits the program by calling {@link System#exit(int)}.
     */
    public static void exit() {
        output.sayGoodBye();

        input.close();
        output.close();
        Platform.exit();
        System.exit(0);
    }

    public static void main(String[] args) {
        //new Duke("data", "duke.csv");

        output.sayHello();

        // Creates the task list

        if (programLoop()) {
            exit();
        }
    }

    public String getResponse()
    {
        
        boolean isExit = false;
        try {
            Optional<Command> possibleCommand = input.getCommand();
            if (!possibleCommand.isPresent()) {
                output.say("Input cannot be empty; please enter a command.");
            }
            else
            {
                possibleCommand.get().execute(tasks, fileRW, output);
                isExit = possibleCommand.get().willTerminate();
            }
        }
        catch (DukeInvalidArgumentException | DukeInvalidCommandException | DukeEmptyDetailException | DukeInvalidDateTimeException e) {
            output.sayError(e);
        }

        if (isExit) {
            exit();
        }

        String returnable = baOS.toString();
        baOS.reset();

        return returnable;
    }

    public void redirect(String inputString) {
        input.setFromString(inputString);
    }
}
