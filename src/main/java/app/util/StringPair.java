package app.util;

public final class StringPair extends Pair {
    public StringPair(String firstValue, String secondValue) {
        super(firstValue, secondValue);
    }

    @Override
    public String getFirstValue() {
        return String.valueOf(this.firstValue);
    }

    @Override
    public String getSecondValue() {
        return String.valueOf(this.secondValue);
    }
}