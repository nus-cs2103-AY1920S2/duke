public class NoSuchDeleteException extends DukeException {
    
    @Override
    public String toString() {
        return Constant.ERROR_LINE + "\n    ☹ OI. Check properly... Where got such task. >:(\n"
                + Constant.ERROR_LINE;
    }
}