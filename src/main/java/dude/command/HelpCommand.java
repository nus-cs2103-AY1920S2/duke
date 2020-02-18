package dude.command;

import dude.parser.Parser;

import dude.component.IUserInterface;
import dude.component.TaskList;

import java.util.HashMap;
import java.util.Map;

public class HelpCommand extends Command {
    /** Mappings from commands to descriptions of their behavior. */
    private static final Map<String, String> COMMAND_INFO = new HashMap<>();

    static {
        COMMAND_INFO.put("help", "Summons these help pages");
        COMMAND_INFO.put("bye", "Exits the program");
        COMMAND_INFO.put("list", "Shows all current tasks");
        COMMAND_INFO.put("today", "Shows all tasks occurring on today");
        COMMAND_INFO.put("done", "Marks the indicated task as done.");
        COMMAND_INFO.put("delete", "Deletes the indicated task from the list.");
        COMMAND_INFO.put("check", "Shows all tasks occurring on the given date.");
        COMMAND_INFO.put("todo", "Adds a new uncompleted todo task");
        COMMAND_INFO.put("deadline", "Adds a new uncompleted deadline with given due date");
        COMMAND_INFO.put("event", "Adds a new uncompleted event with given start and end dates");
        COMMAND_INFO.put("find", "Shows all tasks with descriptions that contain the given keyword");
    }

    private String helpTopic;

    /**
     * Initializes a HelpCommand which, when executed, displays usage information for all commands.
     */
    public HelpCommand() {
        this.helpTopic = "all";
    }

    /**
     * Initializes a HelpCommand which, when executed, displays information about the topic given.
     *
     * @param helpTopic the topic to receive help from. Can be a command or -date, specified in Parser.USAGES.
     */
    public HelpCommand(String helpTopic) {
        this.helpTopic = helpTopic;
    }

    /**
     * Shows useful help messages to the user regarding the topic given.
     *
     * @param tasks the current TaskList before the command is executed. Can be modified by execute.
     * @param ui the IUserInterface to report results of successful commands.
     * @throws CommandExecutionException if helpTopic is not a command or -date.
     */
    @Override
    public void execute(TaskList tasks, IUserInterface ui) throws CommandExecutionException {
        switch (helpTopic) {
        case "all":
            helpAll(ui);
            break;
        case "-date":
            helpDate(ui);
            break;
        default:
            helpCommand(helpTopic, ui);
            break;
        }
    }

    private void helpAll(IUserInterface ui) {
        ui.respond(() -> {
            ui.speak("Hey man! I'm Dude, the chillest chatbot you've ever seen.");
            ui.speak("I can help you keep track of your tasks,"
                    + " just tell me what you want in one of these formats:"
                    + System.lineSeparator());
            for (String usageMsg : Parser.USAGES.values()) {
                ui.speak("  " + usageMsg);
            }
        });
    }

    private void helpCommand(String command, IUserInterface ui) throws CommandExecutionException {
        if (!COMMAND_INFO.containsKey(command)) {
            throw new CommandExecutionException("I can't help you with that!");
        }

        String additionalHelpMessage = "";
        if (isIndexedCommand(command)) {
            additionalHelpMessage = "Use list or filter the tasks via today, check or find to see each tasks' index.";
        } else if (isDateCommand(command)) {
            additionalHelpMessage = "Use 'help -date' to learn more about the different date formats.";
        }
        ui.respond(command, "", COMMAND_INFO.get(command), "",
                "USAGE: ", "", Parser.USAGES.get(command), "", additionalHelpMessage);

    }

    private void helpDate(IUserInterface ui) {
        ui.respond("I'm great at understanding my dates",
                "Of course I'm great at understanding your dates, too!",
                "You can tell me your dates in any of these ways:");
        ui.respond("1. yyyy-mm-dd", "",
                "Example: 2020-02-02", "",
                "Nice and easy, I can't go wrong with this");
        ui.respond("2. d MMM yyyy", "",
                "Example: 2 Feb 2020", "",
                "Even easier to remember and type!");
        ui.respond("3. dayOfWeek [+number]", "",
                "Examples:", "Monday +4", "Tue", "Sun +3", "",
                "If just a day of the week is given, say Tue or Wed, the date is "
                + "taken to be the next closest day from today. Eg. 'Tue' refers to "
                + "next Tuesday.",
                "If a number n is specified, the date is the given day n weeks from now. "
                + "Eg. 'Monday +4' refers to the Monday 4 weeks from now");
        ui.respond("4. number [day(s) | week(s) | month(s) | year(s)]", "",
                "Examples:", "10 days", "4 weeks", "1 year", "",
                "The given duration after today. Eg. If today is 2020-02-09, '10 days' "
                + "would be 2020-02-19");
    }

    private boolean isIndexedCommand(String command) {
        return command.equals("done") || command.equals("delete");
    }

    private boolean isDateCommand(String command) {
        switch (command) {
        case "check":
        case "deadline":
        case "event":
            return true;
        default:
            return false;
        }
    }
}
