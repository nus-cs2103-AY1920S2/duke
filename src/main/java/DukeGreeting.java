package main.java;


/**Class to customize greetings/user outputs */
public class DukeGreeting {
    
public DukeGreeting(){}

    public void showWelcomeMessage() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println();
    }

    public void showGoodbyeMessage() {
        System.out.println("-------------------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println();
                System.out.println("-------------------------------------------------------------");
                System.out.println();
    }
}