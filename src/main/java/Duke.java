public class Duke {

    private static Duke duke =null;

    private Ui ui;
    private Common common;
    private Parser parser;

    private Duke(){
        ui=new Ui();
        common=Common.getInstance();
        parser=Parser.getInstance();
    }

    public static Duke getInstance(){
        if(duke ==null){
            duke =new Duke();
            return duke;
        }
        return null;
    }

    public void run(){
        ui.preLog();

        Boolean isExit=false;

        while(!isExit){
            String userInput=ui.getInput();
            Command command=parser.parse(userInput);
            command.execute(common,ui);
            isExit=command.isExit();
        }

    }

    public static void main(String[] args) {
        Duke duke = Duke.getInstance();
        assert duke != null;
        duke.run();
    }
}
