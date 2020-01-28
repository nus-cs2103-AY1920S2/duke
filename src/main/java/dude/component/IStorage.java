package dude.component;

public interface IStorage {
    TaskList restoreSession(IUserInterface ui);

    void saveSession(IUserInterface ui, TaskList session);
}
