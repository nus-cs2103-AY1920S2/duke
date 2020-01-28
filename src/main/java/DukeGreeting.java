package main.java;


/**Class to customize greetings/user outputs */
public class DukeGreeting {
    
/** Constructor
 *  */    
public DukeGreeting(){}

    /** Method to show welcome message */
    public void showWelcomeMessage() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println();
        System.out.println("-------------------------------------------------------------");
        System.out.println();
    }

    /**Methods to show Instructions on user inputs */
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

    /**Method to show goodbye message */
    public void showGoodbyeMessage() {
        System.out.println("-------------------------------------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println();
                System.out.println("-------------------------------------------------------------");
                System.out.println();
    }
}