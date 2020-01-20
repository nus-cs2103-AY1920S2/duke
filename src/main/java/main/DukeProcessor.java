package main;

public class DukeProcessor {

    private boolean isActive;

    public DukeProcessor() {
        isActive = true;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I help you with today? :)");
    }

    public void processInput(String input) {
        switch(input) {
            case "bye":
                System.out.println("Ok see you!");
                disable();
                break;
            default:
                System.out.println(input);
        }
    }

    public void disable() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }
}
