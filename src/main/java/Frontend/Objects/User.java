package Frontend.Objects;

import Frontend.Constants.Config;
import javafx.scene.image.Image;

public class User {

    private String text;
    private Image img;

    public User(String imgPath){

        this.img = new Image(this.getClass().getResourceAsStream( imgPath ));
        this.text = Config.USER_EMPTY_TEXT;

    }

    public User(){
        this.text = Config.USER_EMPTY_TEXT;
    }

    public void addText( String text ){
        this.text = text;
    }

    public void clearText(){
        this.text = Config.USER_EMPTY_TEXT;
    }

    public String getText(){
        return this.text;
    }

    public Image getImage(){
        return this.img;
    }

}
