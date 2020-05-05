package hakunamatata.command;

import hakunamatata.HakunaMatataException;
import hakunamatata.Storage;
import hakunamatata.Ui;
import hakunamatata.task.TaskList;

public class SpecialCommand extends Command {

    public static String[] specialTerm = {"爬", "爱你", "love you", "love u", "加油", "Fighting", "憨批", "嘤"};
    private String userInput;
    private String reply;

    public SpecialCommand(String userInput) {
        this.userInput = userInput;
        this.reply = "";
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws HakunaMatataException {
        switch (userInput) {
        case "爬":
        case "走开":
            this.reply = "(⑉꒦^꒦⑉) 嘤";
            break;
        case "爱你":
        case "love you":
        case "love u":
            this.reply = "ฅ՞•ﻌ•՞ฅ ♡ Love Ya !";
            break;
        case "加油":
        case "Fighting":
            this.reply = "( ง⁼̴̀^⁼̴́)ง⁼³₌₃ Fighting ! !";
            break;
        case "憨批":
            this.reply = "( ˙⌓˙ ) 爬";
            break;
        case "嘤":
            this.reply = "(｡•◡•｡) 摸～";
            break;
        default:
        }
        return this.reply;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
