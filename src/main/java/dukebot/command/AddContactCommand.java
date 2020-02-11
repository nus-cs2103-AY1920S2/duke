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

public class AddContactCommand extends Command {
    private final String[] inpArr;

    /**
     * Generates the command.
     *
     * @param inpArr  The input entered by user split by space
     */
    public AddContactCommand(String[] inpArr) {
        assert inpArr != null;
        this.inpArr = inpArr;
    }

    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        assertExecuteNotNull(appStorage, ui, storage);
        ContactList contactList = appStorage.getContactList();
        if (inpArr.length <= 2) {
            ui.sayLine(LineName.ADD_CONTACT_EMPTY);
            return;
        }
        if (!MiscUtils.isInteger(inpArr[2])) {
            ui.sayLine(LineName.NOT_A_NUMBER);
            return;
        }
        String name = inpArr[1];
        int phoneNumber = Integer.parseInt(inpArr[2]);
        ContactDetail contactDetail = new ContactDetail(name, phoneNumber);
        contactList.add(contactDetail);
        ui.sayLineWithContact(LineNameWithContact.ADD_CONTACT_SUCCESS, contactDetail);

        try {
            storage.saveContactList(contactList);
        } catch (DukeException e) {
            ui.sayLine(e.getErrorLineName());
        }
    }
}
