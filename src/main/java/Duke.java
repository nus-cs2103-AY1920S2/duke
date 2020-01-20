public class Duke {

    private static Duke duke =null;

    private Ui ui;

    private Duke(){
        ui=new Ui();
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


        while(true){
            String userInput=ui.getInput();
            if(userInput.equals("bye")){
                ui.endLog();
                break;
            }
            ui.display(userInput);
        }

    }

    public static void main(String[] args) {
        Duke duke = Duke.getInstance();
        duke.run();
    }
}
