public class Ui {
    public static void main(String[] args) {
        Duke chatbot = new Duke("Data/Duke.txt");
        Ui.loadingCompleted();
        Ui.initiateGreetings();
        chatbot.run();
    }
    public static void initiateGreetings() {
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
    }
    public static void loadingCompleted(){

        System.out.println("FINISH loading data");
    }
    public void transmitMessage(String message){

        System.out.println(message);
    }
    public void showLoadingError(DukeException err){

        err.printStackTrace();
    }
    public void initiateFareWell(){

        System.out.println("Bye. Hope to see you again soon!");
    }
}
