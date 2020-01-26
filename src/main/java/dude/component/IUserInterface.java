package dude.component;

public interface IUserInterface {
    String readCommand();
    void respond(String ...responses);
    void respond(Runnable r);
    void respondError(String errorMsg, String ...usageMsgs);
    void speak(String str);
    void close();
}
