package dukebot.command;

import dukebot.contactlist.ContactDetails;
import dukebot.contactlist.ContactList;
import dukebot.storage.AppStorage;
import dukebot.storage.Storage;
import dukebot.ui.LineName;
import dukebot.ui.LineNameWithContact;
import dukebot.ui.Ui;

public class ListContactCommand extends Command {
    @Override
    public void execute(AppStorage appStorage, Ui ui, Storage storage) {
        ContactList contactList = appStorage.getContactList();
        if (contactList.size() == 0) {
            ui.sayLine(LineName.CONTACT_LIST_EMPTY);
        } else {
            ui.sayLine(LineName.CONTACT_LIST_EXIST);
            for (ContactDetails contactDetails : contactList.getContactList()) {
                ui.sayLineWithContact(LineNameWithContact.PRINT_CONTACT, contactDetails);
            }
        }
    }
}
