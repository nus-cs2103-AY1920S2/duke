public class DukeException extends Exception {
    private int index;

    public DukeException(int i) {
        this.index = i;
    }

    @Override
    public String toString() {
        switch (index) {
        case 0: // todo task needs a description
            return "Oh no! :( The description of todo cannot be empty.";
        case 1: // deadline task needs a description
            return "Oh no! :( The description of deadline cannot be empty.";
        case 2: // deadline task needs a deadline
            return "Please include the deadline of this task!";
        case 3: // event task needs a description
            return "Oh no! :( The description for event cannot be empty.";
        case 4: // event task needs a date and time
            return "Please include the date and time of this event!";
        case 5: // for any unrecognised commands
            return "Oh no! I am not sure what you are talking about:(";
        case 6: // for not specifying which task is done
            return "Please let me know which task you are done with!:)";
        case 7: // for not specifying which task to delete
            return "Please let me know which task you want to delete!:)";
        case 8: // file loading error
            return null;
        default:
            return "Error!";
        }
    }
}
