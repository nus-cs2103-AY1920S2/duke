package Frontend.Objects;

import javafx.scene.image.Image;

public class User {

    private String text;
    private Image img;

    public User(String imgPath){

        this.img = new Image(this.getClass().getResourceAsStream( imgPath ));
        this.text = "";

    }

    public User(){
        this.text = "";
    }

    public void addText( String text ){
        this.text = text;
    }

    public void addImg( String imgPath ){
        this.img = new Image(this.getClass().getResourceAsStream(imgPath));
    }

    public void clearText(){
        this.text = "";
    }

    public String getText(){
        return this.text;
    }

    public Image getImage(){
        return this.img;
    }

}
