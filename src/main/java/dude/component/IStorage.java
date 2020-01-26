package dude.component;

import dude.Task;

import java.util.List;

public interface IStorage {
    TaskList restoreSession(IUserInterface ui);
    void saveSession(IUserInterface ui, TaskList session);
}
