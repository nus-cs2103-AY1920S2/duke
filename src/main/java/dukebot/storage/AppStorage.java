package dukebot.storage;

import dukebot.contactlist.ContactList;
import dukebot.tasklist.TaskList;

public class AppStorage {
    private TaskList taskList;
    private ContactList contactList;

    public AppStorage(TaskList taskList, ContactList contactList) {
        this.taskList = taskList;
        this.contactList = contactList;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public ContactList getContactList() {
        return contactList;
    }
}
