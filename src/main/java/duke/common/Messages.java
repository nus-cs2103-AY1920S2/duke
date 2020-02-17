package duke.common;

/**
 * Container and generator for user visible messages.
 */
public class Messages {
    /**
     * The welcome message.
     */
    public static final String WELCOME_MSG =
            "Hello! I'm Duke.\n"
                    + "What can I do for you?";

    /**
     * The message displayed when this list of task is empty.
     */
    public static final String EMPTY_TASK_LIST_MSG =
            "There are currently no tasks in your list.\n"
                    + "Add a task by using one of the following commands:\n"
                    + "\ttodo [description]\n"
                    + "\tdeadline [description] /by [time]\n"
                    + "\tevent [description] /at [time]\n"
                    + "Time should be in the format of 'dd/MM/yyyy HH:mm'";

    /**
     * The message displayed when find command returns no result.
     */
    public static final String EMPTY_FIND_RESULT_MSG = "There are no matching tasks in your list.";

    /**
     * The message displayed when the description of a task to be added is not specified.
     */
    public static final String EMPTY_DESCRIPTION_ERROR_MSG = "You forgot to include a description.";

    /**
     * The message displayed when the time of a task to be added is not specified.
     */
    public static final String EMPTY_TIME_ERROR_MSG = "You forgot to include a time.";

    /**
     * The message displayed when the index specified in the user's input command is invalid.
     */
    public static final String INVALID_INDEX_ERROR_MSG = "This command should only have a valid task index as the "
            + "argument.";

    /**
     * The message displayed when the application is unable to decode the storage file data.
     */
    public static final String INVALID_ENCODING_ERROR_MSG =
            "Encoded task in your storage file has an invalid format.\n"
                    + "I am unable to decode.\n"
                    + "Please exit Duke and delete the corrupted file 'data/duke.txt'";

    /**
     * The message displayed when a wrong command is entered by the user.
     */
    public static final String INVALID_COMMAND_ERROR_MSG =
            "I'm sorry, but I don't know what that means.\n"
                    + "Perhaps you can try typing these commands:\n"
                    + "\ttodo [description]\n"
                    + "\tdeadline [description] /by [time]\n"
                    + "\tevent [description] /at [time]\n"
                    + "\tlist\n"
                    + "\tdone [task_index]\n"
                    + "\tdelete [task_index]\n"
                    + "\tfind [keyword]\n"
                    + "\tundo\n";

    /**
     * Generates and returns the error message when the application is unable to write to the storage file.
     *
     * @param filePath The path of the storage file.
     * @return The error message.
     */
    public static String generateWriteToFileErrorMessage(String filePath) {
        return "Unable to write to file: \n"
                + String.format("\t%s", filePath);
    }

    /**
     * Generates and returns the error message when the application is unable to read from the storage file.
     *
     * @param filePath The path of the storage file.
     * @return The error message.
     */
    public static String generateReadFromFileErrorMessage(String filePath) {
        return "Error reading data from file: \n"
                + String.format("\t%s", filePath);
    }

    /**
     * Generates and returns the success message upon adding a new task to the list.
     *
     * @param task The Task added.
     * @param size The current size of the TaskList.
     * @return The success message.
     */
    public static String generateAddSuccessMessage(String task, int size) {
        return "Got it. I've added this task: \n"
                + String.format("\t%s\n", task)
                + String.format("Now you have %d tasks in the list.", size);
    }


    /**
     * Generates and returns the error message upon failure to add a Todo task.
     *
     * @param message The detail message.
     * @return The error message.
     */
    public static String generateAddTodoErrorMessage(String message) {
        return message + "\n"
                + "To add a todo, tell me in this format:\n"
                + "\ttodo [description]";
    }

    /**
     * Generates and returns the error message upon failure to add a Deadline task.
     *
     * @param message The detail message.
     * @return The error message.
     */
    public static String generateAddDeadlineErrorMessage(String message) {
        return message + "\n"
                + "To add a deadline, tell me in this format:\n"
                + "\ttodo [description] /by [time]\n"
                + "\tTime should be in the format of 'dd/MM/yyyy HH:mm'";
    }

    /**
     * Generates and returns the error message upon failure to add a Event task.
     *
     * @param message The detail message.
     * @return The error message.
     */
    public static String generateAddEventErrorMessage(String message) {
        return message + "\n"
                + "To add an event, tell me in this format: "
                + "\ttodo [description] /at [time]"
                + "\tTime should be in the format of 'dd/MM/yyyy HH:mm'";
    }

    /**
     * Generates and returns the success message upon marking a task as done.
     *
     * @param task The task that is marked as done.
     * @return The success message.
     */
    public static String generateDoneSuccessMessage(String task) {
        return "Nice! I've marked this task as done:\n"
                + String.format("\t%s", task);
    }

    /**
     * Generates and returns the success message upon undoing a mark as as done command.
     *
     * @param task The task that is reverted to not done.
     * @return The success message.
     */
    public static String generateNotDoneSuccessMessage(String task) {
        return "Noted! I've reverted your mark as done command:\n"
                + String.format("\t%s", task);
    }

    /**
     * Generates and returns the error message upon failure to mark a task as done.
     *
     * @param message The detail message.
     * @return The error message.
     */
    public static String generateDoneErrorMessage(String message) {
        return message + "\n"
                + "To mark a task as done, tell me in this format:\n"
                + "\tdone [task_index]";
    }

    /**
     * Generates and returns the error message upon failure to parse the user's input time.
     *
     * @return The error message.
     */
    public static String generateTimeFormatErrorMessage() {
        return "I don't understand the time entered.\n"
                + "Time should be in the format of 'dd/MM/yyyy HH:mm'";
    }

    /**
     * Generates and returns the success message upon deleting a task.
     *
     * @param task The task that is deleted.
     * @param size The current size of the TaskList.
     * @return The success message.
     */
    public static String generateDeleteSuccessMessage(String task, int size) {
        return "Noted. I've removed this task:\n"
                + String.format("\t%s\n", task)
                + String.format("Now you have %d tasks in the list.", size);
    }

    /**
     * Generates and returns the error message upon failure to delete a task.
     *
     * @param message The detail message.
     * @return The error message.
     */
    public static String generateDeleteErrorMessage(String message) {
        return message + "\n"
                + "To delete a task, tell me in this format:\n"
                + "\tdelete [task_index]";
    }

    /**
     * Generates and returns the error message when the user input more or less than one argument for the find command.
     *
     * @return The error message.
     */
    public static String generateFindErrorMessage() {
        return "Only one keyword is allowed for this command.\n"
                + "To find a task, tell me in this format:\n"
                + "\tfind [keyword]\n"
                + "Only one keyword is allowed at a time.";
    }

    /**
     * Generates and returns the error message when the user's input task index is not found in the list.
     *
     * @param taskIndex The user's input task index that is invalid.
     * @return The error message.
     */
    public static String generateTaskIndexNotFoundMessage(int taskIndex) {
        return String.format("The task of index %d cannot be found in your list.", taskIndex);
    }
}
