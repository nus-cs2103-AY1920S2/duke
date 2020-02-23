package duke.command.method;

import duke.Duke;
import duke.command.Command;
import duke.task.Task;

public class HelpCommandMethod implements CommandMethod {
    public String getCommandName() {
        return "help";
    }

    public String getFormat() {
        return getCommandName();
    }

    public String getDescription() {
        return "Displays the help message.";
    }

    public void execute(Command command) {
        StringBuilder output = new StringBuilder();
        output.append("Commands available:\n");
        for (CommandMethod method : Command.COMMANDS) {
            output.append(method.getFormat() + "\n");
            output.append("\t" + method.getDescription() + "\n");
            output.append("\n");
        }
        output.append("Datetime's must be in " + Task.DATE_TIME_INPUT_PATTERN + " format.\n");
        output.append("Tasks are saved at " + Duke.getProgram().getStorage().getFilePath());
        Duke.getProgram().getUi().print(output.toString());
    }
}
