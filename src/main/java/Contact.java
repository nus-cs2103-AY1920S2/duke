import java.util.ArrayList;
public class Contact {
    String name;
    int number;
    String address;
    private static ArrayList<Contact> contactList= new ArrayList<>();

    public Contact(String name, int number, String address) {
        this.name = name;
        this.number = number;
        this.address = address;
    }

    private int getNumber() {
        return this.number;
    }

    private String getName() {
        return this.name;
    }

    private String getAddress() {
        return this.address;
    }

    public static void addToContacts(String input) {
        String[] details = input.split(",");
        String name = details[0];
        int number = Integer.parseInt(details[1]);
        String address = details[2];
        contactList.add(new Contact(name, number, address));
    }

    public String toString() {
        String output = "";
        output = "Name: " + this.getName() + "\n No.: " + this.getNumber() + "\n Address: " + this.getAddress();
        return output;
    }
}
