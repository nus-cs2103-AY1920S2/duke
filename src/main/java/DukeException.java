public class DukeException extends Exception {
    public String tmp;

    public DukeException(String tmp) {
        super(tmp);
        this.tmp = tmp;
    }

    @Override
    public String toString() {
        //Argument of delete is not 1 (total of 2)
        if (tmp.equals("delete argument not found")) {
            return "Please provide a valid number to delete.";
            //If exception is command that the bot understands but exception thrown due to invalid arguments
        } else if (!tmp.equals("don't understand")) {
            return "The description cannot be empty!!";
            //If delete command does not have argument
        } else {
            return "Sumimasen, I can't understand what chu talking about. Try again?";
        }
    }
}
