package commands;

import data.exceptions.StorageOperationException;
import storage.StorageFile;

public class SaveToJsonCommand extends Command{
    public static final String COMMAND_WORD = "save_json";
    public static final String MESSAGE_DONE = "  Data saved successfully!";

    public SaveToJsonCommand() {

    }

    @Override
    public CommandResult execute() {
        try {
            StorageFile.saveJson(taskManager);
        } catch (StorageOperationException soe) {
            System.out.println(soe);
        }
        return new CommandResult(MESSAGE_DONE);
    }

    @Override
    public CommandResult executeForGUI() {
        try {
            StorageFile.saveJson(taskManager);
        } catch (StorageOperationException soe) {
            System.out.println(soe);
        }
        return new CommandResult(MESSAGE_DONE);
    }
}
