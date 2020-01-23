public class Print extends Command {
    void printList(){
        System.out.println("____________________________________________________________");


        for(int i=0;i<Duke.pos_in_list;i++){
            //System.out.println(i+1+". "+Duke.list[i]);
            Task ob=Duke.list[i];
            String tick="";
            if(ob.getDone()==0) {
                tick="[N]";
            }
            else{
                tick="[Y]";
            }
            System.out.println(i + 1 + ". "+tick+" " + ob.getTaskName());
        }
        System.out.println("____________________________________________________________");
    }

    void printTask(int i){
        Task ob=Duke.list[i];
        System.out.println("[Y] " + ob.getTaskName());
    }
}