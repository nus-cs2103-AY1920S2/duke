package duke.Exception;

public class EmptyDateError extends Exceptions {

    public EmptyDateError(String type) {

        super(type);
    }

    @Override
    public String errorMessage() {

        return "\nOPPS! The date of a " +  super.getType() + " cannot be empty.";

    }
}
