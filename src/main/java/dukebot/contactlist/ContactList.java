package dukebot.contactlist;

import java.util.ArrayList;

public class ContactList {
    private ArrayList<ContactDetail> contactList;

    /**
     * Creates a contact list.
     *
     * @param contactList The contact list to use.
     */
    public ContactList(ArrayList<ContactDetail> contactList) {
        this.contactList = contactList;
    }

    /**
     * Gets TaskList.
     *
     * @return The stored contact list.
     */
    public ArrayList<ContactDetail> getContactList() {
        return this.contactList;
    }

    /**
     * Adds ContactDetails to end of ContactList.
     *
     * @param contactDetail ContactDetails to add.
     */
    public void add(ContactDetail contactDetail) {
        contactList.add(contactDetail);
    }

    /**
     * Returns size of ContactList.
     *
     * @return Size of ContactList.
     */
    public int size() {
        return this.contactList.size();
    }

    /**
     * Deletes task at specified index.
     *
     * @param contactInd Index of task.
     */
    public ContactDetail deleteTask(int contactInd) {
        if (contactInd >= size() || contactInd < 0) {
            return null;
        } else {
            ContactDetail contactDetail = this.contactList.get(contactInd);
            this.contactList.remove(contactInd);
            return contactDetail;
        }
    }
}
