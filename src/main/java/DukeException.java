public class DukeException extends Exception {
    private String error;

    public DukeException(String str) {
        this.error = str;
    }

    @Override
    public String toString() {
        switch (error) {
        case "TODO_NO_DESC": // todo task needs a description
            return "Oh no! :( The description of todo cannot be empty.";
        case "DEADLINE_NO_DESC": // deadline task needs a description
            return "Oh no! :( The description of deadline cannot be empty.";
        case "DEADLINE_NO_DEADLINE": // deadline task needs a deadline
            return "Please include the deadline of this task!";
        case "EVENT_NO_DESC": // event task needs a description
            return "Oh no! :( The description for event cannot be empty.";
        case "EVENT_NO_DATE_AND_TIME": // event task needs a date and time
            return "Please include the date and time of this event!";
        case "OTHERS": // for any unrecognised commands
            return "Oh no! I am not sure what you are talking about:(";
        case "UNK_TASK_DONE": // for not specifying which task is done
            return "Please let me know which task you are done with!:)";
        case "UNK_TASK_TO_DELETE": // for not specifying which task to delete
            return "Please let me know which task you want to delete!:)";
        case "LOAD_ERROR": // file loading error
            return null;
        default:
            return "Error!";
        }
    }
}
