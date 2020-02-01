public class Duke {

    private Ui ui;
    private DukeWorker worker;

    public Duke(String storagePath) {
        ui = new Ui();
        worker = new DukeWorker(storagePath);
    }

    public void run() {
        worker.initializeWorker();
        ui.welcomeMessage();
        String request = "";
        //while (!request.split(" ")[0].toLowerCase().equals("bye")) {
        while (!ui.isExit()) {
            request = ui.requestInput().trim().toLowerCase();
            worker.handleRequest(request, ui);
            ui.displayResponse();
        }
    }

    public static void main(String[] args) {
        new Duke(Constants.DUKE_FILE_PATH).run();
    }
}
