import java.util.Scanner;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList taskList;

    public static void main(String[] args) {
        new Duke().run();
    }

    private Duke() {
        try {
            this.storage = new Storage("data/duke.txt");
            this.taskList = this.storage.loadTaskList();
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    private void run() {
        UI.printIntro();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                System.out.println(input);
                boolean quit = Parser.parseUserInput(input, this.storage, this.taskList);
                if (quit) break;
            } catch (DukeException e) {
                System.err.println(e.toString());
                continue;
            } catch (ArrayIndexOutOfBoundsException x) {
                Exception e = new DukeException("Please enter a valid instruction!");
                System.err.println(e.toString());
                continue;
            } catch (IOException e) {
                System.err.println(e.toString());
                continue;
            }
        }
    }

}