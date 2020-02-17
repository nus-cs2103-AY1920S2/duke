public class EmptyTaskException extends InvalidInputException {
    @Override
    public String toString() {
        return "Sorry the task requires some description";
    }
}
