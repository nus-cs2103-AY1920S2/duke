public enum Command {
    LIST, DONE, TODO, DEADLINE, EVENT, DELETE;

    public static Command lookUp(String str) throws InvalidCommandException {
        try {
            return Command.valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new InvalidCommandException(str + " is an invalid command");
        }
    }
}
