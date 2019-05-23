package View;

import ViewModel.EnterceViewModel;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;

public class EnterController implements Observer,IView {

    public CheckBox ExistPlayer;
    public Button b1;
    public RadioButton gender1;
    public RadioButton gender2;
    public RadioButton firstAvatar;
    public RadioButton secondAvatar;
    public BorderPane BorderPane;
    public ImageView image1;
    public ImageView image2;
    public TextField usertxt;
    Image Avatar1;
    Image Avatar2;
    EnterceViewModel viewModel;

    MediaPlayer mediaPlayer;



    public void b1pressed() throws IOException, ClassNotFoundException {
        Player player = new Player();
        player.setName(usertxt.getText());
        if (gender1.isSelected()) {
            player.setGender(gender1.getText());
            SetPlayer(player);
        } else if (gender2.isSelected()) {
            player.setGender(gender2.getText());
            SetPlayer(player);

        } else {
            Erormsg();
            System.out.println("null");
        }
        if (player.getImage()!=null&&player.getGender()!=""&&player.getName()!="")
        {
            File file=new File("Resources\\Users\\"+player.getName());
            if(!file.exists())
                file.mkdir();
            viewModel=new EnterceViewModel(player);
            mediaPlayer.stop();
            Main.switchScene("storyScene.fxml", (Stage) b1.getScene().getWindow(), 1000,500);
        }
    }

    private void SetPlayer(Player player) {
        if (firstAvatar.isSelected())
            player.setImage("firstAvatar");
        else if (secondAvatar.isSelected())
            player.setImage("secondAvatar");
        else
            Erormsg();
    }


    private void Erormsg() {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Please Choose Gender and Avatar");
        alert.show();

    }

    public void gender1Pressed() throws FileNotFoundException {
        SetImage(gender2, "Resources\\Image\\2.png", "Resources\\Image\\1.png");
        firstAvatar.setDisable(false);
        secondAvatar.setDisable(false);
    }

    private void SetImage(RadioButton gender, String s, String s2) throws FileNotFoundException {
        gender.setSelected(false);
        Avatar1=new Image(new FileInputStream(s));
        Avatar2=new Image(new FileInputStream(s2));
        image1.setImage(Avatar1);
        image2.setImage(Avatar2);
    }

    public void Gender2Pressed() throws FileNotFoundException {
        SetImage(gender1, "Resources\\Image\\3.png", "Resources\\Image\\4.png");
        firstAvatar.setDisable(false);
        secondAvatar.setDisable(false);
    }
    public void firstAvatarPressed(){
        secondAvatar.setSelected(false);

    }
    public void secondAvatarPressed(){
        firstAvatar.setSelected(false);

    }
    @Override
    public void update(Observable o, Object arg) {
        try {

            gender1.setSelected(viewModel.gender1.getValue());
            gender2.setSelected(viewModel.gender2.getValue());
            firstAvatar.setSelected(viewModel.firstAvatar.getValue());
            secondAvatar.setSelected(viewModel.secondAvatar.getValue());

            if(arg instanceof Player) {
                switch (((Player) arg).getGender()) {
                    case "Female":
                        Gender2Pressed();
                        gender2.setSelected(true);
                        break;
                    case "Male":
                        gender1Pressed();
                        gender1.setSelected(true);
                        break;
                }
                switch (((Player) arg).getImage()) {
                    case "firstAvatar":
                        firstAvatarPressed();
                        firstAvatar.setSelected(true);
                        break;
                    case"secondAvatar":
                        secondAvatarPressed();
                        secondAvatar.setSelected(true);
                        break;
                }
            }
            else
            if ((Player)arg==null)
                ExistPlayer.setSelected(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void checkboxact() throws IOException, ClassNotFoundException {
        if (ExistPlayer.isSelected()){
            gender1.setDisable(true);
            gender2.setDisable(true);
            firstAvatar.setDisable(true);
            secondAvatar.setDisable(true);
            viewModel.updatePlayerData();

        }
        else{
            gender1.setDisable(false);
            gender2.setDisable(false);
            firstAvatar.setDisable(false);
            secondAvatar.setDisable(false);
        }
    }


    @Override
    public void setViewModel(Object viewModel) {

        if(viewModel instanceof EnterceViewModel){
            this.viewModel=(EnterceViewModel)viewModel;
            bindProperties(viewModel);
            mediaPlayer=Main.Music("Resources/song/song2.mp3");
        }

    }
    public void tmp(){
        ExistPlayer.setSelected(false);
    }
    public void setuser(){
        usertxt.textProperty().addListener((obs, oldText, newText) -> {});
    }
    @Override
    public void bindProperties(Object viewModel) {
        if(viewModel instanceof EnterceViewModel){
            ((EnterceViewModel) viewModel).user.bind(usertxt.textProperty());
            ((EnterceViewModel) viewModel).gender1.bind(this.gender1.selectedProperty());
            ((EnterceViewModel) viewModel).gender2.bind(this.gender2.selectedProperty());
            ((EnterceViewModel) viewModel).firstAvatar.bind(this.firstAvatar.selectedProperty());
            ((EnterceViewModel) viewModel).secondAvatar.bind(this.secondAvatar.selectedProperty());

        }
    }

    public void buttenSound(){ Main.Music("Resources/song/buttenHover.wav"); }

}




