package dukebot.command;

import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

/**
 * Command output a line.
 */
public class UiOnlyCommand extends Command {
    private LineName toSay;

    /**
     * Generates the command.
     *
     * @param toSay  The LineName to say.
     */
    public UiOnlyCommand(LineName toSay) {
        this.toSay = toSay;
    }

    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        ui.sayLine(toSay);
    }
}
