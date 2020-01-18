public class DukeException extends IllegalArgumentException {
    protected static int ID = 1001;
    protected DukeException(String message){
        super(message);
    }

}
