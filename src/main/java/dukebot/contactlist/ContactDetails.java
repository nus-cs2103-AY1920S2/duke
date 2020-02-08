package dukebot.contactlist;

import java.io.Serializable;

public class ContactDetails implements Serializable {
    private String name;
    private int phoneNumber;
    // private String notes;

    // public ContactDetails(String name, int phoneNumber, String notes) {
    //     this.name = name;
    //     this.phoneNumber = phoneNumber;
    //     this.notes = notes;
    // }

    public ContactDetails(String name, int phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        // this.notes = "";
    }

    public String getName() {
        return name;
    }

    // public void setName(String name) {
    //     this.name = name;
    // }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    // public void setPhoneNumber(int phoneNumber) {
    //     this.phoneNumber = phoneNumber;
    // }

    // public String getNotes() {
    //     return notes;
    // }

    // public void setNotes(String notes) {
    //     this.notes = notes;
    // }

}
