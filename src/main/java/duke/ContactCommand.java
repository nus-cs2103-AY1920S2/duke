package duke;

public class ContactCommand extends Command {
    protected String description;
    protected String phoneNumber;

    public ContactCommand(String description, String phoneNumber) {
        this.description = description;
        this.phoneNumber = phoneNumber;
    }

    public String execute(Ui ui, Storage storage, TaskList tasks) {
        return "";
    }

    public String execute(Ui ui, Storage storage, ContactList contacts) {
        Contact c = new Contact(description, phoneNumber);
        contacts.addContact(c);
        storage.storeC(contacts.getEntireList());
        return ui.printContactComplete();
    }
}
