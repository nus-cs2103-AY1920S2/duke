import task.Task;

import java.util.List;

public class ReplyFormat {
    protected String indentationInFront;
    protected String message;
    protected String outline = "____________________________________________________________";
    protected int numOfIndentInFront;

    /**
     * Initialise the reply message format
     */
    public ReplyFormat() {
        message = "";
        numOfIndentInFront = 0;
        indentationInFront = "";
    }

    /**
     * Adding one sentence to the reply
     * @param sentence message required to add
     * @param numOfIndent number of additional indent required to add
     */
    public void addSentence(String sentence, int numOfIndent) {
        message = message + indentationInFront + setAdditionalIndent(numOfIndent) + sentence + '\n';
    }

    /**
     * Change both outlines at top and bottom
     * @param outline the outline where wrap the message.
     */
    public void changeOutline(String outline) {
        this.outline = outline;
    }

    /**
     * Adding outline with indentation
     * @return the outline with indentation
     */
    public String addOutlineWithIndentation() {
        return indentationInFront + outline + '\n';
    }

    /**
     * Adding paragraph to the reply
     * @param paragraph paragraph to add
     * @param numOfIndent number of additional spaces required in front
     */
    public void addParagraph(String paragraph, int numOfIndent) {
        String[] sentences = paragraph.split("\n");
        for (String sentence : sentences) {
            addSentence(sentence, numOfIndent);
        }
    }

    /**
     * Adding empty line in between
     */
    public void addEmptyLine() {
        addSentence("", 0);
    }

    /**
     * Adding list of data to the reply
     * @param listing listing to display
     */
    public void addList(List<Task> listing) {
        int count = 1;
        for (Task task : listing) {
            addSentence(count + "." + task.toString(), 1);
            count += 1;
        }
    }

    /**
     * Add additional indent required
     * @param numOfIndent number of indent required to add
     * @return the amount of spaces
     */
    public String setAdditionalIndent(int numOfIndent) {
        String indent = "";
        for (int i = 0; i < numOfIndent; i++) {
            indent = indent.concat(" ");
        }
        return indent;
    }

    /**
     * Set the amount of indentation in front of message
     * @param numOfIndent number of indentation required
     */
    public void setIndentationInFront(int numOfIndent) {
        numOfIndentInFront = numOfIndent;
        indentationInFront = "";
        for (int i = 0; i < numOfIndentInFront; i++) {
            indentationInFront = indentationInFront.concat(" ");
        }
    }

    /**
     * Clear message
     */
    public void clearMessage() {
        message = "";
    }

    /**
     * Reply message
     * @return return outline with message
     */
    public String replyMessage() {
        return addOutlineWithIndentation() + message + addOutlineWithIndentation();
    }
}
