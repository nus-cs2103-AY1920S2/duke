public class DukeException extends Exception {
    protected String exception;
    public DukeException(String exception){
        this.exception = exception;
    }

    @Override
    public String toString(){
        if (exception.equals("todo")){
            return "      OOPS!!! The description of a todo cannot be empty.";
        }
        else{
            return "      OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }
}
