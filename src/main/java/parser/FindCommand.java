package parser;

public class FindCommand extends Command{
    private String keyWord;

    FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    public String execute() {

        return this.taskList.findByKeyWord(keyWord);
    }
}
