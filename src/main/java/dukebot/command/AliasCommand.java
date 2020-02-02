package dukebot.command;

import dukebot.exception.DukeException;
import dukebot.storage.Storage;
import dukebot.tasklist.TaskList;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

import java.util.HashMap;

public class AliasCommand extends Command {
    private String[] inpArr;
    private Parser parser;

    /**
     * Generates the command.
     *
     * @param inpArr  The input entered by user split by space
     */
    public AliasCommand(String[] inpArr, Parser parser) {
        this.parser = parser;
        this.inpArr = inpArr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (inpArr.length >= 3) {
            String currentAlias = inpArr[1];
            String newAlias = inpArr[2];
            try {
                HashMap<String, CommandList> aliasMap = parser.updateAliasMap(currentAlias, newAlias);
                storage.saveAlias(aliasMap);
                ui.aliasSuccess(currentAlias, newAlias);
            } catch (DukeException e) {
                ui.sayLine(e.getErrorLineName());
            }
        } else {
            ui.sayLine(LineName.ALIAS_COMMAND_FAIL);
        }
    }
}
