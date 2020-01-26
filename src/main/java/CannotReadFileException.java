class CannotReadFileException extends DukeException {

    @Override
    public String toString() {
        return "☹ OOPS!!! There was a problem reading your file; new data file created.";
    }
}
