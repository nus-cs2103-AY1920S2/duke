public class Add extends Command{

    void addToList(String word){
        Task ob=new Task();
        Duke.list[Duke.pos_in_list]=ob;
        ob.setTaskName(word);
        Duke.pos_in_list++;
        System.out.println("____________________________________________________________\n  added: "+word+"\n____________________________________________________________");
    }

}