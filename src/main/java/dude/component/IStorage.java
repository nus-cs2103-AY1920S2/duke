package dude.component;

public interface IStorage {
    /**
     * Loads the TaskList from the previous Dude session from memory.
     * If data from the previous session cannot be found, returns an empty TaskList.
     *
     * @param ui User Interface which Dude chatbot uses to report errors when loading data.
     * @return TaskList with all tasks from previous session if successful, else shows an error message,
     *  and returns TaskList with tasks that were successfully parsed from memory.
     */
    TaskList restoreSession(IUserInterface ui);

    /**
     * Saves the TaskList from the current session into some form of persistent memory.
     * If an error occurs in writing the data, reports the error and warns users that data may be lost.
     *
     * @param ui User Interface which Dude chatbot uses to report errors when saving data.
     * @param session TaskList containing Tasks to save to memory.
     */
    void saveSession(IUserInterface ui, TaskList session);
}
