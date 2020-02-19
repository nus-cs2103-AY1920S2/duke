package factory;

import tasks.Task;

public interface Factory<T> {
    T create(String userInput);

}
