class EmptyTodoException extends EmptyTaskException {
    EmptyTodoException() {
        super("Oops! The description of an event cannot be empty.");
    }
}
