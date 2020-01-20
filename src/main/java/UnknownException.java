public class UnknownException extends Exception{
    public static final String string = "    ____________________________________________________________";
    public static final String space = "     ";
    public UnknownException() {};

    public String toString() {
        return string + "\n" + space + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + string;
    }
}
