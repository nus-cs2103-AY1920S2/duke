public class TimeFormatException extends RuntimeException {
    TimeFormatException() {
        super("Please enter date in the format yyyy-MM-dd HHmm");
    }
}
