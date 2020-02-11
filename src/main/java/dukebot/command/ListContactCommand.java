package dukebot.command;

import dukebot.contactlist.ContactList;
import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.ui.LineName;
import dukebot.ui.Ui;

public class ListContactCommand extends Command {
    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        assertExecuteNotNull(appStorage, ui, storage);
        ContactList contactList = appStorage.getContactList();
        if (contactList.size() == 0) {
            ui.sayLine(LineName.CONTACT_LIST_EMPTY);
            return;
        }
        ui.sayLine(LineName.CONTACT_LIST_EXIST);
        ui.sayContacts(contactList.getContactList());
    }
}
