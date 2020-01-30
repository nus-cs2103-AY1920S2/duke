import Task.DukeException;
import Utils.Bot;
import Utils.Ui;

/**
 * Duke Program for CS2103T 2020.
 */
public class Duke {

    private Ui ui;

    public Duke(String filepath){
        this.ui = new Ui();
    }

    public void run() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        /**
         * initialize variables
         */
        Bot bot = new Bot();
        boolean finished = false;

        ui.enter();

        while ( !finished ){
            String input = ui.sc.nextLine().toLowerCase().trim();

            try {
                Ui.printDivider();
                if( Ui.isFinished(input)  ){
                    finished = true;
                } else {
                    bot.res(input);
                    Ui.printDivider();
                }
            } catch( DukeException e ){
                System.out.println(e);
            }

        }

        ui.exit();
    }
    /**
     * The main method.
     * @param args args
     */
    public static void main(String[] args) {

        new Duke("./data/tasks.txt").run();

    }
}
