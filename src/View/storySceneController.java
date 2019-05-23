package View;

import ViewModel.storySceneViewModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.Observable;
import java.util.Observer;

public class storySceneController implements IView, Observer {

    @FXML
    private storySceneViewModel viewModel;
    public TextField txtfld_width;
    public TextField txtfld_height;
    public ComboBox combo_level;
    public Button btn_play;
    public Button btn_giveUp;
    public TextArea txtarea_storyTextArea1;
    public TextArea txtarea_storyTextArea2;

    @Override
    public void update(Observable o, Object arg) {
    }

    @Override
    public void setViewModel(Object viewModel) {
        if(viewModel instanceof storySceneViewModel){
            this.viewModel = (storySceneViewModel) viewModel;
            bindProperties(viewModel);
            setRegex();
        }
    }

    private void setRegex() {
        txtfld_width.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}?")) {
                    txtfld_width.setText(oldValue);
                }
            }
        });
        txtfld_height.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,9}?")) {
                    txtfld_height.setText(oldValue);
                }
            }
        });
    }

    @Override
    public void bindProperties(Object viewModel) {
        if(viewModel instanceof storySceneViewModel) {
            ((storySceneViewModel) viewModel).mazeWidth.bind(txtfld_width.textProperty());
            ((storySceneViewModel) viewModel).mazeHeight.bind(txtfld_height.textProperty());
            ((storySceneViewModel) viewModel).mazeLevel.bind(combo_level.valueProperty());
            combo_level.getItems().addAll("Simple", "Difficult");

            txtarea_storyTextArea1.textProperty().bind(((storySceneViewModel) viewModel).storyTextArea1);
            txtarea_storyTextArea2.textProperty().bind(((storySceneViewModel) viewModel).storyTextArea2);
        }
    }

    public void ready_onClick() {
        if(isValid()) {
            int height = Integer.valueOf(txtfld_height.getText());
            int width = Integer.valueOf(txtfld_width.getText());
            String level = combo_level.getValue().toString();
            //viewModel.generateMaze(width, heigth, level);
            ConfigurationsProperties.addProp("width",String.valueOf(width));
            ConfigurationsProperties.addProp("height",String.valueOf(height));
            ConfigurationsProperties.addProp("level",level);

            //open maze scene
            Main.switchScene("GameScene.fxml", (Stage) btn_giveUp.getScene().getWindow(), 1000, 500);
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You must enter all the fields!");
            alert.show();
        }
    }

    private boolean isValid() {
        boolean isValid = true;
        if(txtfld_height.getText().isEmpty()) {
            txtfld_height.getStyleClass().add("error");
            isValid = false;
        }
        else
            txtfld_height.getStyleClass().removeAll("error");
        if(txtfld_width.getText().isEmpty()) {
            txtfld_width.getStyleClass().add("error");
            isValid = false;
        }
        else
            txtfld_width.getStyleClass().removeAll("error");
        if(combo_level.getValue() == null) {
            combo_level.getStyleClass().add("error");
            isValid = false;
        }
        else
            combo_level.getStyleClass().removeAll("error");
        return isValid;
    }

    public void giveUp_onClick(){
        //exit game
        Main.switchScene("Enterce.fxml", (Stage) btn_giveUp.getScene().getWindow(), 550, 350);

        /*try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("GameScene.fxml"));
            Parent root = (Parent) loader.load();
            GameSceneController controller = (GameSceneController) loader.getController();
            //controller.setClientThread(client)

            Scene scene = new Scene(root, 100, 100);
            Stage stage = (Stage) btn_giveUp.getScene().getWindow();
            stage.setScene(scene);
            stage.getScene().getStylesheets().add(getClass().getResource("MYViewStyle.css").toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
