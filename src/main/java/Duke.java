import java.time.format.DateTimeParseException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try{
            tasks = new TaskList(storage.loadFromSave());
        } catch(UnableToLoadException e){
            ui.showDukeError(e);
            storage.retryLocation(ui.getInput());
        }
    }

    public void run(){
        // initialise scanner
        ui.sayHi();

        // reply to input
        String input = ui.getCommand();
        Command command = parser.parse(input);

        while (command != Command.BYE) {
            String[] inputArr = input.split(" ");
            
            try {
                switch (command) {
                case LIST:
                    ui.reply(tasks.list(inputArr));
                    break;
                case DONE:
                    ui.reply(tasks.checkDone(inputArr));
                    storage.saveToSave(tasks);
                    break;
                case DELETE:
                    ui.reply(tasks.removeTask(inputArr));
                    storage.saveToSave(tasks);
                    break;
                case CREATETODO:
                    ui.reply(tasks.addTodo(inputArr));
                    storage.saveToSave(tasks);
                    break;
                case CREATEEVENT:
                    ui.reply(tasks.addEvent(inputArr));
                    storage.saveToSave(tasks);
                    break;
                case CREATEDEADLINE:
                    ui.reply(tasks.addDeadline(inputArr));
                    storage.saveToSave(tasks);
                    break;
                default:
                    throw new UnknownCommandException();
                }
            } catch (DukeException e) {
                ui.showDukeError(e);
            } catch (IOException e) {
                ui.showIOError();
            } catch (DateTimeParseException e) {
                ui.showDateTimeError();
            }
            // next input
            input = ui.getCommand();
            command = parser.parse(input);
        }

        ui.goodBye();
    }
    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir")).run();       
    }
}
