public class TodoException extends DukeException {

    private String input;
    public TodoException(String input){
        super(input);
    };

    @Override
    public String toString() {
        return "____________________________________________________________\n" +
                " ☹ OOPS!!! You must specify a Task to be done!\n" +
                "\n Please enter as: 'todo <msg>', where msg is your description \n" +
                "____________________________________________________________\n";
    }
}
