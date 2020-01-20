public class Parser {

    private static Parser parser=null;

    private Parser(){}

    public static Parser getInstance() throws SingletonException{
        if(parser==null){
            parser=new Parser();
            return parser;
        }
        throw new SingletonException("There should only be one parser.");
    }

    public Command parse(String userInput) throws DukeException {

        if(userInput.contains(" ")){
            String keyword=userInput.substring(0,userInput.indexOf(" "));
            String information=userInput.substring(userInput.indexOf(" ")+1);

            switch (keyword){
                case "done":
                    return parseDone(information);
                case "delete":
                    return parseDelete(information);
                case "todo":
                    return parseTodo(information);
                case "deadline":
                    return parseDeadline(information);
                case "event":
                    return parseEvent(information);
            }
        }else{
            switch (userInput){
                case "list":
                    return parseList();
                case "bye":
                    return parseBye();
            }
        }
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }


    private Command parseBye(){
        return new Command_Exit();
    }

    private Command parseList(){
        return new Command_List();
    }

    private Command parseDone(String information) throws DukeException {
        try {
            int index = Integer.parseInt(information) - 1;
            return new Command_Done(index);
        }catch (NumberFormatException e){
            throw new DukeException("The input for done must contain a number.");
        }
    }

    private Command parseDelete(String information) throws DukeException {
        try {
            int index = Integer.parseInt(information) - 1;
            return new Command_Delete(index);
        }catch (NumberFormatException e){
            throw new DukeException("The input for done must contain a number.");
        }
    }

    private Command parseTodo(String information) throws DukeException {
        if(information.trim().length()==0) {
            throw new DukeException("The description of the todo cannot be empty.");
        }
        return new Command_AddToDo(information);
    }

    private Command parseDeadline(String information) throws DukeException {
        if(!information.contains(" /by ")){
            throw new DukeException("The input for deadline must contain /by ");
        }

        String description=information.substring(0,information.indexOf(" /by "));
        if(description.trim().length()==0) {
            throw new DukeException("The description of the deadline cannot be empty.");
        }

        String time=information.substring(information.indexOf(" /by ")+5);
        if(time.trim().length()==0) {
            throw new DukeException("The time of the deadline cannot be empty.");
        }

        return new Command_AddDeadline(description,time);
    }

    private Command parseEvent(String information) throws DukeException {
        if(!information.contains(" /at ")){
            throw new DukeException("The input for event must contain /at ");
        }

        String description=information.substring(0,information.indexOf(" /at "));
        if(description.trim().length()==0) {
            throw new DukeException("The description of the event cannot be empty.");
        }

        String time=information.substring(information.indexOf(" /at ")+5);
        if(time.trim().length()==0) {
            throw new DukeException("The time of the event cannot be empty.");
        }

        return new Command_AddEvent(description,time);
    }

}
