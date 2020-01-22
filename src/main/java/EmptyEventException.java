class EmptyEventException extends EmptyTaskException {
    EmptyEventException() {
        super("Oops! The description of an event cannot be empty.");
    }
}
