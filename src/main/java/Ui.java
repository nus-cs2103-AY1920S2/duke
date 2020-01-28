public class Ui {


    public Ui(){

    }

    public void introduction(){
        String horizontalLine = "*******************************************";
        System.out.println(horizontalLine);
        //To run using gradle go and search in Help > Delegate Run > build tools > gradle
        String logo3 = " _____    |   _"
                +                     "    _| |_   |\n"
                +      "|_____   _|  | |  |_   _|  |__\n"
                +      "|_____  |_|  |_|    |_|    |  |";

        System.out.println("" + logo3);
        System.out.println("Hello!!!!! My name is Edith. Tony Stark's Personal Assistant"+ "\n"
                + "What can I do for you?");
        System.out.println(horizontalLine);
    }
}
