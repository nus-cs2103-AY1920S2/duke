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

    public void showInstructions() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Instructions: Type in Commands");
        System.out.println("Examples:");
        System.out.println("1. ~todo task~" + '\n' + "2. ~deadline task /by yyyy-MM-dd HH:mm~" + '\n' 
        + "3. ~event task /at yyyy-MM-dd HH:mm~" + '\n' + "4. ~delete taskname~" + '\n' + "5. ~done taskname~" + '\n' + "6. ~list~");
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