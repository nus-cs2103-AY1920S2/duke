public class Parser {
    private String userInput;

    public String parse(String userInput) {
        this.userInput = userInput;
        if (userInput.equals("list")) {                                                  //Lists out the tasks
            return "list";
        } else if (userInput.split("\\s")[0].equals("done")) {
            return "done";
        } else if (userInput.split("\\s")[0].equals("todo")) {
            return "todo";
        } else if (userInput.split("\\s")[0].equals("deadline")) {
            return "deadline";
        } else if (userInput.split("\\s")[0].equals("event")) {
            return "event";
        } else if (userInput.split("\\s")[0].equals("delete")) {
            return "delete";
        } else {
            return "error";
        }
    }
}
