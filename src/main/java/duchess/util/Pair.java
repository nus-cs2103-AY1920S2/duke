package duchess.util;

/**
 * The {@code Pair} class is a helper class that keeps two different
 * pieces of data together.
 *
 * @param <U> Type of first element.
 * @param <T> Type of second element.
 */
public class Pair<U, T> {
    private U first;
    private T second;

    /**
     * Initialises an instance of {@code Pair}.
     *
     * @param first  The first element of the {@code Pair}.
     * @param second The second element of the {@code Pair}.
     */
    public Pair(U first, T second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Returns the first element of the {@code Pair}.
     *
     * @return The first element.
     */
    public U getFirst() {
        return this.first;
    }

    /**
     * Returns the second element of the {@code Pair}.
     *
     * @return The second element.
     */
    public T getSecond() {
        return this.second;
    }
}
