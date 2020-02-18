public class DukeException extends Exception {
    public DukeException(String errorMsg) {
        super(new String(Character.toChars(0x1F62D)) + " " + errorMsg);
    }
}