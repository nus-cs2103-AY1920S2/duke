package duke.utils;

public class Pair<T,U> {
    private final T first;
    private final U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public static <T,U> Pair<T,U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Pair<?,?> pair = (Pair<?,?>) obj;
        return first.equals(pair.first) && second.equals(pair.second);
    }

    @Override
    public int hashCode() {
        return 31 * first.hashCode() + second.hashCode();
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", first, second);
    }
}