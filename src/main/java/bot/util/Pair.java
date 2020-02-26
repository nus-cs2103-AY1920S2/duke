package bot.util;

/**
 * A class the represents a pair of objects
 *
 * @param <T> The type of the first object.
 * @param <U> The type of the second object.
 */
public class Pair<T, U> {
    private final T first;
    private final U second;

    /**
     * Constructor for a Pair
     *
     * @param f The object to be set as
     *          the Pair's first object
     * @param s The object to be set as
     *          the Pair's second object
     */
    public Pair(T f, U s) {
        this.first = f;
        this.second = s;
    }

    /**
     * Gets the first Object in this Pair.
     *
     * @return The first Object, with type T
     */
    public T getFirst() {
        return this.first;
    }

    /**
     * Gets the second Object in this Pair.
     *
     * @return The second Object, with type U
     */
    public U getSecond() {
        return this.second;
    }

    @Override
    public String toString() {
        return "(" + this.first.toString() + ", "
                + this.second.toString() + ")";
    }
}
