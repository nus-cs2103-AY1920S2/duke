package duke.commands;


public class HelpCommand implements Command {
    /**
     * Creates a HelpCommand that returns a list of instructions.
     */
    public HelpCommand() {

    }

    @Override
    public String execute() {
        String helpMessage =  "Help Center\n\n"
                + "[action]\t[details...]\t\t\t\t\t\t[description]\n\n"
                + "todo\t\t<description>\t\t\t\t\t\tCreates a todo\n"
                + "event\t<description> /at <time> <date>\t\tCreates an event\n"
                + "deadline\t<description> /by <time> <date>\t\tCreates a deadline\n"
                + "list\t\t\t\t\t\t\t\t\t\tLists all tasks saved by user\n"
                + "delete\t<index>\t\t\t\t\t\t\tDelete task at index\n"
                + "done\t<index>\t\t\t\t\t\t\tMark task at index as done\n"
                + "find\t\t<keyword>\t\t\t\t\t\tTasks containing keyword\n"
                + "date\t\t<date>\t\t\t\t\t\t\tTasks on/by given date\n"
                + "#tag\t\t\t\t\t\t\t\t\t\tTasks tagged with given tag\n"
                + "help\t\t\t\t\t\t\t\t\t\tHelp center\n"
                + "bye\t\t\t\t\t\t\t\t\t\tExit Duke\n"
                + "\n* actions with <date> are to be formatted like this: dd/mm/yyyy\n"
                + "** actions with <time> are to be formatted like this: hh:mm";

        return helpMessage.strip();
    }
}
