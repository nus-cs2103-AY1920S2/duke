public class Boxer {
    public void greet() {
        String greeting = "Hey there, Red. Anything I can do for you?";
        print(greeting);
    }

    public void echo(String toEcho) {
        print(toEcho);
    }

    public void exit() {
        String farewell = "Guess that's enough for now. I've got your back, so you keep me close.";
        print(farewell);
    }

    public void print(String toPrint) {
        System.out.println(toPrint);
    }
}
