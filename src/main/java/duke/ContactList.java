package duke;

import java.util.ArrayList;

/**
 * Class for ContactList.
 */
public class ContactList {

    ArrayList<Contact> contacts = new ArrayList<>();

    /**
     * ContactList Constructor.
     * @param contacts takes in arraylist of contacts
     */
    public ContactList(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    /**
     * Returns the size of contacts list.
     * @return size of the arraylist
     */
    public int getContactListSize() {
        return contacts.size();
    }

    /**
     * Gets the Contact.
     * @param i index of the Contact
     * @return the Contact object
     */
    public Contact getContact(int i) {
        return contacts.get(i);
    }

    /**
     * Removes a Contact.
     * @param i index of contact to remove
     */
    public void removeContact(int i) {
        contacts.remove(i);
    }

    /**
     * Adds in the Contact.
     * @param c takes in a Contact
     */
    public void addContact(Contact c) {
        contacts.add(c);
    }

    /**
     * Returns the entire list of contacts.
     * @return arraylist of contacts
     */
    public ArrayList<Contact> getEntireList() {
        return contacts;
    }
}

