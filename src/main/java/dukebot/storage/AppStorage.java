package dukebot.storage;

import dukebot.contactlist.ContactList;
import dukebot.tasklist.TaskList;

public class AppStorage {
    private TaskList taskList;
    private ContactList contactList;

    /**
     * Generates the main storage of the app.
     *
     * @param  taskList TaskList to store.
     * @param contactList ContactList to store.
     */
    public AppStorage(TaskList taskList, ContactList contactList) {
        assert taskList != null;
        assert contactList != null;
        this.taskList = taskList;
        this.contactList = contactList;
    }

    /**
     * Gets the task list.
     *
     * @return The task list.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Gets the contact list.
     *
     * @return The contact list.
     */
    public ContactList getContactList() {
        return contactList;
    }
}
