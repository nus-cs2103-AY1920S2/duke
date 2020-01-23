public class DukeException {
    public void emptyCommandException(String s){
        System.out.println("____________________________________________________________\n:( OOPS!!! The description of a "+s+" cannot be empty.\n____________________________________________________________");
    }
    public void incorrectCommand(){
        System.out.println("____________________________________________________________\n:( OOPS!!! I'm sorry, but I don't know what that means :-(\n____________________________________________________________");
    }
}
