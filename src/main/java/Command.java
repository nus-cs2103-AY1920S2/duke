public enum Command {
    LIST, DONE, TODO, DEADLINE, EVENT;

    public static Command lookUp(String str) {
        return Command.valueOf(str.toUpperCase());
    }
}
