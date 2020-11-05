public class Ui {
    public String runGreeting() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        return "Hello! I'm Duke" + "\n" + "What can I do for you?";
    }

    public void showLoadingError() {
        System.out.println("Error");
    }
}
