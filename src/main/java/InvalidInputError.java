public class InvalidInputError extends Exceptions {

public InvalidInputError () {
    super(null);
}

    @Override
    public String errorMessage() {
        return "OOPS! I'm sorry, but I don't know what that means.";
    }
}
