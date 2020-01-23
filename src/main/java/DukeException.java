public class DukeException extends Exception {
    public String givenInput;
    public String type;
    public DukeException(String type, String givenInput) {
        this.givenInput = givenInput;
        this.type = type;
    }
    public DukeException(String type) {
        this.givenInput = "";
        this.type = type;
    }


    public String toString() {
        if (type.equals("task")) {
            if (givenInput.equals("todo") || givenInput.equals("deadline") || givenInput.equals("event")) {
                return "☛ dude, elaborate more!";
            }
            else {
                return "☛ dude, give me a proper command!";
            }
        }
        else if (type.equals("done") || type.equals("delete")) {
            return "☛ dude, no such task exists!";
        }
        else {
            return "error";
        }
    }

}
