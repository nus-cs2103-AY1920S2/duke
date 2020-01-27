import java.util.HashMap;
import java.util.Optional;

/*
 * Argument
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 23 Jan 2020
 *
 */

/**
 * <p>Argument class is used for representing the command
 * entered by the client. The Argument stores the command,
 * and the details of the command, such as the description,
 * and the date of the task, if applicable.</p>
 * @author Mario Lorenzo
 */

public class Argument {
    private Command command;
    private String details;
    private static HashMap<String, Command> valid_commands = new HashMap<>() {
        {
            put("list", Command.LIST);
            put("done", Command.DONE);
            put("todo", Command.TODO);
            put("deadline", Command.DEADLINE);
            put("event", Command.EVENT);
            put("delete", Command.DELETE);
        }
    };

    /**
     * Constructs the Argument instance that takes in a Command enum
     * and the details of the command entered by the client. The method
     * is private, which is intended to be called by the factory method.
     * @param command The command enum, representing the keyword of the command.
     * @param details The details, which are the second word, onwards of the command entered.
     */

    private Argument(Command command, String details) {
        this.command = command;
        this.details = details;
    }

    /**
     * createArgument static method is a factory method to create an Argument
     * instance. If the command keyword is a valid keyword, it will return an
     * Argument instance. Otherwise, it will throw an exception.
     * @param commands The command string entered by the client.
     * @return An Argument instance, initialized by the constructor.
     * @throws DukeUnknownKeywordException If the command keyword (the first word) is invalid.
     */

    public static Argument createArgument(String commands) throws DukeUnknownKeywordException {
        String[] splitted_commands = commands.split(" ");
        String command_string = splitted_commands[0];
        /*
         If optional_command is empty, it means that the command is not found.
         Therefore, it will throw the exception to inform the client.
         */

        Optional<Command> optional_command = getOptionalCommand(command_string);
        Command keyword = optional_command.orElseThrow(() ->
                new DukeUnknownKeywordException("☹ OOPS!!! I'm sorry, " +
                        "but I don't know what that means :-("));
        String details = splitted_commands.length > 1 ?
                commands.split(" ", 2)[1] :
                "";
        return new Argument(keyword, details);
    }

    /**
     * getOptionalCommand method gets the Command enums of the corresponding
     * command string provided. Optional instances are used to handle cases
     * where the command string entered by the client is invalid, thus returning
     * null value.
     * @param command_string The command provided by the client to be processed.
     * @return The corresponding Command enums, packed in form of an Optional instance.
     */

    public static Optional<Command> getOptionalCommand(String command_string) {
        return Optional.ofNullable(valid_commands.get(command_string));
    }

    /**
     * getCommand method is used for other classes to retrieve
     * the Command enum of the Argument instance.
     * @return The corresponding command enum of an Argument.
     */

    public Command getCommand() {
        return this.command;
    }

    /**
     * checkValidListArgument verifies whether the Argument instance
     * is a valid "list" argument. It will throw an exception if
     * the argument is not valid.
     * @throws DukeInvalidArgumentFormatException If the argument is not a valid argument.
     */

    public void checkValidListArgument() throws DukeInvalidArgumentFormatException {
        if (!this.details.equals("")) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! " +
                    "The argument for 'list' command is invalid.");
        }
    }

    /**
     * checkValidDoneArgument verifies whether the Argument instance
     * is a valid "done" argument. It will throw an exception if
     * the argument is not valid (i.e. if the argument is empty or
     * not a number).
     * @return The index of the done argument, if the argument is valid.
     * @throws DukeInvalidArgumentFormatException If the argument is not valid.
     */

    public int checkValidDoneArgument() throws DukeInvalidArgumentFormatException {
        int index = -1;
        try {
            index = Integer.parseInt(this.details);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! " +
                    "The argument for 'done' command requires a number.");
        }
        return index;
    }

    /**
     * checkValidDeleteArgument verifies whether the Argument instance
     * is a valid "delete" argument. It will throw an exception if
     * the argument is not valid (i.e. if the argument is empty or
     * not a number).
     * @return The index of the delete argument, if the argument is valid.
     * @throws DukeInvalidArgumentFormatException If the argument is not valid.
     */

    public int checkValidDeleteArgument() throws DukeInvalidArgumentFormatException {
        int index = -1;
        try {
            index = Integer.parseInt(this.details);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! " +
                    "The argument for 'delete' command requires a number.");
        }
        return index;
    }

    /**
     * checkValidTodoArgument verifies whether the Argument instance
     * is a valid "todo" argument. It will throw an exception if
     * the argument is not valid (i.e. if the argument has no
     * description).
     * @return The description of the todo argument, if the argument is valid.
     * @throws DukeInvalidArgumentFormatException If the argument is not valid.
     */

    public String checkValidTodoArgument() throws DukeInvalidArgumentFormatException {
        if (this.details.equals("")) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! " +
                    "The argument for 'todo' command requires a description");
        }
        return this.details;
    }

    /**
     * checkValidDeadlineArgument verifies whether the Argument instance
     * is a valid "deadline" argument. It will throw an exception if
     * the argument is not valid (i.e. if the argument has no description,
     * or the due date, or the format of the command is not proper).
     * @return The Pair of String, regarding to the description and the due date.
     * @throws DukeInvalidArgumentFormatException If the argument is not valid.
     */

    public Pair<String, String> checkValidDeadlineArgument() throws DukeInvalidArgumentFormatException {
        String caption;
        String by_schedule;
        String[] details_with_schedule = this.details.split(" /by ");
        try {
            caption = details_with_schedule[0];
            by_schedule = details_with_schedule[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! " +
                    "The argument for 'deadline' " +
                    "command requires a description and a due date.");
        }

        return new Pair<>(caption, by_schedule);
    }

    /**
     * checkValidEventArgument verifies whether the Argument instance
     * is a valid "event" argument. It will throw an exception if
     * the argument is not valid (i.e. if the argument has no description,
     * or the event date, or the format of the command is not proper).
     * @return The Pair of String, regarding to the description and the event date.
     * @throws DukeInvalidArgumentFormatException If the argument is not valid.
     */

    public Pair<String, String> checkValidEventArgument() throws DukeInvalidArgumentFormatException {
        String caption;
        String by_schedule;
        String[] details_with_schedule = this.details.split(" /at ");
        try {
            caption = details_with_schedule[0];
            by_schedule = details_with_schedule[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! " +
                    "The argument for 'event' " +
                    "command requires a description and an event date.");
        }

        return new Pair<>(caption, by_schedule);
    }
}
