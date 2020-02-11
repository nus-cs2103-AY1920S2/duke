package dukebot.command;

import dukebot.exception.DukeException;
import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

import java.util.HashMap;

/**
 * Changes phrase mapping of command.
 */
public class AliasCommand extends Command {
    private final String[] inpArr;
    private final HashMap<String, CommandList> aliasMap;

    /**
     * Generates the command.
     *
     * @param inpArr  The input entered by user split by space
     */
    public AliasCommand(String[] inpArr, HashMap<String, CommandList> aliasMap) {
        assert inpArr != null;
        assert aliasMap != null;
        this.aliasMap = aliasMap;
        this.inpArr = inpArr;
    }

    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        assertExecuteNotNull(appStorage, ui, storage);
        if (inpArr.length < 3) {
            ui.sayLine(LineName.ALIAS_COMMAND_FAIL);
            return;
        }
        String currentAlias = inpArr[1];
        String newAlias = inpArr[2];
        if (!aliasMap.containsKey(currentAlias)) {
            ui.sayLine(LineName.ALIAS_DOES_NOT_EXIST);
            return;
        } else if (aliasMap.containsKey(newAlias)) {
            ui.sayLine(LineName.ALIAS_ALREADY_EXISTS);
            return;
        }
        CommandList command = aliasMap.get(currentAlias);
        aliasMap.remove(currentAlias);
        aliasMap.put(newAlias, command);
        try {
            storage.saveAlias(aliasMap);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
        }
        ui.aliasSuccess(currentAlias, newAlias);
    }
}
