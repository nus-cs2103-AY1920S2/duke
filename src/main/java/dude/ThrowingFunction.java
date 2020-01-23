package dude;

/**
 * Helper functional interface for functions that throw checked exceptions
 * 
 * @param <T> type of input the function takes
 * @param <R> type of output the function returns
 * @param <E> type of checked exception the function throws
 */
public interface ThrowingFunction<T, R, E extends Exception> { 
    R apply(T t) throws E; 
}