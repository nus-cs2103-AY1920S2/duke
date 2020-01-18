public class Test {
    public static void main(String[] args) {
        String strArr = "done 1";
        for (String s : strArr.split(" ")) {
            System.out.println(s);
        }
        System.out.println(strArr.split(" ")[0].equals("done"));
    }
}
