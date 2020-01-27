public class InvalidDateError extends Exceptions {


    public InvalidDateError() {
        super(null);
    }

    @Override
    public String errorMessage() {

        return "Date should be in d/MM/yyyy HH:mm format";

    }
}
