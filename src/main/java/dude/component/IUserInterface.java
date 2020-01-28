package dude.component;

public interface IUserInterface {
    String readCommand();

    void respond(Runnable r);

    void respond(String... responses);

    void respondParsingError(String errorMsg, String... usageMsgs);

    void speak(String str);

    void close();
}
