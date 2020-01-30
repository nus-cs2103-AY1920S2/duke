package duchess.util;

public class Pair<U, T> {
    private U first;
    private T second;

    public Pair(U first, T second) {
        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return this.first;
    }

    public T getSecond() {
        return this.second;
    }
}
