import java.lang.String;

public class Parser {

    String[] commandArr;
    String command;
    String date;

    public Parser() {

    }

    public String[] parseUserInput(String input) {

        String[] outputArr = new String[3];

       if(input.equals("list") || input.equals("bye")) {

           outputArr[0] = input;
           outputArr[1] = "";
           outputArr[2] = "";

           return outputArr;

       } else if (input.startsWith("done") || input.startsWith("delete")) {

         String[] splitInput = input.split(" ");

           if( splitInput.length < 2) {

               outputArr[0] = "MissingTaskNumber";
               outputArr[1] = "";
               outputArr[2] = "";

               return outputArr;

           } else {

               return splitInput;

           }

       }

        int index = input.indexOf("/");
        int whiteSpaceIndex = input.indexOf(" ");


        if(whiteSpaceIndex == -1) {

            outputArr[0] = input;
            outputArr[1] = "EmptyDescription";
            outputArr[2] = "";

        } else if (input.startsWith("todo")) {

            outputArr[0] = input.substring(0,whiteSpaceIndex);
            outputArr[1] = input.substring(whiteSpaceIndex+ 1);
            outputArr[2] = "";

        } else if (index == -1) {

            outputArr[0] = input.substring(0,whiteSpaceIndex);
            outputArr[1] = "EmptyDate";
            outputArr[2] = "";

        } else {

            outputArr[0] = input.substring(0,whiteSpaceIndex);
            outputArr[1] = input.substring(whiteSpaceIndex + 1, index);
            outputArr[2] = input.substring(index + 4);

        }
        return outputArr;
    }


}
