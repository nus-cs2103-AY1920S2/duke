public class DukeException extends Exception {
    public String tmp;

    public DukeException(String tmp) {
        super(tmp);
        this.tmp = tmp;
    }

    @Override
    public String toString() {
        if (tmp.equals("don't understand") == false) {
            return "The description cannot be empty!!";
        } else {
            return "Sumimasen, I can't understand what chu talking about. Try again?";
        }
    }
}
