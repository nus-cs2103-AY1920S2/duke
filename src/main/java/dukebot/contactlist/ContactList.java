package dukebot.contactlist;

import dukebot.tasklist.Task;

import java.util.ArrayList;

public class ContactList {
    private ArrayList<ContactDetails> contactList;

    public ContactList(ArrayList<ContactDetails> contactList) {
        this.contactList = contactList;
    }

    /**
     * Gets TaskList.
     *
     * @return The stored contact list.
     */
    public ArrayList<ContactDetails> getContactList() {
        return this.contactList;
    }

    /**
     * Adds ContactDetails to end of ContactList.
     *
     * @param contactDetails ContactDetails to add.
     */
    public void add(ContactDetails contactDetails) {
        contactList.add(contactDetails);
    }

    /**
     * Returns size of ContactList.
     *
     * @return Size of ContactList.
     */
    public int size() {
        return this.contactList.size();
    }
}
