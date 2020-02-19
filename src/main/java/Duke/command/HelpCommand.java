package Duke.command;

public class HelpCommand extends Command {
    private static final String HORIZONTAL_BORDER = "-------------------------------------------------\n";
    private static final String COMMANDS = HORIZONTAL_BORDER + "General Commands\n" +
            "Command format:\nCOMMAND\n\nlist - Shows all tasks in order which they " +
            "were added from oldest to newest\nstats - Shows all successful add, delete and done command in " +
            "order they which were added, from oldest to newest\nbye - Exit the application\n" +
            "help - Summon the Royal rescue team (ie. this help prompt)\n\n" + HORIZONTAL_BORDER +
            "Done/Delete Commands\n" +
            "Command format:\nCOMMAND TASKNUM\nNote:\n1. TASKNUM -> task number as shown by 'list' command\n\n" +
            "done - mark task with TASKNUM as done\ndelete - delete task with TASKNUM from list\n\n" + HORIZONTAL_BORDER
            + "Add Task Commands(w/o date and time)\nCommand format:\nTASKTYPE DESCRIPTION\n(eg. todo read)\n\n" +
            "TASKTYPE:\ntodo\n\n" + HORIZONTAL_BORDER + "Add Task Commands(with date and time)\n" +
            "Command format:\nTASKTYPE DESCRIPTION /PREPOSITION DATE TIME\n" +
            "(eg. event Networking /on 2020-01-30 1700h)\nNote:\n1. DATE input format " +
            "is YYYY-MM-DD\n2. Preposition starts with a '/' followed by any single word\n3. TIME is optional\n\n" +
            "TASKTYPE:\ndeadline\nevent\n\n" + HORIZONTAL_BORDER;
    public HelpCommand() {
        super();
    }

    public String execute() {
        String out = HORIZONTAL_BORDER + "HELP:\n" + COMMANDS;
        return out;
    }

}
