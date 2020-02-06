package app.util;

/**
 * This class is a special child of the Pair class,
 * where it only holds two String values.
 */
public final class StringPair extends Pair {

    /**
     * Initializes a new StringPair object with two strings.
     * @param firstValue The first string
     * @param secondValue The second string
     */
    public StringPair(String firstValue, String secondValue) {
        super(firstValue, secondValue);
    }

    /**
     * Returns the first string of the StringPair object.
     * @return the first string of the StringPair object
     */
    @Override
    public String getFirstValue() {
        return String.valueOf(this.firstValue);
    }
    
    /**
    * Returns the second string of the StringPair object.
    * @return the second string of the StringPair object
    */
    @Override
    public String getSecondValue() {
        return String.valueOf(this.secondValue);
    }
}