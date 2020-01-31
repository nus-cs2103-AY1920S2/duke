/**
 * Represents as an outline what a command carried about by
 * Chatbot should be able to do.
 * Any class that extends this class must implement method
 * execute and isExit.
 * Only inherits from this class according to what Chatbot can
 * understand as a command.
 *
 * @author Kenny Ho
 */

public abstract class Command {

    private Task task;

    public Command(Task task) {
        this.task = task;
    }

    public Command() {
        this.task = null;
    }

    /**
     * Getter method to retrieve Task object if command
     * requires a Task object to be created.
     *
     * @return Task object.
     */
    public Task getTask() {
        return task;
    }

    /**
     * Provide the ability any command to:
     * 1) call respective TaskList method if command requires
     * any form of functionality provided by TaskList class.
     * 2) call respective Ui method if command requires any
     * form of interaction with user.
     * 3) call respective Storage method if command requires
     * any form of functionality provided by Storage class.
     *
     * @param tasks   TaskList object providing command the ability to call TaskList methods.
     * @param ui      Ui object providing command the ability to call Ui methods.
     * @param storage Storage object providing command the ability to call Storage methods.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Should set boolean to true if command is an exit command otherwise
     * set boolean to false.
     *
     * @return Boolean value representing command is an exit command.
     */
    public abstract boolean isExit();

}

