package core;

import dukexception.DukeException;
import dukexception.SingletonException;
import task.Task;

import java.util.ArrayList;

public class Common {

    private static Common common=null;

    private Model model;
    private StateHolder stateHolder=null;
    private Storage storage;

    private Common() throws SingletonException, DukeException {
        storage=Storage.getInstance();
        model = Model.getInstance();
        loadData();
    }

    public static Common getInstance() throws SingletonException, DukeException {
        if(common==null){
            common=new Common();
            return common;
        }
        throw new SingletonException(ErrorMessage.SINGLETON.ofType("common"));
    }


    // todo duplicate updateState() on many method, any way to reduce duplication?
    public String[] addTask(Task task) throws DukeException {
        model.addTask(task);
        updateState();
        ArrayList<String> s=new ArrayList<>();
        s.add("Got it. I've added this task: :");
        s.add(""+task);
        s.add("Now you have "+model.getSize()+" tasks in the list.");
        return s.toArray(new String[0]);
    }

    public String[] printList() throws DukeException {
        ArrayList<String> s=model.formatList();
        updateState();
        s.add(0,"Here are the tasks in your list:");
        return s.toArray(new String[0]);
    }

    public String[] markAsDone(int index) throws DukeException {
        model.markDone(index);
        updateState();
        ArrayList<String> s=new ArrayList<>();
        s.add("Nice! I've marked this task as done: ");
        s.add(""+model.getTask(index));
        return s.toArray(new String[0]);
    }

    public String[] deleteTask(int index) throws DukeException {
        Task task=model.getTask(index);
        model.deleteTask(index);
        updateState();
        ArrayList<String> s=new ArrayList<>();
        s.add("Noted. I've removed this task: ");
        s.add(""+task);
        s.add("Now you have "+model.getSize()+" tasks in the list.");
        return s.toArray(new String[0]);
    }

    private void updateState(){
        stateHolder.addNewState(new State(model.getTaskList()));
    }

    public void saveData() throws DukeException {
        storage.save(stateHolder);
    }

    private void loadData() throws DukeException{
        stateHolder=storage.load();
        if(stateHolder==null) {
            stateHolder = new StateHolder();
        }else{
            model.load(stateHolder.getCurrentState().getTaskList());
        }
    }

    public void reset() throws DukeException {
        model.clearData();
        storage.clearData();
    }

}
