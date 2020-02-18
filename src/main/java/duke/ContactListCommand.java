package duke;

/**
 * List Command class that inherits from Command.
 */
public class ContactListCommand extends Command {

    public String execute(Ui ui, Storage storage, ContactList contacts) {
        try {
            String response;
            if (contacts.getContactListSize() == 0) {
                throw new EmptyListException();
            } else {
                response = "Here are the contacts in your contact book: \n";
                for (int i = 0; i < contacts.getContactListSize(); i++) {
                    response += (i + 1) + ". " + contacts.getContact(i).toString() + "\n";
                }
                return response;
            }
        } catch (DukeException ex) {
            return ex.toString();
        }
    }
    public String execute(Ui ui, Storage storage, TaskList tasks) {
        return "";
    }
}
