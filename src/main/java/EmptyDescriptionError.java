public class EmptyDescriptionError extends Exceptions {

    public EmptyDescriptionError(Task.Types type) {
        super(type);
    }

    @Override
    public String errorMessage() {
      return "\nOPPS! The description of a " + super.getType() + " cannot be empty";
    }
}
