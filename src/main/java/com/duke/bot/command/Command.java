package com.duke.bot.command;

import java.util.List;
import com.duke.bot.Task;

public abstract class Command {
    public static class ExecuteResult {
        private final List<Task> tasks;
        private final List<String> messages;

        public ExecuteResult(List<Task> tasks, List<String> messages) {
            this.tasks = tasks;
            this.messages = messages;
        }

        public List<Task> getTasks() {
            return tasks;
        }

        public List<String> getMessages() {
            return messages;
        }
    }

    public abstract ExecuteResult execute(List<Task> tasks); 
}