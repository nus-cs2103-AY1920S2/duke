public class NoNumberDeleteException extends DukeException {

    @Override
    public String toString() {
        return Constant.ERROR_LINE + "\n    ☹ OI. Specify which task to delete! >:(\n"
                + Constant.ERROR_LINE;
    }
}
