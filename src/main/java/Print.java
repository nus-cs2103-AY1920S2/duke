public class Print extends Command {
    void printList(){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for(int i=0;i<Duke.pos_in_list;i++){
            Task ob=Duke.list.get(i);
            String tick=(ob.getDone()==0)?"[N]":"[Y]";
            System.out.println(i + 1 + ". "+ob.toString());
        }
        System.out.println("____________________________________________________________");
    }

    String printTask(int i){
        Task ob=Duke.list.get(i-1);
        return(ob.toString());
    }
}