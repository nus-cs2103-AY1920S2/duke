public class DukeException extends Exception {
    public String tmp = "";
    public int num = 0;
    public String tmp2 = "";
    public String tmp3 = "";

    public DukeException(String tmp) {
        super(tmp);
        this.tmp = tmp;
    }

    public DukeException(String tmp, int num) {
        super(tmp);
        this.tmp = tmp;
        this.num = num;
    }

    public DukeException(String tmp, String tmp2, String tmp3) {
        super(tmp);
        this.tmp = tmp;
        this.tmp2 = tmp2;
        this.tmp3 = tmp3;
    }

    @Override
    public String toString() {
        //Argument of delete is not 1 (total of 2)
        if (tmp.equals("delete argument not found")) {
            return "Please provide a valid number to delete.";
        //If the bot does not understand the command
        } else if (tmp.equals("Don't understand")) {
            return "Sumimasen, I can't understand what chu talking about. Try again?";
        } else if (tmp.equals("unable to delete from list")) {
            return "Unable to delete " + num + " from the list. Please try again with a valid list number.";
        } else if (tmp.equals("unable to mark done")) {
            return "Unable to mark list #" + num + " as done. Please try again with a valid list number.";
        } else if (tmp3.equals("no slash")) {
            return "Please provide a valid deadline. For example, 「" + tmp + " " + tmp2 + " /by Monday」.";
        //If exception is command that the bot understands but exception thrown due to invalid arguments
        } else {
            return "The description of 「" + tmp + "」 cannot be empty!!";
        }
    }
}
