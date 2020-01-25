package duke.ui;

public class TextUi implements Ui {
    private String lineBreak = "===========================================================\n";

    public void showReply(String reply) {
        System.out.print(formatReply(reply));
    }

    public void showError(String error) {
        System.out.print(formatReply(error));
    }

    public void showGreeting() {
        StringBuilder sb = new StringBuilder();
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        sb.append(logo);
        sb.append("\n");
        sb.append(formatReply("Hello! I'm Duke\nGive me a moment while I locate your save file..."));
        System.out.print(sb.toString());
    }

    private String formatReply(String str) {
        String[] lines = str.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append("> ");
            sb.append(line);
            sb.append("\n");
        }
        sb.append(lineBreak);
        return sb.toString();
    }
}