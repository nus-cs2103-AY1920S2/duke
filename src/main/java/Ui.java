public class Ui {
    //deals with interactions with the user

    public String horizontalLines() {
        return "__________________________________________________________________________________________________________\n";
    }

    public void dukePrint (String input) {
        System.out.print(horizontalLines());
        System.out.print(input);
        System.out.print(horizontalLines());
    }
}