package duke;

public class Ui {

    static final String COMMAND_NOT_FOUND = "Oops! Command not found!";
    static final String INVALID_FIELD = "Oops! Invalid field!";
    static final String TASK_NEEDS_NAME = "Oops! This task needs a name!";
    static final String TASK_NEEDS_DATE_TIME = "Oops! This task needs a date/time!";
    static final String WRONG_DATE_TIME_FORMAT = "Oops! The date needs to be DD/MM/YYYY and time needs to be HHMM (24hr) format!";
    static final String NO_TASK_FOUND = "Oops! Task not found in the list!";
    static final String NO_TASK_IN_LIST = "Yay! There are no tasks in your list!";
    static final String DISPLAY_TASK_LIST = "Here are all the tasks in your list:";
    static final String DISPLAY_MATCHING_TASK_LIST = "Here are your tasks that matched your query:";
    static final String NO_MATCHING_TASK_IN_LIST = "Oops! There are no tasks that matches your query!";
    static final String CHANGES_SAVED = "Changes saved!";
    static final String MARKED_AS_DONE = "Marked this task as done:\n    ";
    static final String TASK_ALREADY_DONE = "Oops! This task was already marked as done!";
    static final String ADDED_NEW_TASK = "New task has been added:\n    ";
    static final String DELETED_TASK = "Removed this task:\n    ";
    static final String UPDATED_TASK = "Updated this task to:\n    ";
    static final String NO_FIELD_TO_UPDATE = "No field to update!";
    static final String CANNOT_SET_DATE_TIME_TO_TODO = "Cannot set date or time to todo!";
    static final String NEED_TO_SPECIFY_PERIOD = "Need to specify period!";
    static final String NEED_TO_SPECIFY_DATE = "Need to specify date!";
    static final String WELCOME_MESSAGE = "Welcome to Duke!\n";

    public static String readNextCommand(String input, TaskList tasklist) {
        String[] command = Parser.parseInput(input.strip());
        return Parser.processCommand(command, tasklist);
    }

    public static String taskCountMessage(int listSize) {
        return "\nNow you have "+ listSize + (listSize == 1 ? " task" : " tasks") + " in the list.";
    }

    public static String displayUpcomingRange(int dayRangeUntil, TaskList upcomingDeadlines, TaskList upcomingEvents) {
        String deadlines = messageUpcomingDeadlines(upcomingDeadlines) + "for the next " + dayRangeUntil + " day(s).\n";;
        if (!upcomingDeadlines.isEmpty()) {
            deadlines = deadlines + upcomingDeadlines + "\n";
        }

        String events = messageUpcomingEvents(upcomingEvents) + "for the next " + dayRangeUntil + " day(s).\n";;
        if (!upcomingEvents.isEmpty()) {
            events = events + upcomingEvents + "\n";
        }
        return deadlines + events;
    }

    public static String displayUpcomingDay(String targetDate, TaskList upcomingDeadlines, TaskList upcomingEvents) {
        String deadlines = messageUpcomingDeadlines(upcomingDeadlines) + "on " + targetDate + ".\n";
        if (!upcomingDeadlines.isEmpty()) {
            deadlines = deadlines + upcomingDeadlines + "\n";
        }

        String events = messageUpcomingEvents(upcomingEvents) + "on " + targetDate + ".\n";
        if (!upcomingEvents.isEmpty()) {
            events = events + upcomingEvents + "\n";
        }
        return deadlines + events;
    }



    public static String messageUpcomingDeadlines(TaskList upcomingDeadlines) {
        if (upcomingDeadlines.isEmpty()) {
            return "You have no upcoming deadlines ";
        }
        upcomingDeadlines.sortTime();
        return "Your upcoming deadlines ";
    }

    public static String messageUpcomingEvents(TaskList upcomingEvents) {
        if (upcomingEvents.isEmpty()) {
            return "You have no upcoming events ";
        }
        upcomingEvents.sortTime();
        return "Your upcoming events ";
    }

}