import java.util.ArrayList;

public class Common {

    private static Common common=null;

    private Model model;

    private Common(){
        model=Model.getInstance();
    }

    public static Common getInstance(){
        if(common==null){
            common=new Common();
            return common;
        }
        return null;
    }

    public String[] addTask(Task task){
        model.addTask(task);
        ArrayList<String> s=new ArrayList<>();
        s.add("Got it. I've added this task: :");
        s.add(""+task);
        s.add("Now you have "+model.getSize()+" tasks in the list.");
        return s.toArray(new String[0]);
    }

    public String[] printList(){
        if(model.formatList()==null){
            ArrayList<String> s=new ArrayList<>();
            s.add("The list is empty");
            return s.toArray(new String[0]);
        }
        ArrayList<String> s=model.formatList();
        s.add(0,"Here are the tasks in your list:");
        return s.toArray(new String[0]);
    }

    public String[] markTask(int index){
        model.markDone(index);
        ArrayList<String> s=new ArrayList<>();
        s.add("Nice! I've marked this task as done: :");
        s.add(""+model.getTask(index));
        return s.toArray(new String[0]);
    }



}
