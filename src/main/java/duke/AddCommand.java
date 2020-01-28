package duke;

public class AddCommand extends Command {

    AddCommand(String input){
        super(input);
    }

    //throw the length <= 1 error in the parser
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] strArr = input.split(" ");
        String type = strArr[0];
        switch (type) {
            case "deadline":
                Deadline d = new Deadline(input);
                tasks.add(d);
                ui.printWithFormat(d.toString(), "task", tasks);
                storage.saveData(tasks);
                break;
            case "event":
                Event e = new Event(input);
                tasks.add(e);
                ui.printWithFormat(e.toString(), "task", tasks);
                storage.saveData(tasks);
                break;
            case "todo":
                Todo td = new Todo(input);
                tasks.add(td);
                ui.printWithFormat(td.toString(), "task", tasks);
                storage.saveData(tasks);
                break;
                
        }
      
    }


}
