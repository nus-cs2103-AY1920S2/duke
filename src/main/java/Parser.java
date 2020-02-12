public class Parser {
    private String userInput;
    private String searchQuery;

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
        } else if (userInput.split("\\s")[0].equals("find")) {
            String searchQuery = "";
            for (int i = 1; i < userInput.split("\\s").length; i++) {
                if (i == 1) {
                    searchQuery += userInput.split("\\s")[i];
                } else {
                    searchQuery += " " + userInput.split("\\s")[i];
                }
            }
            this.setSearchQuery(searchQuery);
            return "find";
        } else if (userInput.split("\\s")[0].equals("priority")) {
            String searchQuery = "";
            for (int i = 1; i < userInput.split("\\s").length; i++) {
                if (i == 1) {
                    searchQuery += userInput.split("\\s")[i];
                } else {
                    searchQuery += " " + userInput.split("\\s")[i];
                }
            }
            this.setSearchQuery(searchQuery);
            return "priority";
        } else if (userInput.split("\\s")[0].equals("bye")) {
            return "bye";
        } else {
            return "error";
        }
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getSearchQuery() {
        return this.searchQuery;
    }
}
