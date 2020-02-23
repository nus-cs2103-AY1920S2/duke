public class Ui {

    public Ui(){
    }

    /**
     * This method return the welcome message.
     * @return String This is the welcome message.
     */
    public String welcomeMessage() {
        return "Hi, welcome to your very own Personal Schedule! \nHow can I help you?";
    }

    /**
     * This method return the Bye Message.
     * @return String This is the bye message.
     */
    public String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * This method forms the Delete action message.
     * @param action This is the action to be deleted.
     * @param amt This is the amount of element in the storage.
     * @return String This is the Message for the Delete action.
     */
    public String deleteMessage(String action, int amt) {
        String output = String.format("\nNow you have %d tasks in the list.\n", amt);
        return "Noted. I've removed this task: \n" + action + output;
    }

    /**
     * This method forms the message for the Done action.
     * @param action This is the action that has been completed.
     * @return This is the message for the Done action.
     */
    public String doneMessage(String action) {
        return "Nice! I've marked this task as done:\n" + action;
    }
}
