package dukebot.contactlist;

import java.io.Serializable;

public class ContactDetail implements Serializable {
    private String name;
    private int phoneNumber;

    /**
     * Generates a contact.
     *
     * @param name  Name of contact to add.
     * @param phoneNumber Phone number of the contact.
     */
    public ContactDetail(String name, int phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the contact's name.
     *
     * @return Name of contact
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the contact's number.
     *
     * @return Phone number of contact
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

}
