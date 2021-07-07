package duke;

public class Contact implements java.io.Serializable {
    protected String name;
    protected String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "[C] Name:" + this.name + " Contact Number:" + this.phoneNumber;
    }
}
