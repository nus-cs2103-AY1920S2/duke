import java.util.ArrayList;

public abstract class Command {
    public abstract boolean execute(Storage storageController, ArrayList<Task> storage);
}

class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean execute(Storage storageController, ArrayList<Task> storage) {
        try {
            storage.add(task);
            int storageSize = storage.size();
            Ui.printAdd(task.toString(), storageSize);
            storageController.writeTask(storage);
        } catch (Exception e) {
            Ui.printError(e);
        }
        return false;
    }
}

class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int i) {
        this.index = i;
    }

    @Override
    public boolean execute(Storage storageController, ArrayList<Task> storage) {
        try {
            int storageSize = storage.size();
            Ui.printDel(storage.get(index).toString(), storageSize - 1);
            storage.remove(index);
            storageController.writeTask(storage);
        } catch (Exception e) {
            Ui.printError(e);
        }
        return false;
    }
}

class DoneCommand extends Command {
    private int index;

    public DoneCommand(int i) {
        this.index = i;
    }

    @Override
    public boolean execute(Storage storageController, ArrayList<Task> storage) {
        try {
            int storageSize = storage.size();
            storage.get(index).setDone();
            Ui.printDone(storage.get(index).toString(), storageSize);
            storageController.writeTask(storage);
        } catch (Exception e) {
            Ui.printError(e);
        }
        return false;
    }
}

class ListCommand extends Command {
    private static final String type = "list";

    public String getType() {
        return type;
    }

    @Override
    public boolean execute(Storage storageController, ArrayList<Task> storage) {
        try {
            for (int i = 0; i < storage.size(); i++) {
                System.out.printf("%d -%s\n", i + 1, storage.get(i));
            }
        } catch (Exception e) {
            Ui.printError(e);
        }
        return false;
    }
}

class ExitCommand extends Command {
    private static final String type = "exit";


    @Override
    public boolean execute(Storage storageController, ArrayList<Task> storage) {
        Ui.goodbye();
        return true;
    }
}

