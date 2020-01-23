class DukeNoKeywordException extends Exception {
    DukeNoKeywordException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return super.getMessage();
    }
}
