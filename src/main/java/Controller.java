public class Controller {
    private Storage storageController;

    public Controller(Storage storageController) {
        this.storageController = storageController;
    }

    public static void printTaskList() {
        System.out.println("Commands available:");
        System.out.println("1. event [description] : add a new event");
        System.out.println("2. deadline [description] by yyyy-mm-dd hhmm : add a new deadline");
        System.out.println("3. todo [description] at yyyy-mm-dd hhmm : add a new todo");
        System.out.println("4. done [index] : complete the task at given index");
        System.out.println("5. delete [index] : delete the task at given index");
        System.out.println("6. bye : say goodbye and quit");
    }

    public boolean execute(Command command) {
        return command.execute(storageController, storageController.generateTaskList());
    }
}
