package bot.command.instruction.concrete;

import bot.Storage;
import bot.Ui;

import bot.command.Command;

import bot.command.instruction.execute.StorageSearching;

import bot.command.instruction.parse.TextInstruction;

import bot.task.Task;

import java.util.ArrayList;

public class FindKeywordInstruction extends TextInstruction
        implements StorageSearching<Task>
{
    public FindKeywordInstruction(Command... commands) {
        super(commands);
    }

    @Override
    public void searchStore(Storage<Task> store, Ui ui, String searchTerm) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int size = store.getSize();
        for (int i = 0; i < size; i++) {
            if (store.get(i + 1).getTaskDetails().contains(searchTerm)) {
                indexes.add(i + 1);
            }
        }

        if (indexes.isEmpty()) {
            ui.showFailedToFind();
        } else {
            ui.showFoundTask();
            for (Integer id : indexes) {
                System.out.println(store.retrieve(id));
            }
        }
    }
}
