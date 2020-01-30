package duke.exceptions;

import duke.enums.ErrorCodes;

public class DukeException extends Exception {
    private ErrorCodes errorCode;
    private String errorInfo;

    public DukeException(ErrorCodes errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public DukeException(ErrorCodes errorCode, String errorInfo) {
        super();
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

    @Override
    public String getMessage() {
        String message = "";
        switch (errorCode) {
        case UNKNOWN_COMMAND:
            message = "Sorry boss, I don't know that command. Try again, boss?";
            break;
        case MISSING_TASK_NAME:
            message = "Boss, I hate to say this but you forgot to tell me what the task is...";
            break;
        case MISSING_EVENT_DATE:
            message = "Err boss, you forgot to tell me when the event is going to happen";
            break;
        case MISSING_DEADLINE_DATE:
            message = "Boss, I can't tell when it's due. Could you be a little clearer?";
            break;
        case INVALID_TASK_INDEX:
            message = "Boss, you do know there's not that many tasks right?";
            break;
        case INVALID_DATE_FORMAT:
            message = "I don't understand the date, boss. Type the date like this YYYY-MM-DD";
            break;
        case FILE_NOT_FOUND:
            message = "Boss, I can't find my notebook... Sorry but I can't find duke.txt";
            break;
        case LOADING_ERROR:
            message = "Oops boss, I've got trouble reading the duke.txt file, from line "
                + this.errorInfo + " onwards... I'll just stop here and load what I have";
            break;
        case SAVING_ERROR:
            message = "Oops boss, I've got trouble trying to save from this task ("
                + this.errorInfo + ") onwards... \nI'll just stop here and save what I have";
            break;
        default:
            message = "Uh-oh boss, I don't feel so good...";
            break;
        }
        return message;
    }
}