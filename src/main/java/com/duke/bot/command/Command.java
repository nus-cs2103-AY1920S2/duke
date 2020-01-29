package com.duke.bot.command;

import java.util.List;
import com.duke.bot.DukeException;
import com.duke.bot.TaskList;

public abstract class Command {
    public static class ExecuteResult {
        private final TaskList tasks;
        private final List<String> messages;
        private final boolean hasNextCommand;

        public ExecuteResult(TaskList tasks, List<String> messages, boolean hasNextCommand) {
            this.tasks = tasks;
            this.messages = messages;
            this.hasNextCommand = hasNextCommand;
        }

        public TaskList getTasks() {
            return tasks;
        }

        public List<String> getMessages() {
            return messages;
        }

        public boolean hasNextCommand() {
            return hasNextCommand;
        }
    }

    public abstract ExecuteResult execute(TaskList tasks) throws DukeException; 
}
