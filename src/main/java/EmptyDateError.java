public class EmptyDateError extends Exceptions {

    public EmptyDateError(Task.Types type) {
        super(type);
    }

    @Override
    public String errorMessage() {
        return "\nOPPS! The date of a " +  super.getType() + " cannot be empty.";
    }
}
