public class Test {
    public static void test(CheckedFunction<String, Integer, NumberFormatException> f) {
        try {
            f.apply("hello");
        } catch (NumberFormatException e) {
            System.out.println("Wrong format!");
        }
    }

    public static void main(String[] args) {
        test(Integer::parseInt);
    }
}

@FunctionalInterface
interface CheckedFunction<T, R, E extends Exception> { R apply(T t) throws E; }