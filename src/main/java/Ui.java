public class Ui {

    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    String welcomeMessage = "Hello from\n" + logo + "\n I am at your service\n";
    //    System.out.println("Hello from\n" + logo + "\n I am at your service\n");
    String botReplyLine = "----------------------------------------------";

    public void showWelcome() {
        System.out.println("Hello from\n" + logo + "\n I am at your service\n" + botReplyLine);
    }

    public void goodbyeMessage() {
        System.out.println(botReplyLine + "\n Duke: I'll say goodnight now \n" + botReplyLine);
    }

    public void showLine() {
        System.out.println(botReplyLine);
    }

    public void emptyList() {
        System.out.println("You currently do not have anything in your list");
        showLine();
    }

    public void doneTask() {
        showLine();
        System.out.println("Consider it done.");

    }

    public void invalidTask() {
        showLine();
        System.out.println("Invalid task number");

    }

    public void deletedTask() {
        showLine();
        System.out.println("Consider it deleted.");
    }

    public String missingTaskNumber() {
        return botReplyLine + "\n Missing task number \n" + botReplyLine;
    }

    public String incompleteCommand(String str) {
        return botReplyLine + "\n" + str + " not complete \n" + botReplyLine;
    }

    public String invalidCommand() {
        return botReplyLine + " \n Sorry I do not understand. \n" + botReplyLine;
    }

    public void addedCommand() {
        showLine();
        System.out.println("Duke: added your command. \n");
    }
    
    public void taskInList(int listSize){
        System.out.println("You have " + listSize + " task(s) in your list.");
        showLine();
    }


}
