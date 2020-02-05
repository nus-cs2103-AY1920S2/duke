public class Parser {
    private String userInput;

    public String parse(String userInput) {
        this.userInput = userInput;
        if (userInput.equals("list")) {                                                  //Lists out the tasks
            return "list";
        }

        // DONE
        else if (userInput.split("\\s")[0].equals("done")) {
            return "done";
        }

        // TO DO
        else if (userInput.split("\\s")[0].equals("todo")) {
            return "todo";
        }

        // DEADLINE
        else if (userInput.split("\\s")[0].equals("deadline")) {
            return "deadline";
        }

        // EVENT
        else if (userInput.split("\\s")[0].equals("event")) {
            return "event";
        }

        //DELETE
        else if (userInput.split("\\s")[0].equals("delete")) {
            return "delete";
        }
        else {
            return "error";
        }
    }


}
