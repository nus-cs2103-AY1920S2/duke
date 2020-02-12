package bot.command.instruction.concrete;

import bot.store.Storage;
import bot.Ui;

import bot.command.Command;
import bot.command.instruction.execute.StorageSearching;
import bot.command.instruction.parse.TextInstruction;

import bot.task.Task;

import bot.util.PrettyTime;

import java.util.ArrayList;

public class SearchTimeInstruction extends TextInstruction
        implements StorageSearching<Task> {

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
            for (int i = 1; i <= size; i++) {
                if (store.get(i).getPrettyTime().equals(pt)) {
                    indexes.add(i);
                }
            }
        } else {
            for (int i = 1; i <= size; i++) {
                if (store.get(i).getPrettyTime().matchDate(pt)) {
                    indexes.add(i);
                }
            }
        }

        printTasksToUi(store, ui, indexes);
    }

    /**
     * Prints a message to the UI, given a list of
     * indexes of a task
     *
     * @param store The store which contains the Tasks
     * @param ui The UI to print to
     * @param indexes The ArrayList of Task indexes
     */
    public static void printTasksToUi(Storage<Task> store, Ui ui, ArrayList<Integer> indexes) {
        if (indexes.isEmpty()) {
            ui.showFailedToFind();
        } else {
            StringBuilder tasks = new StringBuilder();
            for (Integer id : indexes) {
                tasks.append(store.retrieve(id)).append("\n");
            }
            // remove extra new line character
            ui.showFoundTask(tasks.deleteCharAt(tasks.length() - 1).toString());
        }
    }
}
