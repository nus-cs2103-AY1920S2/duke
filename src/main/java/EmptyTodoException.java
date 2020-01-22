class EmptyTodoException extends EmptyTaskException {
    EmptyTodoException() {
        super("Oops! The description of a todo cannot be empty.");
    }
}
