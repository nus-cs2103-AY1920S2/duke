public class DukeException extends Exception {
    private int errorType;
    // 0 - Unknown command
    // 1 - Incomplete todo
    // 2 - Incomplete event
    // 3 - Incomplete deadline
    // 4 - Done argument out of range
    // Default - Some unknown error

    public DukeException(int errorType) {
        super();
        this.errorType = errorType;
    }

    @Override
    public String getMessage() {
        String message = "";
        switch (errorType) {
        case 0:
            message = "Sorry boss, I don't know that command. Try again, boss?";
            break;
        case 1:
            message = "Boss, I hate to say this but you forgot to tell me what the task is...";
            break;
        case 2:
            message = "Err boss, you forgot to tell me when the event is going to happen";
            break;
        case 3:
            message = "Boss, I can't tell when it's due. Could you be a little clearer?";
            break;
        case 4:
            message = "Boss, you do know there's not that many tasks right?";
            break;
        default:
            message = "Uh-oh boss, I don't feel so good...";
            break;
        }
        return message;
    }
}