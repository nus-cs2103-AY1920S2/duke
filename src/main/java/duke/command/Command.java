package duke.command;

import duke.DukeException;
import duke.TaskList;

public abstract class Command {
    public static class ExecuteResult {
        private final TaskList tasks;
        private final String message;
        private final boolean hasNextCommand;

        /**
         * Instantiates an data structure containing the results of calling
         * the {@link #execute(TaskList, String, boolean) execute} method.
         * 
         * @param tasks Updated tasks after executing the command
         * @param message Message given as a response to executing the command
         * @param hasNextCommand Whether or not the current command is terminal
         */
        public ExecuteResult(TaskList tasks, String message, boolean hasNextCommand) {
            this.tasks = tasks;
            this.message = message;
            this.hasNextCommand = hasNextCommand;
        }

        public TaskList getTasks() {
            return tasks;
        }

        public String getMessage() {
            return message;
        }

        public boolean hasNextCommand() {
            return hasNextCommand;
        }
    }

    public abstract ExecuteResult execute(TaskList tasks) throws DukeException; 
}
