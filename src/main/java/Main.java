import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws DukeException{

        Scanner sc = new Scanner(System.in);
        MyList taskList = new MyList();
        Storage storage = new Storage();
        storage.loadFile(taskList);
        Ui ui = new Ui();
        Parser parser = new Parser(ui);
        boolean isExit = false;

        ui.showStartMessage();

        while(!isExit) {

            try{
                String word = sc.nextLine();
                Command command = parser.parseCommand(word);
                command.execute(word, taskList, ui, storage);
                isExit = command.isExit();
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }

        }

        sc.close();

    }

}
