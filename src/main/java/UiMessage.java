public enum UiMessage {

    GREETING("Hello! I'm Duke",
            "What can I do for you?"),
    FAREWELL("Bye. Hope to see you again soon!");

    String[] msg;

    UiMessage(String... msg){
        this.msg=msg;
    }

    public String[] getMsg() {
        return msg;
    }
}
