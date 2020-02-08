package duke.command.method;

import duke.Duke;
import duke.command.Command;
import duke.task.Task;

public class HelpCommandMethod implements CommandMethod {
    public static final String NAME = "help";

    private static final String other = ""
            + "Datetime's must be in " + Task.DATE_TIME_INPUT_PATTERN + " format.\n"
            + "Tasks are saved at " + Duke.getProgram().getStorage().getFilePath();

    public String getFormat() {
        return HelpCommandMethod.NAME;
    }

    public String getDescription() {
        return "Displays the help message.";
    }

    public void execute(Command command) {
        Duke program = Duke.getProgram();
        CommandMethod[] commands = {
            new HelpCommandMethod(),
            new ListCommandMethod(),
            new TodoCommandMethod(),
            new DeadlineCommandMethod(),
            new EventCommandMethod(),
            new DeleteCommandMethod(),
            new DoneCommandMethod(),
            new FindCommandMethod(),
            new ByeCommandMethod()
        };
        StringBuilder output = new StringBuilder();
        output.append("Commands available:\n");
        for (CommandMethod cm : commands) {
            output.append(cm.getFormat() + "\n");
            output.append("\t" + cm.getDescription() + "\n");
            output.append("\n");
        }
        output.append(HelpCommandMethod.other);
        program.getUi().print(output.toString());
    }
}
