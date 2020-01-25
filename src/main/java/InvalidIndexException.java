public class InvalidIndexException extends DukeException {
    int size;
    int num;

    InvalidIndexException(int num, int size) {
        this.size = size;
        this.num = num;
    }

    @Override
    public String toString() {
        String back = "You currently have " + size + (size < 2 ? " task." : " tasks.") + "\nPlease enter a number in the range 1 to " + size + ".\n";
        if (num < 1) {
            return "Index supplied is too small. " + back;
        }
        return "Index supplied is too large. " + back;
    }
}
