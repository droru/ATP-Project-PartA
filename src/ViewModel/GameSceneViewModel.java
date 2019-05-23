package ViewModel;

import Model.GameSceneModel;
import View.ConfigurationsProperties;
import View.Player;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.Observable;
import java.util.Observer;

public class GameSceneViewModel extends Observable implements Observer {

    private int width;
    private int height;
    private String level;
    private String story;

    public StringProperty userName = new SimpleStringProperty();
    public ObjectProperty<Image> img_avatar = new SimpleObjectProperty<>();
    public StringProperty lbl_row = new SimpleStringProperty("0");
    public StringProperty lbl_col = new SimpleStringProperty("0");

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public String getStory() {
        return story;
    }

    GameSceneModel model;
    public GameSceneViewModel(GameSceneModel model) {
        this.model=model;
         width= Integer.parseInt(ConfigurationsProperties.getProp("width"));
         height=Integer.parseInt(ConfigurationsProperties.getProp("height"));
         level=ConfigurationsProperties.getProp("level");
         setSideDetails();
         generateMaze(width,height,level);

    }

    private void setSideDetails() {
        Player player= EnterceViewModel.getPlayer();
        Image image;
        story=getStory(player);
        userName.setValue(player.getName());
        try {
            switch(story){
                case "superman":
                    image=new Image(new FileInputStream("Resources\\Image\\1.png"));
                    img_avatar.setValue(image);
                    break;
                case "spiderman":
                    image=new Image(new FileInputStream("Resources\\Image\\2.png"));
                    img_avatar.setValue(image);
                    break;
                case "snowwhite":
                    image=new Image(new FileInputStream("Resources\\Image\\3.png"));
                    img_avatar.setValue(image);
                    break;
                case "Alice":
                    image=new Image(new FileInputStream("Resources\\Image\\4.png"));
                    img_avatar.setValue(image);
                    break;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
            if(o==model){
                int row = model.getCharacterPositionRow();
                //lbl_row.set(row+"");
                int col = model.getCharacterPositionColumn();
                //lbl_col.set(col+"");
                setChanged();
                notifyObservers();
            }

    }
    public void generateMaze(int width, int height, String generator){
        if (generator == "Simple")
            model.generateMaze(width, height, new SimpleMazeGenerator());
        else
            model.generateMaze(width, height, new MyMazeGenerator());
    }
    public Maze getMaze(){
        return model.getMaze();
    }

    private String getStory(Player player) {
        if (player.getGender().compareTo("Male")==0&&player.getImage().compareTo("firstAvatar")==0)
            return "superman";
        else if (player.getGender().compareTo("Male")==0 &&player.getImage().compareTo("secondAvatar")==0)
            return "spiderman";
        else if (player.getGender().compareTo("Female")==0 &&player.getImage().compareTo("firstAvatar")==0)
            return "snowwhite";
        else if (player.getGender().compareTo("Female")==0 && player.getImage().compareTo("secondAvatar")==0)
            return "Alice";
        return "";
    }

    public void moveCharacter(KeyCode movement){
        model.moveCharacter(movement);
    }
}