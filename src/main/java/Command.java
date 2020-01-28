import java.io.IOException;

abstract class Command {

    enum Type {
        bye         ("bye"),
        list        ("list"),
        done        ("done"),
        todo        ("todo"),
        delete      ("delete"),
    
        deadline    ("deadline"),
        deadline_by ("/by"),
    
        event       ("event"),
        event_at    ("/at");
    
        private final String command;
    
        Type(String command) {
            this.command = command;
        }
    
        String getCommand() {
            return command;
        }
    }

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;
}
