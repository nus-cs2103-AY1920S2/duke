package duke.util;

public class DukeException {

    public static class InvalidCommand extends Exception {
        public InvalidCommand() {
            super("Sorry, I don't know what you mean. Try again?");
        }
    }

    public static class EmptyToDo extends Exception {
        public EmptyToDo() {
            super("Whoa there! I need to know what you are \"ToDo-ing\"!");
        }
    }

    public static class EmptyDeadlineName extends Exception {
        public EmptyDeadlineName() {
            super("Whoa there! I need to know what is the deadline!");
        }
    }

    public static class NoDeadlineTime extends Exception {
        public NoDeadlineTime() {
            super("Whoa there! I need to know when is the deadline! E.g. \"deadline quiz /by 4 July");
        }
    }

    public static class EmptyEvent extends Exception {
        public EmptyEvent() {
            super("Whoa there! I need to know what's the event!");
        }
    }

    public static class NoEventDatetime extends Exception {
        public NoEventDatetime() {
            super("Whoa there! I need to know when is the event! E.g. \"event Party /at 4 July 2-4pm");
        }
    }

}
