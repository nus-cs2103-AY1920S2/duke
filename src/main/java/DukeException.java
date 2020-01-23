public class DukeException extends Exception {
    public String givenInput;
    public DukeException(String givenInput) {
        super();
        this.givenInput = givenInput;
    }

    public String toString() {
        if (givenInput.equals("todo") || givenInput.equals("deadline") || givenInput.equals("event")) {
            return "☛ dude, elaborate more!";
        }
        else {
            return "☛ dude, give me a proper command!";
        }
    }


}
