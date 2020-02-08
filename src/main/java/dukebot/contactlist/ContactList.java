package dukebot.contactlist;

import java.util.ArrayList;

public class ContactList {
    private ArrayList<ContactDetails> contactList;

    public ContactList(ArrayList<ContactDetails> contactList) {
        this.contactList = contactList;
    }

    public void add(ContactDetails contactDetails) {
        contactList.add(contactDetails);
    }
}
