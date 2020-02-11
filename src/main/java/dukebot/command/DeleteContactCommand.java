package dukebot.command;

import dukebot.contactlist.ContactDetail;
import dukebot.contactlist.ContactList;
import dukebot.exception.DukeException;
import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.ui.LineName;
import dukebot.ui.LineNameWithContact;
import dukebot.ui.Ui;
import dukebot.util.MiscUtils;

public class DeleteContactCommand extends Command {
    private final String[] inpArr;

    /**
     * Generates the command.
     *
     * @param inpArr The input entered by user split by space
     */
    public DeleteContactCommand(String[] inpArr) {
        assert inpArr != null;
        this.inpArr = inpArr;
    }

    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        assertExecuteNotNull(appStorage, ui, storage);
        ContactList contactList = appStorage.getContactList();
        if (inpArr.length == 1) {
            ui.sayLine(LineName.DELETE_EMPTY);
            return;
        }

        if (!MiscUtils.isInteger(inpArr[1])) {
            ui.sayLine(LineName.NOT_A_NUMBER);
            return;
        }

        int taskInd = Integer.parseInt(inpArr[1]) - 1;
        ContactDetail contactDetail = contactList.deleteTask(taskInd);

        if (contactDetail == null) {
            ui.sayLine(LineName.DELETE_OUT_OF_INDEX);
            return;
        }

        ui.sayLineWithContact(LineNameWithContact.DELETE_CONTACT_SUCCESS, contactDetail);

        try {
            storage.saveContactList(contactList);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
        }
    }
}
