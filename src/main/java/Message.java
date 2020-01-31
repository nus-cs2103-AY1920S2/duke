package packagedirectory.test;

/**
 * Instructions containing the message input by the user while making the format of the output.
 * A <code>Message</code> object corresponds to a String object.
 */

public class Message {
    public static String lines = "--------------------------------------------------\n";
    private String message;

    public Message(){
        this.message = "";
    }

    /**
     * Create a message class that will show the information entered by the user.
     * The message will be stored in the message argument.
     * @param message a String containing the information input by user.
     * @return a Message Object
     */

    public Message(String message) {
        this.message = message;
    }

    /**
     * This method helps to change the existing message in Message class.
     * @param msg input a string.
     */

    public void add(String msg) {
        this.message = msg;
    }

    /**
     * This method helps to retrieve the message in the class.
     * @return a String that contain the message.
     */

    public String getMsg() {
        return message;
    }

    /**
     * This method welcomes the user during input.
     */

    public static void welcome() {
        System.out.println(lines + "Hello Edmond! How can i help you?\n"
                + "What can I do for you?\n" + lines);
    }

    /**
     * This method provides a good bye message.
     */

    public static void end() {
        System.out.println(lines + "Bye. See you next time!\n" + lines);
    }

    @Override
    public String toString() {
        return lines + message + "\n" + lines;
    }
}