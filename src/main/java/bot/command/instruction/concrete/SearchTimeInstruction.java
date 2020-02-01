package bot.command.instruction.concrete;

import bot.Storage;
import bot.Ui;

import bot.command.Command;

import bot.command.instruction.execute.StorageSearching;

import bot.command.instruction.parse.TextInstruction;

import bot.task.Task;
import bot.util.PrettyTime;

import java.util.ArrayList;

public class SearchTimeInstruction extends TextInstruction
        implements StorageSearching<Task>
{
    public SearchTimeInstruction(Command... commands) {
        super(commands);
    }

    @Override
    public void searchStore(Storage<Task> store, Ui ui, String date) {
        // parse the String
        PrettyTime pt = new PrettyTime(date);
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        int size = store.getSize();

        if (pt.hasTime()) {
            for (int i = 0; i < size; i++) {
                if (store.get(i).getPrettyTime().equals(pt)) {
                    indexes.add(i + 1);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (store.get(i).getPrettyTime().matchDate(pt)) {
                    indexes.add(i + 1);
                }
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
