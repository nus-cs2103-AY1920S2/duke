public class List{

    public List(){}

    private String[] listArray = new String[100];
    private int counter = 0;

    public void setListArray(String list){
        this.listArray[counter] = list;
        System.out.println("added: " + list);
        counter++;
    }

    public void printList(){
        for(int i=0; i<counter; i++){
            System.out.println(i+1 + ". " + listArray[i]);
        }
    }

}