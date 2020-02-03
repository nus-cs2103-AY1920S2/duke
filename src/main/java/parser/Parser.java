package main.java.Parser;

import main.java.exceptions.NoCommandException;
import main.java.exceptions.NoDescriptionException;
import main.java.model.DeadLineTask;
import main.java.model.EventTask;
import main.java.model.ToDoTask;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    static final String EXIT_KEY = "bye";
    static final String  VIEW_LIST_KEY = "list";
    static final String DELETE_KEY = "(delete)(\\s+)(\\d+)";
    static final String FINISH_KEY = "(done)(\\s+)(\\d+)";
    static final String TODO_KEY = "(todo)(.*)";
    static final String DEADLINE_KEY = "(deadline)(.*)(\\/by)(.*)";
    static final String EVENT_KEY = "(event)(.*)(\\/at)(.*)";

    static final Pattern DELETE_PATTERN = Pattern.compile(DELETE_KEY);
    static final Pattern FINISH_PATTERN = Pattern.compile(FINISH_KEY);
    static final Pattern TODO_PATTERN = Pattern.compile(TODO_KEY);
    static final Pattern DEADLINE_PATTERN = Pattern.compile(DEADLINE_KEY);
    static final Pattern EVENT_PATTERN = Pattern.compile(EVENT_KEY);

    public Parser() { }

    private int findIndex(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return Integer.parseInt(matcher.group(3)) - 1;
    }

    private String findDescription(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher.group(2).trim();
    }

    private String findEndTime(Pattern pattern, String input) {
        Matcher matcher = pattern.matcher(input);
        matcher.find();
        return matcher.group(4).trim();
    }

    private static boolean isExitKey(String input) { return EXIT_KEY.equals(input); }

    private static boolean isViewListKey(String input) {return VIEW_LIST_KEY.equals(input); }

    private static boolean isDeleteKey(String input) {
        Matcher deleteMatcher = DELETE_PATTERN.matcher(input);
        return deleteMatcher.find();
    }

    private static boolean isFinishKey(String input) {
        Matcher finishMatcher = FINISH_PATTERN.matcher(input);
        return finishMatcher.find();
    }

    private static boolean isTodoKey(String input) {
        Matcher todoMatcher = TODO_PATTERN.matcher(input);
        return todoMatcher.find();
    }

    private static boolean isDeadLineKey(String input) {
        Matcher deadlineMatcher = DEADLINE_PATTERN.matcher(input);
        return deadlineMatcher.find();
    }

    private static boolean isEventKey(String input) {
        Matcher eventMatcher = EVENT_PATTERN.matcher(input);
        return eventMatcher.find();
    }

    public Command parseCommand(String userInput) throws NoCommandException {
        if (this.isExitKey(userInput)) {
            return new ExitCommand();
        }
        else if (this.isViewListKey(userInput)) {
            return new ViewListCommand();
        }
        else if (this.isDeleteKey(userInput)) {
            int deletedTaskIndex = this.findIndex(DELETE_PATTERN, userInput);
            return new DeleteCommand(deletedTaskIndex);
        }
        else if (this.isFinishKey(userInput)) {
            int finishedTaskIndex = this.findIndex(FINISH_PATTERN, userInput);
            return new FinishCommand(finishedTaskIndex);
        }
        else if (this.isTodoKey(userInput)){
            String description = this.findDescription(TODO_PATTERN, userInput);
            ToDoTask taskToAdd = new ToDoTask();
            return new AddCommand(taskToAdd, description);
        }
        else if (this.isDeadLineKey(userInput)){
            String description = this.findDescription(DEADLINE_PATTERN, userInput);
            String by = this.findEndTime(DEADLINE_PATTERN, userInput);
            DeadLineTask taskToAdd = new DeadLineTask();
            return new AddCommand(taskToAdd, description, by);
        }
        else if (this.isEventKey(userInput)){
            String description = this.findDescription(EVENT_PATTERN, userInput);
            String at = this.findEndTime(EVENT_PATTERN, userInput);
            EventTask taskToAdd = new EventTask();
            return new AddCommand(taskToAdd, description, at);
        }
        else {
            throw new NoCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }



}
