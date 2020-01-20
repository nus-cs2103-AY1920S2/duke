public class Duke {

    private static Duke duke =null;

    private Ui ui;
    private Model model;

    private Duke(){
        ui=new Ui();
        model=Model.getInstance();
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
            }else if(userInput.equals("list")){
                ui.display(model.formatList());
            }else {
                model.addList(userInput);
                ui.display("added: "+userInput);
            }
        }

    }

    public static void main(String[] args) {
        Duke duke = Duke.getInstance();
        duke.run();
    }
}
