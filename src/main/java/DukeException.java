public class DukeException extends Exception{

    public DukeException(String msg){
        super(msg);
    }

    public String toString(){
        return "____________________________________________________________\n" +
                "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "____________________________________________________________\n";
    }
}
