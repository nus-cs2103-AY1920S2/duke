package duke;

/**
 * Delete Command class that inherits from Command.
 */
public class DeleteContactCommand extends Command {
    protected String done;

    /**
     * Delete Command constructor.
     * @param done index of contact to remove
     */
    public DeleteContactCommand(String done) {
        this.done = done;
    }

    /**
     * Execute the command.
     * @param ui Pass in Ui class
     * @param storage Pass in Storage class
     * @param taskList Pass in taskList class
     */
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        return "";
    }

    public String execute(Ui ui, Storage storage, ContactList contacts) {
        String num = getIndex();
        String response = "Noted! I've removed this Contact:\n";
        response += num + ". " + contacts.getContact(Integer.parseInt(num) - 1);
        contacts.removeContact(Integer.parseInt(num) - 1);
        storage.storeC(contacts.getEntireList());
        response += "\nNow you have " + contacts.getContactListSize() + " contacts in the address book";
        return response;
    }
    /**
     * Method to return the index.
     * @return returns the index in the class
     */
    public String getIndex() {
        return done;
    }
}
