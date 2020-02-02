import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage("../data/tasks.txt");
        TaskList taskList = new TaskList(storage.load());
        ui.start();
        Scanner sc = new Scanner(System.in);
        Boolean isRunning = true;
        while (isRunning) {
            try {
                String userInput = sc.nextLine();
                Command cmd = Parser.parseInput(userInput);
                isRunning = cmd.execute(storage, taskList, ui);
                ui.promptMsg();
            }
            catch(DukeException e) {
                ui.errorMsg(e);
                ui.promptMsg();
            }
        }
        sc.close();
    }
}