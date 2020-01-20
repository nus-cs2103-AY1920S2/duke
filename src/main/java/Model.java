import java.util.ArrayList;

public class Model {

    private static Model model=null;

    private ArrayList<String> list;

    private Model(){
        list=new ArrayList<>();
    }

    public static Model getInstance(){
        if(model==null){
            model=new Model();
            return model;
        }
        return null;
    }

    public void addList(String description){
        list.add(description);
    }

    public String[] formatList(){
        ArrayList<String> s=new ArrayList<>();

        if(list.isEmpty()){
            s.add("The list is empty.");
        }else {
            for (int i = 0; i < list.size(); i++) {
                s.add((i + 1) + ". " + list.get(i));
            }
        }
        return s.toArray(new String[0]);
    }







}
