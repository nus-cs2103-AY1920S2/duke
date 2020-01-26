class UnknownDateTimeException extends DukeException {

    @Override
    public String toString() {
        return "Use DD/MM/YYYY HHMM, for nicely formatted datetime!";
    }
}
