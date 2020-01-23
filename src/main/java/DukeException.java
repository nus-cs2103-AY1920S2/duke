public class DukeException extends Exception {
    int i;

    public DukeException(int i) {
        this.i = i;
    }

    public String toString() {
        switch (i) {
            case 1: // todo task needs a description
                return "Oh no! :( The description of todo cannot be empty.";
            case 2: // deadline task needs a description
                return "Oh no! :( The description of deadline cannot be empty.";
            case 3: // deadline task needs a deadline
                return "Please include the deadline of this task!";
            case 4: // event task needs a description
                return "Oh no! :( The description for event cannot be empty.";
            case 5: // event task needs a date and time
                    return "Please include the date and time of this event!";
            case 6: // for any unrecognised commands
                return "Oh no! I am not sure what you are talking about:(";
            case 7: // for not specifying which task is done
                return "Please let me know which task you are done with!:)";
        }
        return "ERROR";
    }
}
