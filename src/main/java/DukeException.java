public class DukeException extends Exception {

    protected DukeErrorType errorType;
    protected String commandName;

    public DukeException(String message, DukeErrorType errorType, String commandName) {
        super(message);
        this.errorType = errorType;
        this.commandName = commandName;
    }

    public DukeException(String message, DukeErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    /**
     * Returns a short description of this throwable.
     * The result is the concatenation of:
     * <ul>
     * <li> the {@linkplain Class#getName() name} of the class of this object
     * <li> ": " (a colon and a space)
     * <li> the result of invoking this object's {@link #getLocalizedMessage}
     *      method
     * </ul>
     * If {@code getLocalizedMessage} returns {@code null}, then just
     * the class name is returned.
     *
     * @return a string representation of this throwable.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        switch (errorType) {
            case INVALID_COMMAND:
                stringBuilder.append("      :( Small peepee move, please give valid command!");
                break;
            case EMPTY_DESCRIPTION:
                stringBuilder.append("      :( WHOOPS I did it again~\n" + "      " +commandName
                        + " description is empty!\n      Describe it for my smol brain please :)");
                break;
            case EMPTY_TIME:
                stringBuilder.append("      Date and time?\n" + "      " +commandName
                        + " time is empty!\n      Set it for your smol brain please :)");
                break;
        }
        return stringBuilder.toString();
    }
}