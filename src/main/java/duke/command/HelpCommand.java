package duke.command;

import duke.constants.Constants;
import duke.constants.HelpConstants;
import duke.main.UiHandler;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Help command implementing command interface.
 */
public class HelpCommand implements Command {

    /**
     * Sets ui response to manual of command to be displayed.
     * @param task task for this execution
     * @param uiHandler ui handler to capture response
     * @param storage storage to be updated
     * @param taskList list of tasks
     */
    @Override
    public void execute(String task, UiHandler uiHandler, Storage storage, TaskList taskList) {
        String[] token = task.split(" ");

        assert token[0].equals("help");

        if (token.length > 1) {
            if (Constants.COMMAND_LIST.contains(token[1])) {
                switch (token[1]) {
                case "deadline":
                    uiHandler.setResponse(HelpConstants.DEADLINE_HELP);
                    return;
                case "event":
                    uiHandler.setResponse(HelpConstants.EVENT_HELP);
                    return;
                case "todo":
                    uiHandler.setResponse(HelpConstants.TODO_HELP);
                    return;
                case "delete":
                    uiHandler.setResponse(HelpConstants.DELETE_HELP);
                    return;
                case "done":
                    uiHandler.setResponse(HelpConstants.DONE_HELP);
                    return;
                case "bye":
                    uiHandler.setResponse(HelpConstants.EXIT_HELP);
                    return;
                case "find":
                    uiHandler.setResponse(HelpConstants.FIND_HELP);
                    return;
                case "snooze":
                    uiHandler.setResponse(HelpConstants.SNOOZE_HELP);
                    return;
                case "list":
                    uiHandler.setResponse(HelpConstants.LIST_HELP);
                    return;
                default:
                    break;
                }
            }
        }

        String response = "1." + HelpConstants.DEADLINE_HELP + "\n" + "2." + HelpConstants.EVENT_HELP + "\n"
                + "3." + HelpConstants.TODO_HELP + "\n" + "4." + HelpConstants.DELETE_HELP + "\n"
                + "5." + HelpConstants.DONE_HELP + "\n" + "6." + HelpConstants.EXIT_HELP + "\n"
                + "7." + HelpConstants.FIND_HELP + "\n" + "8." + HelpConstants.SNOOZE_HELP + "\n"
                + "9." + HelpConstants.LIST_HELP + "\n";
        uiHandler.setResponse(response);
    }
}
