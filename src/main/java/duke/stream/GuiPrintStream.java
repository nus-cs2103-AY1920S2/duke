package duke.stream;

import duke.gui.control.MainWindow;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

public class GuiPrintStream extends PrintStream {

    protected static Consumer<String> mainChatWindowCallback;

    public GuiPrintStream(OutputStream out) {
        super(out);
    }

    @Override
    public void println(String x) {
        super.println(x);
        mainChatWindowCallback.accept(x);
    }

    public static void setMainChatWindowCallback(Consumer<String> newCallback) {
        mainChatWindowCallback = newCallback;
    }
}
