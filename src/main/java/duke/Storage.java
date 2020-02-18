package duke;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Storage class for duke.
 */
public class Storage {

    protected String filePath;

    /**
     * Constructor for Storage.
     * @param filePath filepath of storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task into duke.
     * @return the arrayList of task
     */
    public ArrayList<Task> load() {
        try {
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);
            return (ArrayList) in.readObject();
        } catch (IOException e) {
            System.out.println(e + "\n" + "Creating a new storage file.....");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + " is caught");
        }
        return new ArrayList<Task>();
    }

    /**
     * Stores the task list into the system.
     * @param taskList takes in the tasklist
     */
    public void store(ArrayList<Task> taskList) {
        try {
            //saving file as object
            FileOutputStream file = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(taskList);
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Loads the task into duke.
     * @return the arrayList of task
     */
    public ArrayList<Contact> loadC() {
        try {
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);
            return (ArrayList) in.readObject();
        } catch (IOException e) {
            System.out.println(e + "\n" + "Creating a new storage file.....");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException" + " is caught");
        }
        return new ArrayList<Contact>();
    }

    /**
     * Stores the contact list into the system.
     * @param contactList takes in the contactlist
     */
    public void storeC(ArrayList<Contact> contactList) {
        try {
            //saving file as object
            FileOutputStream file = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(contactList);
            out.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
