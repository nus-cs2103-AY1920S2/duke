package duke.constants;

public class HelpConstants {
    public static final String DEADLINE_HELP = "Deadline: "
            + "adds a new deadline to list of task. Usage: \n"
            + "  deadline <task_name> /by <deadline(yyyy-MM-dd)>\n"
            + "eg. deadline Do CS2103 project /by 2020-04-24\n";

    public static final String EVENT_HELP = "Event: "
            + "adds a new event to list of task. Usage: \n"
            + "  event <task_name> /at <schedule(yyyy-MM-dd)>\n"
            + "eg. event NUS career fair /at 2020-03-12\n";

    public static final String TODO_HELP = "Todo: "
            + "adds a new todo to list of task. Usage: \n"
            + "  todo <task_name>\n"
            + " eg. todo Learn java\n";

    public static final String DELETE_HELP = "Delete: "
            + "deletes selected tasks from the list of tasks. Usage: \n"
            + "  delete [list of tasks indices]\n"
            + " eg. delete 1 3 4\n";

    public static final String DONE_HELP = "Done: "
            + "marks selected tasks as done. Usage: \n"
            + "  done [list of tasks indices]\n"
            + " eg. done 1 2 5\n";

    public static final String EXIT_HELP = "Bye: "
            + "exits duke application. Usage: \n"
            + "  bye\n"
            + " eg. bye\n";

    public static final String FIND_HELP = "Find: "
            + "finds all tasks that contain a specific keyword. Usage: \n"
            + "  find <keyword>\n"
            + " eg.  find homework\n";

    public static final String SNOOZE_HELP = "Snooze: "
            + "delays the selected task by a certain specified amount of days. Usage: \n"
            + "  snooze <task index> <days>\n"
            + " eg.  snooze 4 20\n";

    public static final String LIST_HELP = "List: "
            + "lists down all tasks stored by duke. Usage: \n"
            + "  list\n"
            + " eg. list\n";
}
