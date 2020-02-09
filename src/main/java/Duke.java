public class Duke {
    static private Ui ui;
    static private Parser parser;

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        ui.print("Hello from\n" + logo);
//        ui.print("NOTE: For all date/time input, please use the DD-MM-YYYY HH:MM format.");
//        ui.print("Hello! I'm Duke the dude.\nHow can I serve you?");
//        ui.setInput(System.in);
    }

    protected void linkResources(Ui ui, Parser parser) {
        this.ui = ui;
        this.parser = parser;
    }

    protected String getResponse(String input) {
        try {
            return parser.parseInput(input);
        } catch (Exception ex) {
            return ui.showError(ex);
        }
    }
}