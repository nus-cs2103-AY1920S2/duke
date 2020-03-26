package duke.duke;

public class FindCommand extends Command {

    public FindCommand(String str) {
        super(str);
    }

    String execute(TaskList lst, Storage storage, Ui ui, TasksNum tasks) {
        String word = createFind(str);
        TaskList tempTasks = new TaskList();
        for (int i = 0; i < lst.getSize(); i++) {
            //System.out.print(lst.getTask(i).getD());
            if (lst.getTask(i).getD().contains(word)) {
                //System.out.print(lst.getTask(i).getD());
                tempTasks.addTask(lst.getTask(i));
            }
        }
        return ui.findTasks(tempTasks);
    }

    String createFind(String str) {
        String phrase = "";
        String[] tempPhrase = str.split(" ");
        for (int i = 1; i < tempPhrase.length; i++) {
            phrase += (tempPhrase[i] + " ");
        }
        return phrase;
    }

}
