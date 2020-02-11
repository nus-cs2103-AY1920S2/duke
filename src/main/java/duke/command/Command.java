package duke.command;

import java.util.List;
import duke.DukeException;
import duke.task.Task;

public abstract class Command {
    public static class ExecuteResult {
        private final List<Task> tasks;
        private final String message;
        private final boolean hasNextCommand;

        /**
         * Instantiates an data structure containing the results of calling
         * the {@link #execute(List, String, boolean) execute} method.
         * 
         * @param tasks Updated tasks after executing the command
         * @param message Message given as a response to executing the command
         * @param hasNextCommand Whether or not the current command is terminal
         */
        public ExecuteResult(List<Task> tasks, String message, boolean hasNextCommand) {
            assert tasks != null;
            assert message != null;

            this.tasks = tasks;
            this.message = message;
            this.hasNextCommand = hasNextCommand;
        }

        public List<Task> getTasks() {
            return tasks;
        }

        public String getMessage() {
            return message;
        }

        public boolean hasNextCommand() {
            return hasNextCommand;
        }
    }

    public abstract ExecuteResult execute(List<Task> tasks) throws DukeException; 
}
