public class Parser {

    private static Parser parser=null;

    private Parser(){}

    public static Parser getInstance(){
        if(parser==null){
            parser=new Parser();
            return parser;
        }
        return null;
    }

    public Command parse(String userInput){

        if(userInput.contains(" ")){
            String keyword=userInput.substring(0,userInput.indexOf(" "));
            String information=userInput.substring(userInput.indexOf(" ")+1);

            switch (keyword){
                case "done":
                    return parseDone(information);
                case "todo":
                    return parseTodo(information);
                case "deadline":
                    return parseDeadline(information);
                case "event":
                    return parseEvent(information);
                default:
                    return null;
            }
        }else{
            switch (userInput){
                case "list":
                    return parseList();
                case "bye":
                    return parseBye();
                default:
                    return null;
            }
        }

    }


    private Command parseBye(){
        return new Command_Exit();
    }

    private Command parseList(){
        return new Command_List();
    }

    private Command parseDone(String information){
        if(isNumeric(information)){
            int index=Integer.parseInt(information)-1;
            return new Command_MarkAsDone(index);
        }
        return null;
    }

    private Command parseTodo(String information){
        return new Command_AddToDo(information);
    }

    private Command parseDeadline(String information){
        if(information.contains(" /by ")){
            String description=information.substring(0,information.indexOf(" /by "));
            String time=information.substring(information.indexOf(" /by ")+5);

            return new Command_AddDeadline(description,time);
        }
        return null;
    }

    private Command parseEvent(String information){
        if(information.contains(" /at ")){
            String description=information.substring(0,information.indexOf(" /at "));
            String time=information.substring(information.indexOf(" /at ")+5);

            return new Command_AddEvent(description,time);
        }
        return null;
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
