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
                default:
                    return parseAdd(userInput);
            }
        }else{
            switch (userInput){
                case "list":
                    return parseList();
                case "bye":
                    return parseBye();
                default:
                    return parseAdd(userInput);
            }
        }

    }


    private Command parseBye(){
        return new CommandExit();
    }

    private Command parseList(){
        return new CommandList();
    }

    private Command parseAdd(String userInput){
        return new CommandAddTask(userInput);
    }

    private Command parseDone(String information){
        if(isNumeric(information)){
            int index=Integer.parseInt(information)-1;
            return new CommandMarkAsDone(index);
        }
        return null;
    }


    public boolean isNumeric(String strNum) {
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
