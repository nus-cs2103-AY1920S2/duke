package duke.command;

import duke.DukeErrorType;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.TaskList;

import java.util.Optional;

public class Help extends Command {

    private Optional<String> specificHelpCommand;

    public Help(Optional<String> specificHelpCommand) {
        this.specificHelpCommand = specificHelpCommand;
    }

    /**
     * Provide the ability any command to:
     * 1) call respective duke.task.TaskList method if command requires
     * any form of functionality provided by duke.task.TaskList class.
     * 2) call respective duke.Ui method if command requires any
     * form of interaction with user.
     * 3) call respective duke.Storage method if command requires
     * any form of functionality provided by duke.Storage class.
     *
     * @param tasks   duke.task.TaskList object providing command the ability to call duke.task.TaskList methods.
     * @param ui      duke.Ui object providing command the ability to call duke.Ui methods.
     * @param storage duke.Storage object providing command the ability to call duke.Storage methods.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (this.specificHelpCommand.isPresent()) {
            return ui.showSpecificHelpMessage(specificHelpCommand.get());
        } else {
            return ui.showHelpMessage();
        }
    }


}
