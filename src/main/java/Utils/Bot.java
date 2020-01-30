package Utils;

import Task.TaskList;
import Task.DukeException;

import java.util.Optional;

public class Bot {

    TaskList taskList;

    public Bot(){
        try {
            this.taskList = new TaskList(Storage.loadFromFile());
        } catch ( DukeException e ){
            this.taskList = new TaskList();
        }

    }

    /**
     * switches on command and decides what is printed
     * @param req user input
     * @throws DukeException
     */
    public void res(String req) throws DukeException {


        Parser parser = new Parser(req);
        String command = parser.getCommand();

        if( command.equals("list")){
            taskList.printTasks();
        } else if (command.equals("date")){
            taskList.printTasksOn(req);
        } else {
            Optional<Integer> indexOptional = parser.getContentAsInt();

            int index = 0;

            if( indexOptional.isPresent() ){
                index = indexOptional.get();
            }

            switch( command ) {
                case "done":
                    taskList.markTask(index);
                    break;
                case "delete":
                    taskList.deleteTask(index);
                    break;
                case "find":
                    taskList.findTask(req);
                    break;
                default:
                    taskList.addTask(req);
            }
        }

    }

}









