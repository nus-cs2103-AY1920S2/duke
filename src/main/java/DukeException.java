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
                str = "Gomenanasai~ Da description of a todo cannot be empty desu!! >:(";
                break;
            case "deadline" :
                str = "Gomenanasai~ Da description of a deadline cannot be empty desu!! >:(";
                break;
            case "event"   :
                str = "Gomenanasai~ Da description of a event cannot be empty desu!! >:(";
                break;
            case "done" :
                str = "Gomenanasai~ Da task number is invalid!! >:(";
                break;
            case "invalid" :
                str = "Gomenanasai~ Boku dont know what this means desu >.<";
                break;
        }
        return str;
    }
}