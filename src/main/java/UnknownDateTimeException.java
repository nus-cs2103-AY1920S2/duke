class UnknownDateTimeException extends DukeException {

    @Override
    public String toString() {
        return "DateTime format is: DD/MM/YYYY HHmm (if, with Time).";
    }
}
