import java.util.Scanner;

public class Duke {

    Ui ui;
    Storage data;
    TaskList taskList;
    Scanner scanner;

    /**
     * Creates a new Duke object.
     */

    public Duke() {
        this.ui = new Ui();

        this.data = new Storage();
        this.taskList = new TaskList();

        if (data.hasPreviousData()) {
            data.loadData(taskList);
        }
    }

    /**
     * Returns a string containing the welcome message upon starting up Duke.
     * @return Welcome message to user.
     */

    public String openingMessage() {
        return this.ui.showWelcome();
    }

    /**
     * Runs duke, returning different outputs based on the user input.
     * @param input Command given by the user.
     * @return A string containing the output based on the user's command.
     */

    public String run(String input) {
        Parser command;
        Parser content;
        String output;

        try {
            this.scanner = new Scanner(input);
            command = new Parser(this.scanner.next());

            if (command.isBye()) {
                output = this.ui.showGoodbye();
                System.exit(0);
            } else if (command.isList()) {
                output = this.ui.showList(this.taskList);
            } else if (command.isDone()) {
                int index = this.scanner.nextInt() - 1;
                output = this.ui.showDone(this.taskList, index, this.data);
            } else if (command.isDelete()) {
                int index = this.scanner.nextInt() - 1;
                output = this.ui.showDelete(this.taskList, index, this.data);
            } else if (command.isFind()) {
                String keyword = this.scanner.nextLine();
                output = this.ui.showSearchResults(this.taskList, keyword);
            } else if (command.isUpdate()) {
                try {
                    int index = this.scanner.nextInt() - 1;
                    output = this.ui.showUpdate(this.taskList, index, this.scanner.next(),
                            this.scanner.nextLine().substring(1), this.data);
                } catch (DukeException exception) {
                    output = this.ui.showError(exception);
                }
            } else {
                boolean isRemainingCommands = command.getCommand().equals("todo")
                        || command.getCommand().equals("event") || command.getCommand().equals("deadline");
                assert isRemainingCommands;

                if (this.scanner.hasNext()) {
                    content = new Parser(this.scanner.nextLine(), command);
                    output = this.ui.showAddedTask(this.taskList, content, this.data);
                } else {
                    output = this.ui.showError(new DukeException("OOPS!!! The description of a "
                            + command.getCommand() + " cannot be empty."));
                }
            }
        } catch (DukeException exception) {
            output = this.ui.showError(exception);
        }

        return output;
    }
}