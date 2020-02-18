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
            return "Please include the deadline of this task! \nIt needs to be specified in the following "
                    + "format: YYYY/MM/DD HHmm e.g. 2019/02/01 0900";
        case "EVENT_NO_DESC": // event task needs a description
            return "Oh no! :( The description for event cannot be empty.";
        case "EVENT_NO_DATE_AND_TIME": // event task needs a date and time
            return "Please include the date and timing details of this event!";
        case "UNK_TASK_DONE": // for not specifying which task is done
            return "Please let me know which task you are done with!:)";
        case "UNK_TASK_TO_DELETE": // for not specifying which task to delete
            return "Please let me know which task you want to delete!:)";
        case "UNK_TASK_TO_FIND": // for not specifying which task to find
            return "You have not specify what item you want me to look for!!";
        case "ITEM_NOT_FOUND": // when the keyword inputted cannot be found in any of the tasks in the task list
            return "The keyword cannot be found in any of the tasks in your task list!";
        case "UPDATE_DEADLINE_DATE_TIME_NEEDED": // new deadline date and time not provided
            return "Please provide both the date and time for this deadline task:)" + "\n" + "Reminder: It needs to "
                    + "be specified in the following format: YYYY/MM/DD HHmm e.g. 2019/02/01 0900";
        case "COMMAND_NOT_FOUND":
            return "Task not found! Please check the list if you forgot the task you inputted!";
        case "LOAD_ERROR": // file loading error
            return null;
        case "OTHERS": // for any unrecognised commands
            return "\u2620" + " Oh no! I am not sure what you are talking about!";
        default:
            return "Error!";
        }
    }
}
