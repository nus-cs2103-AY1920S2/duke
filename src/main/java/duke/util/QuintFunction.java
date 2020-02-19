package duke.util;

import duke.exception.DuchessException;

/**
 * Functional interface for a function object that takes in four arguments to
 * return a single return value.
 *
 * @param <T1> First generic type of QuadFunction
 * @param <T2> Second generic type of QuadFunction
 * @param <T3> Third generic type of QuadFunction
 * @param <T4> Fourth generic type of QuadFunction
 */
@FunctionalInterface
public interface QuintFunction<T1, T2, T3, T4, T5> {
    /**
     * Returns a value based on the provided four parameters and the function
     * implementing this interface. Throws a {@code DuchessException}.
     *
     * @param t1 First parameter of QuadFunction.
     * @param t2 Second parameter of QuadFunction.
     * @param t3 Third parameter of QuadFunction.
     * @param t4 Fourth parameter of QuadFunction.
     * @return String result
     * @throws DuchessException Thrown by implementations of this function.
     */
    String apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) throws DuchessException;
}
