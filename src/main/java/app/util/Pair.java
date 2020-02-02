package app.util;

public class Pair {
    protected Object firstValue;
    protected Object secondValue;

    public Pair(Object firstValue, Object secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public Object getFirstValue() {
        return this.firstValue;
    }

    public Object getSecondValue() {
        return this.secondValue;
    }
}