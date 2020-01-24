public abstract class DukeException extends Exception {
    public DukeException(String errMsg) {
        super(errMsg);
    }

    @Override
    public String toString(){
        return "☹ OOPS!!! " + this.getMessage();
    }
}
