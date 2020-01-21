public class WelcomeGoodbye {

    private String horizontalLine = "____________________________________________________________\n";

    public void welcome() {
        print(horizontalLine + "Hello, Duke here! :D \n"
                + "I'm feeling good and ready to go! What can I do for you?\n"
                + horizontalLine);
    }

    public void goodbye() {
        print(horizontalLine + "Alright byeee and see ya soon!\n"
                + horizontalLine);
    }

    private void print(String s) {
        System.out.println(s);
    }

}
