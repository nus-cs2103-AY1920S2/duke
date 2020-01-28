public class Duke {

    public static void main(String[] args) {


        String horizontalLine = "*******************************************";

        FastReader fr = new FastReader();


        TaskManager manager = new TaskManager();
        Ui ui = new Ui(manager);
        ui.introduction();

        manager.loadExistingData();
        String textEntered = fr.nextLine();

        while(ui.hasEnded == false){
            ui.handleInputs(textEntered);
            if (textEntered.equals("bye")){
                break;
            }
            textEntered = fr.nextLine();
        }

        System.out.println("Data has been saved! Goodbye Mr.Stark!!!!");
        System.out.println(horizontalLine);
    }


}




