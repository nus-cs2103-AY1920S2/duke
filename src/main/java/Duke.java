import java.util.Scanner;

public class Duke {
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
}