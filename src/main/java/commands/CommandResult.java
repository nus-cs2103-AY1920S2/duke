package commands;

import ui.TextUi;

public class CommandResult {
    public final String feedbackToUser;
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        TextUi.showResult(this.feedbackToUser);
    }
}
