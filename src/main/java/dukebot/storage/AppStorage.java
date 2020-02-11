package dukebot.storage;

import dukebot.contactlist.ContactList;
import dukebot.tasklist.TaskList;

public class AppStorage {
    private TaskList taskList;
    private ContactList contactList;

    public AppStorage(TaskList taskList, ContactList contactList) {
        assert taskList != null;
        assert contactList != null;
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
