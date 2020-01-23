/*
 * Pair
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 23 Jan 2020
 *
 */

/**
 * The Pair class represents a set containing 2 elements.
 * The class is implemented using generic variables.
 * @author Mario Lorenzo
 */

public class Pair<U, V> {
    private U first;
    private V second;

    /**
     * Constructs the Pair instance with.
     * @param first The first element of the pair.
     * @param second The second element of the pair.
     */

    public Pair(U first, V second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return this.first;
    }

    public V getSecond() {
        return this.second;
    }
}
