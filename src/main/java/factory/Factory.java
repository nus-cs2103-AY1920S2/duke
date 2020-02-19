package factory;

/**
 * Interface for factory classes used to create different tasks.
 *
 * @param <T> Different types of tasks.
 */
public interface Factory<T> {
    T create(String userInput);

}
