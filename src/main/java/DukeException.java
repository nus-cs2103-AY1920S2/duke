public class DukeException extends Exception {
    public DukeException () {}
    public DukeException(String message) {
        super(message);
    }
    public String toString() {
        String msg = this.getMessage();
        String str = "";
        switch (msg) {
            case "todo":
                str = "Sorry~ The description of a todo cannot be empty desu!! >:(";
                break;
            case "deadline" :
                str = "Sorry~ The description of a deadline cannot be empty desu!! >:(";
                break;
            case "event"   :
                str = "Sorry~ The description of a event cannot be empty desu!! >:(";
                break;
            case "done" :
                str = "Sorry~ The task number is invalid!! >:(";
                break;
            case "invalid" :
                str = "Sorry~ I don't know what this means >.<";
                break;
            case "delete" :
                str = "Sorry~ The task number is invalid!! >:(";
                break;
        }
        return str;
    }
}