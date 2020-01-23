public class NoTimeSpecifiedException extends DukeException {
    @Override
    public String toString() {
        return "You forgot to put the date/ time for your task!";
    }
}
