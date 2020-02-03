package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class FindCommand extends Command {
    private static final String type = "find";
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean execute(Storage storageController, ArrayList<Task> storage) {
        try {
            AtomicInteger index = new AtomicInteger(1);
            Ui.printFindPre(keyword);
            storage.stream()
                    .filter(task -> task.getDescription().toLowerCase().contains(this.keyword.toLowerCase()))
                    .forEach(task -> System.out.printf("\t%d -%s\n", index.getAndIncrement(), task.toString()));
            Ui.printFindPost(index.get() - 1);
        } catch (Exception e) {
            Ui.printError(e);
        }
        return false;
    }
}
