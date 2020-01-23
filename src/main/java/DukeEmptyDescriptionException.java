class DukeEmptyDescriptionException extends Exception {
    DukeEmptyDescriptionException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
