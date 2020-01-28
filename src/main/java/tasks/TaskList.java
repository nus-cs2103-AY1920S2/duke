package tasks;

import ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> list;
    private Ui ui;

    public TaskList() {
        this.list = new ArrayList<>();
        this.ui = new Ui();
    }

    public int size() {
        return this.list.size();
    }

    public void add(Task t) {
        this.list.add(t);
        ui.printNewTask(t, this.size());
    }

    public void save(Task t) {
        this.list.add(t);
    }

    public void delete(int index) {
        Task t = this.list.get(index);
        this.list.remove(index);
        ui.printDelete(t, this.size());
    }

    public void markAsDone(int index) {
        Task t = this.list.get(index);
        t.markAsDone();
        ui.printDone(t);
    }

    public String toSaveFormat() {
        String text = "";
        for (Task t : this.list) {
            text += t.toSaveFormat() + "\n";
        }
        return text;
    }

    public void searchDateTask(LocalDate date) {
        ArrayList<Task> dateTasks = new ArrayList<>();

        for (Task t : this.list) {
            if (t instanceof DateTask) {
                if (date.equals(((DateTask) t).getDate())) {
                    dateTasks.add(t);
                }
            }
        }
        ui.printList(dateTasks);
    }

    public void print() {
        ui.printList(this.list);
    }

    @Override
    public String toString() {
        String s = "";
        for (Task t : this.list) {
            s += t.toString() + "\n";
        }
        return s;
    }
}
