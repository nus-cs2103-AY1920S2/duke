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
 * <p>Argument class is used for storing the commands
 * entered by the client. The Argument stores the keyword,
 * and the details of the command, such as the description,
 * and the date of the task, if applicable.</p>
 * @author Mario Lorenzo
 */

public class Argument {
    private Keyword keyword;
    private String details;

    /**
     * Constructs the Argument instance that takes in a Command enum
     * and the details of the command entered by the client. The method
     * is private, which is intended to be called by the factory method.
     * @param keyword The keyword enum, representing the keyword of the command.
     * @param details The details, which are the second word, onwards of the command entered.
     */

    public Argument(Keyword keyword, String details) {
        this.keyword = keyword;
        this.details = details;
    }

    /**
     * getCommand method is used for other classes to retrieve
     * the Command enum of the Argument instance.
     * @return The corresponding command enum of an Argument.
     */

    public Keyword getKeyword() {
        return this.keyword;
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
