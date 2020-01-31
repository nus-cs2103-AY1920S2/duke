import java.io.IOException;

/** deals with making sense of the user command. 
 * Deconstructs a line into a String[] of arguments, with the first item being the cmd and the rest the arg. */
class Parser {
    private static final boolean SHUTDOWN = true;
    Storage storage;
    Ui ui;
    TaskList tasks;
    
    public Parser(Storage storage, Ui ui, TaskList tasks) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
    }

    public boolean parse(String line) throws IncorrectArgumentException, InvalidCommandException, IOException{
        String[] cmd = line.split(" ", 2);
        boolean isBye = false;
        switch (cmd[0].toLowerCase()) {
        case "bye":
            isBye = bye();
            break;
        case "list":
            isBye = list();
            break;
        case "done":
            isBye = done(Integer.parseInt(cmd[1]));
            break;
        case "todo":
            isBye = todo(cmd[1]);
            break;
        case "deadline":
            isBye = deadline(cmd[1]);
            break;
        case "event":
            isBye = event(cmd[1]);
            break;
        case "delete":
            isBye = delete(Integer.parseInt(cmd[1]));
            break;
        default:
            throw new InvalidCommandException("invalid command:\n  " + line + "\nplease try again");
        }
        storage.saveToFile(tasks);
        return isBye;
    }

    public boolean bye() {
        ui.out("Bye. Hope to see you again soon!");
        ui.close();
        return SHUTDOWN;
    }

    public boolean list() {
        ui.out(tasks.list());
        return !SHUTDOWN;
    }

    public boolean done(int i) {
        ui.out(tasks.done(i));
        return !SHUTDOWN;
    }

    public boolean todo(String args) throws IncorrectArgumentException {
        ui.out(tasks.addTodo(args));
        return !SHUTDOWN;
    }

    public boolean deadline(String args) throws IncorrectArgumentException {
        String[] argv = args.split(" /by ");
        ui.out(tasks.addDeadline(argv[0], argv[1]));
        return !SHUTDOWN;
    }

    public boolean event(String args) throws IncorrectArgumentException {
        String[] argv = args.split(" /at ");
        ui.out(tasks.addEvent(argv[0], argv[1]));
        return !SHUTDOWN;
    }

    public boolean delete(int i) {
        ui.out(tasks.delete(i));
        return !SHUTDOWN;
    }

}