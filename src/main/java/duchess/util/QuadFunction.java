package duchess.util;

import duchess.exception.DuchessException;

/**
 * Functional interface for a function object that takes
 * in four arguments to return a single return value.
 *
 * @param <T1> First generic type of QuadFunction
 * @param <T2> Second generic type of QuadFunction
 * @param <T3> Third generic type of QuadFunction
 * @param <T4> Fourth generic type of QuadFunction
 * @param <R>  Return generic type of QuadFunction
 */
@FunctionalInterface
public interface QuadFunction<T1, T2, T3, T4, R> {
    /**
     * Returns a value based on the provided four parameters
     * and the function implementing this interface. Throws a
     * {@code DuchessException}.
     *
     * @param t1 First parameter of QuadFunction.
     * @param t2 Second parameter of QuadFunction.
     * @param t3 Third parameter of QuadFunction.
     * @param t4 Fourth parameter of QuadFunction.
     * @return Return value of the function.
     * @throws DuchessException Thrown by implementations of this function.
     */
    public R apply(T1 t1, T2 t2, T3 t3, T4 t4) throws DuchessException;
}
