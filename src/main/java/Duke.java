public class Duke {
    static private Ui ui;
    static private Parser parser;

    public static void main(String[] args) {}

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