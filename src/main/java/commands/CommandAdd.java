package commands;

import main.DukeProcessor;

import java.util.ArrayList;
import java.util.List;

public class CommandAdd implements DukeCommand {

    public void execute(DukeProcessor processor, String args) {
        List<String> textList = (ArrayList<String>) processor.getTextList();
        textList.add(args);

        System.out.println(String.format("added: %s", args));
    }
}
