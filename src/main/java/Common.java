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

    public String[] addTask(String description){
        Task task=new Task(description);
        model.addTask(task);
        ArrayList<String> s=new ArrayList<>();
        s.add("added:"+task);
        return s.toArray(new String[0]);
    }

    public String[] printList(){
        if(model.formatList()==null){
            ArrayList<String> s=new ArrayList<>();
            s.add("The list is empty");
            return s.toArray(new String[0]);
        }
        return model.formatList();
    }

    public String[] markTask(int index){
        model.markDone(index);
        ArrayList<String> s=new ArrayList<>();
        s.add("Nice! I've marked this task as done: :");
        s.add(""+model.getTask(index));
        return s.toArray(new String[0]);
    }



}
