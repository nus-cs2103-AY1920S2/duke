
public class Duke {

    private Ui ui;
    private Common common;
    private Parser parser;

    public Duke(){
        ui=new Ui();
        try {
            common = Common.getInstance();
            parser = Parser.getInstance();
        }catch (SingletonException e){
            ui.errorLog(e.getMessage());
        }
    }


    public void run(){
        ui.preLog();

        boolean isExit=false;

        while(!isExit){
            String userInput=ui.getInput();
            try {
                Command command = parser.parse(userInput);
                command.execute(common,ui);
                isExit=command.isExit();
            }catch (DukeException e){
                ui.errorLog(e.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
