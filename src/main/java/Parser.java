public class Parser {
    String command;
    String description = "";
    public Parser(String[] userInput) {
        this.command = userInput[0];
        if (userInput.length > 1) {
            this.description = userInput[1];
        }
    }
    public String getCommand(){

        return command;
    }
    public String getDescription(){

        return description;
    }
}

