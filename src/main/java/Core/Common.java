package Core;

import Exceptions.DukeException;
import Exceptions.SingletonException;
import Task.Task;

import java.util.ArrayList;

public class Common {

    private static Common common=null;

    private Model model;

    private Common() throws SingletonException {
        model = Model.getInstance();
    }

    public static Common getInstance() throws SingletonException {
        if(common==null){
            common=new Common();
            return common;
        }
        throw new SingletonException("There should be only one common.");
    }

    public String[] addTask(Task task) throws DukeException {
        model.addTask(task);
        ArrayList<String> s=new ArrayList<>();
        s.add("Got it. I've added this task: :");
        s.add(""+task);
        s.add("Now you have "+model.getSize()+" tasks in the list.");
        return s.toArray(new String[0]);
    }

    public String[] printList() throws DukeException {
        ArrayList<String> s=model.formatList();
        s.add(0,"Here are the tasks in your list:");
        return s.toArray(new String[0]);
    }

    public String[] markAsDone(int index) throws DukeException {
        model.markDone(index);
        ArrayList<String> s=new ArrayList<>();
        s.add("Nice! I've marked this task as done: ");
        s.add(""+model.getTask(index));
        return s.toArray(new String[0]);
    }

    public String[] deleteTask(int index) throws DukeException {
        Task task=model.getTask(index);
        model.deleteTask(index);
        ArrayList<String> s=new ArrayList<>();
        s.add("Noted. I've removed this task: ");
        s.add(""+task);
        s.add("Now you have "+model.getSize()+" tasks in the list.");
        return s.toArray(new String[0]);
    }


}
