public class Parser {
    public static Command parse(String command) throws DuchessException {
        try {
            return Command.valueOf(command.split("\\s", 2)[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DuchessException("I don't see what I can do with what you just told me.");
        }
    }
}
