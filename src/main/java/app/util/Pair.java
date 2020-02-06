package app.util;

/**
 * This class represents a data structure that contains two values.
 * This is similar to a tuple in other programming languages.
 */
public class Pair {
    protected Object firstValue;
    protected Object secondValue;

    /**
     * Initializes a new Pair object with two values.
     * @param firstValue The first value of the object
     * @param secondValue The second value of the object
     */
    public Pair(Object firstValue, Object secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    /**
     * Returns the first value of the Pair object.
     * @return the first value of the Pair object
     */
    public Object getFirstValue() {
        return this.firstValue;
    }

    /**
     * Returns the second value of the Pair object.
     * @return the second value of the Pair object
     */
    public Object getSecondValue() {
        return this.secondValue;
    }
}