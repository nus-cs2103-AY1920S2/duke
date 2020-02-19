package parser;

import static parser.Parser.FIND_PATTERN;

public class FindCommand extends Command{
    private String keyWord;

    FindCommand(String userInput) {
        keyWord = this.findKeyword(FIND_PATTERN, userInput);
    }

    public String execute() {
        return this.taskList.findByKeyWord(keyWord);
    }
}
