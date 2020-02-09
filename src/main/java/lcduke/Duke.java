package lcduke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException | ParseException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        String userInput = ui.input();
        Parser newInput = new Parser(userInput);

        while(!userInput.equals("bye")){
            if(newInput.getIsProblem()) {
                Parser.isProblem = false;
            } else {
                if (userInput.equals("list")) {
                    Ui.list(this.tasks);
                } else if (userInput.equals("done 2")) {
                    Ui.done2(this.tasks);
                } else if (userInput.contains("delete")){
                    this.tasks.delete(userInput);
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        System.out.println("Cannot write file");
                    }
                } else if (userInput.contains("todo")) {
                    this.tasks.toDo(userInput);
                    TaskList.totalTasks[TaskList.totalTasksCount -1].printInit();
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        System.out.println("Cannot write file");
                    }
                } else if (userInput.contains("deadline")) {
                    this.tasks.deadline(userInput);
                    TaskList.totalTasks[TaskList.totalTasksCount -1].printInit();
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        System.out.println("Cannot write file");
                    }
                } else {
                    this.tasks.event(userInput);
                    TaskList.totalTasks[TaskList.totalTasksCount -1].printInit();
                    try {
                        storage.save(tasks);
                    } catch (IOException e) {
                        System.out.println("Cannot write file");
                    }
                }
            }
            userInput = ui.input();
            newInput = new Parser(userInput);
        }
        Ui.bye();
    }

    public static void main(String[] args) {
        new Duke("./src/main/java/savedTaskList.txt").run();
    }
}
