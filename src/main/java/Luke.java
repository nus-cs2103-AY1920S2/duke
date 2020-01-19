public class Luke {

    private static Luke luke =null;

    private Ui ui;

    private Luke(){
        ui=new Ui();
    }

    public static Luke getInstance(){
        if(luke ==null){
            luke =new Luke();
            return luke;
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
        Luke luke = Luke.getInstance();
        luke.run();
    }
}
