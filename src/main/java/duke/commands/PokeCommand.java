package duke.commands;

import duke.model.Storage;
import duke.model.TaskList;

/**
 * The <code>PokeCommand</code> class represents a poke command to return Duke's response
 * when getting poked.
 */
public class PokeCommand extends Command {
    private static final String[] RESPONSES = {"(- o - ) . z Z)", "( > A < )/", "/(T___T)\\", "(0` - `!)/",
                                               "(> - w -) >", "(/ _ < .)", "(/ > D <)/", "(/q n q)/", "/(o_ o)"};

    @Override
    public String execute(TaskList taskList, Storage storage) {
        int randomIndex = (int) Math.floor(Math.random() * RESPONSES.length);
        return RESPONSES[randomIndex];
    }
}
