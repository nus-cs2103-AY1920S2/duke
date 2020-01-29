package com.duke.bot.command;

import java.util.List;
import com.duke.bot.DukeException;
import com.duke.bot.Task;

public abstract class Command {
    public static class ExecuteResult {
        private final List<Task> tasks;
        private final List<String> messages;
        private final boolean hasNextCommand;

        public ExecuteResult(List<Task> tasks, List<String> messages, boolean hasNextCommand) {
            this.tasks = tasks;
            this.messages = messages;
            this.hasNextCommand = hasNextCommand;
        }

        public List<Task> getTasks() {
            return tasks;
        }

        public List<String> getMessages() {
            return messages;
        }

        public boolean hasNextCommand() {
            return hasNextCommand;
        }
    }

    public abstract ExecuteResult execute(List<Task> tasks) throws DukeException; 
}