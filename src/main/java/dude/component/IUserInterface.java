package dude.component;

public interface IUserInterface {
    String readCommand();
    void respond(String ...responses);
    void respond(Runnable r);
    void respondParsingError(String errorMsg, String ...usageMsgs);
    void speak(String str);
    void close();
}
