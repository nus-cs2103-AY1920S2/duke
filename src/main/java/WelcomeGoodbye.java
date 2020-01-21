public class WelcomeGoodbye {

    private String horizontalLine = "____________________________________________________________";

    public void welcome() {
        printLine();
        print("Hello, Duke here! :D \n"
                + "I'm feeling good and ready to go! "
                + "What can I do for you?");
        printLine();
    }

    public void goodbye() {
        printLine();
        print("====> Alright byeee thanks for coming and see ya soon! <==== \n:D :D :D :D :D");
        printLine();
    }

    private void printLine() {
        print(horizontalLine);
    }

    private void print(String s) {
        System.out.println(s);
    }

}
