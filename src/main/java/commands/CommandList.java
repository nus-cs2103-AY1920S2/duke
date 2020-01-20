package commands;

import main.DukeProcessor;

import java.util.ArrayList;
import java.util.List;

public class CommandList implements DukeCommand {

    public void execute(DukeProcessor processor, String args) {
        List<String> textList = (ArrayList<String>)processor.getTextList();

        for(int i = 0; i < textList.size(); i ++) {
            System.out.println(String.format("%d. %s", i + 1, textList.get(i)));
        }
    }
}
