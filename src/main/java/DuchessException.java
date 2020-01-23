public class DuchessException extends Exception {
    public DuchessException(String errorMessage) {
        super("\tStop causing me trouble...\n\t" + errorMessage);
    }
}
