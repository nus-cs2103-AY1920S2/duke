public class HelpCommand extends Command {

    public HelpCommand(String inputCommand, boolean isExit) {
        super(inputCommand, isExit);
    }

    @Override
    public String execute(UI ui, TaskList list, Storage storage, HistoryManager historyManager) throws DukeException {
        String helpCommands = "";
        helpCommands += ui.getDecoration().trim() + "\n";
        helpCommands += "Command List \n";
        helpCommands += ui.getDecoration().trim() + "\n";
        helpCommands += "1) todo [.....]: adds a todo task to task list [todo A].\n";
        helpCommands += "2) event [...] /at [....]: adds an event to task list [event X /at 21 Jan].\n";
        helpCommands += "3) deadline [...] /by [dd-MM-yyy HH:mm]: adds a deadline to task list.\n";
        helpCommands += "4) done [number]: sets task with that number to done.\n";
        helpCommands += "5) delete [number]: deletes the associated task.\n";
        helpCommands += "6) list: list all the tasks.\n";
        helpCommands += "7) find [word]: find all tasks with that word.\n";
        helpCommands += "8) priority [number] [low/medium/high]: sets that task to the stated priority.\n";
        helpCommands += "9) list-priority: lists tasks based on ascending priority.\n";
        helpCommands += "10) help: provide help commands.\n";
        helpCommands += "11) undo: undo the last command, max 5 previous commands.\n";
        helpCommands += "12) bye: exit the chatBot.";
        ui.prettyPrinting(helpCommands);
        return helpCommands;
    }
}
