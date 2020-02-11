import java.util.Scanner;
import javafx.application.Application;

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
            } else {
                // InvocationTargetException
                Parser content;

                try {
                    content = new Parser(this.scanner.nextLine(), command);
                    output = this.ui.showAddedTask(this.tracker, content, this.data);
                } catch (DukeException exception) {
                    output = this.ui.showError(exception);
                }
            }
        } catch (DukeException exception) {
            output = this.ui.showError(exception);
        }

        return output;
    }

/**
    public static void main(String[] args) {
        Ui ui = new Ui();

        ui.showWelcome();

        Storage data = new Storage();
        Tracker tracker = new Tracker();
        Scanner scanner = new Scanner(System.in);
        Parser command;

        if (data.hasPreviousData()) {
            data.loadData(tracker);
        }

        while (true) {
            try {
                command = new Parser(scanner.next());

                if (command.isBye()) {
                    ui.showGoodbye();
                    break;
                } else if (command.isList()) {
                    ui.showList(tracker);
                } else if (command.isDone()) {
                    int index = scanner.nextInt() - 1;
                    ui.showDone(tracker, index);
                    data.saveData(tracker.showList());
                } else if (command.isDelete()) {
                    int index = scanner.nextInt() - 1;
                    ui.showDelete(tracker, index);
                    data.saveData(tracker.showList());
                } else if (command.isFind()) {
                    String keyword = scanner.nextLine();
                    ui.showSearchResults(tracker, keyword);
                } else {
                    Parser content;

                    try {
                        content = new Parser(scanner.nextLine(), command);
                        ui.showAddedTask(tracker, content);
                        data.saveData(tracker.showList());
                    } catch (DukeException exception) {
                        ui.showError(exception);
                    }
                }
            } catch (DukeException exception) {
                ui.showError(exception);
            }
        }
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
**/
}