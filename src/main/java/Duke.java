import java.util.Scanner;

public class Duke {

    Ui ui;
    Storage data;
    Tracker tracker;
    Scanner scanner;

    public Duke() {
        this.ui = new Ui();

        this.data = new Storage();
        this.tracker = new Tracker();

        if (data.hasPreviousData()) {
            data.loadData(tracker);
        }
    }

    public String openingMessage() {
        return this.ui.showWelcome();
    }

    public String run(String input) {
        Parser command;
        Parser content;
        String output;

        try {
            this.scanner = new Scanner(input);
            command = new Parser(this.scanner.next());

            if (command.isBye()) {
                output = this.ui.showGoodbye();
            } else if (command.isList()) {
                output = this.ui.showList(this.tracker);
            } else if (command.isDone()) {
                int index = this.scanner.nextInt() - 1;
                output = this.ui.showDone(this.tracker, index, this.data);
            } else if (command.isDelete()) {
                int index = this.scanner.nextInt() - 1;
                output = this.ui.showDelete(this.tracker, index, this.data);
            } else if (command.isFind()) {
                String keyword = this.scanner.nextLine();
                output = this.ui.showSearchResults(this.tracker, keyword);
            } else if (command.isUpdate()) {
                try {
                    int index = this.scanner.nextInt() - 1;
                    output = this.ui.showUpdate(this.tracker, index, this.scanner.next(),
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
                    output = this.ui.showAddedTask(this.tracker, content, this.data);
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