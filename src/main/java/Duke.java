import java.util.HashMap;
import java.util.Scanner;

/** Main class. */
public class Duke {

    private Storage storage;
    private TaskList lst;
    private Ui ui;
    private Parser parser;
    private Factory factory;
    private UiV2 uiV2;

    private HashMap<Cloud, String> hm;
    private Command globalCommand;

    /**
     * Empty Constructor for Duke class. Needed to run Launcher.
     */
    public Duke() {

    }

    /**
     * Constructor for Duke class. Ui handles console user interaction. Storage stores and loads Tasklist from
     * persistent storage. TaskList stores tasks and provide functions to maintain these tasks.
     * Parser parses input. Factory creates task objects. UiV2 handles GUI user interaction.
     *
     * @param filepath path where TaskList is stored.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        this.lst = storage.load();
        this.parser = new Parser();
        this.factory = new Factory();
        this.uiV2 = new UiV2();
        this.globalCommand = Command.DEFAULT;
        this.hm = new HashMap<Cloud, String>();
        hm.put(Cloud.TEMP, "");
        hm.put(Cloud.TEMPDESC, "");
        hm.put(Cloud.TEMPTD1, "");
    }

    /**
     * Gets global command.
     *
     * @return global command.
     */
    public Command getGlobalCommand() {
        return this.globalCommand;
    }

    /**
     * Sets global command.
     *
     * @param command command to be set.
     */
    public void setGlobalCommand(Command command) {
        this.globalCommand = command;
    }

    public static void main(String[] args) {
        new Duke("src/main/data/tasks.ser").run();
    }

    /**
     * Gets response from duke based on user input and current global command.
     *
     * @param getInput user input.
     * @param currCommand current global command.
     * @return duke response.
     */
    public String getResponse(String getInput, Command currCommand) {
        String res = "";
        if (currCommand == Command.DEFAULT) {
            try {
                Command command = parser.parse(getInput);
                switch (command) {
                case BYE:
                    res = "Bye little kid, have  fun in school!";
                    break;
                case LIST:
                    res = uiV2.sendList(lst);
                    break;
                case HELP:
                    res = uiV2.sendInstructions();
                    break;
                case ADD:
                    this.globalCommand = Command.ADD;
                    hm.put(Cloud.TEMP, getInput); //todo, deadline, event
                    res = "What do you want to add?";
                    break;
                case FIND:
                    this.globalCommand = Command.FIND;
                    res = "Enter the keyword or phrase you would like to find. :)";
                    break;
                case DONE:
                    this.globalCommand = Command.DONE;
                    res = "Enter the task number you want to mark as done.\n" + uiV2.sendList(lst) ;
                    break;
                case DELETE:
                    this.globalCommand = Command.DELETE;
                    res = "Enter the task number you want to delete.\n" + uiV2.sendList(lst);
                    break;
                default:
                    throw new DukeException("Invalid Input");
                }

            } catch (DukeException e) {
                res = uiV2.sendErrInvalidInput();
            } catch (NullPointerException e) {
                res = uiV2.sendErrInvalidInput();
            } catch (Exception e) {
                e.printStackTrace();
                res = uiV2.sendErrInvalidInput() + " unknown";
            }

        } else if (currCommand == Command.ADD){
            if (hm.get(Cloud.TEMP).equals("todo")) {
                Task task = factory.buildTodoFromCloud(getInput);
                lst.addTask(task);
                res = uiV2.sendAddTask(task, lst.getSize());
                storage.save(lst);
                this.setGlobalCommand(Command.DEFAULT);
            } else if (hm.get(Cloud.TEMP).equals("deadline")) {
                hm.put(Cloud.TEMPDESC, getInput);
                res = "When is the deadline? \nFormat: {dd/mm/yyyy hhmm}";
                this.setGlobalCommand(Command.CREATEDEADLINE1);
            } else if (hm.get(Cloud.TEMP).equals("event")) {
                hm.put(Cloud.TEMPDESC, getInput);
                res = "When does the event start? \nFormat: {dd/mm/yyyy hhmm}";
                this.setGlobalCommand(Command.CREATEEVENT1);
            }

        } else if (currCommand == Command.CREATEDEADLINE1){
            Task task = factory.buildDeadlineFromCloud(hm.get(Cloud.TEMPDESC), getInput);
            lst.addTask(task);
            res = uiV2.sendAddTask(task, lst.getSize());
            storage.save(lst);
            this.setGlobalCommand(Command.DEFAULT);

            hm.put(Cloud.TEMPDESC, "");

        } else if (currCommand == Command.CREATEEVENT1){
            hm.put(Cloud.TEMPTD1, getInput);
            res = "When does the event end? \nFormat: {dd/mm/yyyy hhmm}";
            this.setGlobalCommand(Command.CREATEEVENT2);

        } else if (currCommand == Command.CREATEEVENT2){
            Task task = factory.buildEventFromCloud(hm.get(Cloud.TEMPDESC), hm.get(Cloud.TEMPTD1), getInput);
            lst.addTask(task);
            res = uiV2.sendAddTask(task, lst.getSize());
            storage.save(lst);
            this.setGlobalCommand(Command.DEFAULT);

            hm.put(Cloud.TEMPDESC, "");
            hm.put(Cloud.TEMPTD1, "");

        } else if (currCommand == Command.FIND) {
            TaskList tempLst = lst.findMatchingTasks(getInput);
            res = uiV2.sendList(tempLst);
            this.setGlobalCommand(Command.DEFAULT);

        } else if (currCommand == Command.DONE) {
            lst.doneTask(getInput);
            res = uiV2.sendDoneTask(lst.getTaskFromString(getInput));
            storage.save(lst);
            this.setGlobalCommand(Command.DEFAULT);

        } else if (currCommand == Command.DELETE) {
            res = uiV2.sendDeleteTask(lst.getTaskFromString(getInput),lst.getSize() - 1);
            lst.deleteTask(getInput);
            storage.save(lst);
            this.setGlobalCommand(Command.DEFAULT);

        } else {
            res = "other command";
            this.setGlobalCommand(Command.DEFAULT);
        }

        return res;

    }

    /**
     * Runs console duke bot.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.showGreeting();

        String getInput = null;
        getInput = sc.next();

        boolean isRunning = true;

        while (isRunning) {
            try {
                Command command = parser.parse(getInput);
                switch (command) {
                    case BYE:
                        ui.showBye();
                        isRunning = false;
                        break;
                    case LIST:
                        ui.showList(lst);
                        break;
                    case FIND:
                        String toFind = sc.nextLine();
                        TaskList tempLst = lst.findMatchingTasks(toFind);
                        ui.showList(tempLst);
                        break;
                    case ADD:
                        String line = sc.nextLine();
                        Task task = factory.buildTask(getInput, line);
                        lst.addTask(task);
                        ui.showAddTask(task, lst.getSize());
                        storage.save(lst);
                        break;
                    case DONE:
                        String getDoneString = sc.next();
                        lst.doneTask(getDoneString);
                        ui.showDoneTask(lst.getTaskFromString(getDoneString));
                        storage.save(lst);
                        break;
                    case DELETE:
                        String getDeleteString = sc.next();
                        ui.showDeleteTask(lst.getTaskFromString(getDeleteString),lst.getSize() - 1);
                        lst.deleteTask(getDeleteString);
                        storage.save(lst);
                        break;
                    default:
                        throw new DukeException("Invalid Input");
                }
            } catch (DukeException e) {
                ui.showErrInvalidInput();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (isRunning) {
                getInput = sc.next();
            }
        }
    }

}
