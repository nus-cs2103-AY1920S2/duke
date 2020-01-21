public enum Command {
    LIST, DONE, TODO, DEADLINE, EVENT;

    public static Command lookUp(String str) throws InvalidCommandException {
        try {
            return Command.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new InvalidCommandException(str + " is an invalid command");
        }
    }
}
