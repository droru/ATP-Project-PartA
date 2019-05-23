package View;

import Model.GameSceneModel;
import Model.storySceneModel;
import ViewModel.EnterceViewModel;
import ViewModel.GameSceneViewModel;
import ViewModel.storySceneViewModel;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class Main extends Application {

    private static Stage stage;
    private static FXMLLoader fxmlLoader;
    private static Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        setDefaultProperties();

        //storySceneModel model = new storySceneModel();
        EnterceViewModel viewModel = new EnterceViewModel();
        //model.addObserver(viewModel);

        primaryStage.setTitle("AD Maze!");
        fxmlLoader = new FXMLLoader();
        root = fxmlLoader.load(getClass().getResource("Enterce.fxml").openStream());
        Scene scene = new Scene(root, 550, 350);
        // for story scene! Scene scene = new Scene(root, 1000,500);
        //primaryStage.setMaxWidth(550);primaryStage.setMaxHeight(350);
        scene.getStylesheets().add(getClass().getResource("MYViewStyle.css").toExternalForm());
        primaryStage.setScene(scene);

        EnterController view = fxmlLoader.getController();
        //view.setResizeEvent(scene);
        view.setViewModel(viewModel);
        viewModel.addObserver(view);
        //--------------
        SetStageCloseEvent(primaryStage);
        primaryStage.show();

    }

    private void setDefaultProperties() {
        ConfigurationsProperties.addProp("story", "superman");//superman, spiderman, snowwhite, belle
    }

    public static void switchScene(String fxmlFile, Stage stage, int width, int height)

    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(fxmlFile));
            Parent root = (Parent) loader.load();
            loader.getController();
            //GameSceneController controller = (GameSceneController) loader.getController();
            //controller.setClientThread(client)

            Scene scene = new Scene(root, width, height);
            stage.setScene(scene);
            stage.getScene().getStylesheets().add(Main.class.getResource("MYViewStyle.css").toExternalForm());

            switch (fxmlFile){
                case "Enterce.fxml":
                    EnterceViewModel viewModel=new EnterceViewModel();
                    EnterController view=loader.getController();
                    view.setViewModel(viewModel);
                    viewModel.addObserver(view);
                    break;
                case"GameScene.fxml":
                    GameSceneModel model1 = new GameSceneModel();
                    GameSceneViewModel viewModel2=new GameSceneViewModel(model1);
                    model1.addObserver(viewModel2);
                    GameSceneController view2=loader.getController();
                    view2.setViewModel(viewModel2);
                    viewModel2.addObserver(view2);
                    break;
                case"storyScene.fxml":
                    storySceneModel model = new storySceneModel();
                    storySceneViewModel viewModel1=new storySceneViewModel(model);
                    model.addObserver(viewModel1);
                    storySceneController view1=loader.getController();
                    view1.setViewModel(viewModel1);
                    viewModel1.addObserver(view1);
                    break;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SetStageCloseEvent(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent windowEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    // ... user chose OK
                    // Close program
                } else {
                    // ... user chose CANCEL or closed the dialog
                    windowEvent.consume();
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }


    public static void loadUser(File file, Map map) throws IOException, ClassNotFoundException {
        map.clear();
        FileReader userRead = new FileReader(file);
        userRead.ready();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Object readUser = ois.readObject();
        if (readUser != null && readUser instanceof HashMap) {
            map.putAll((HashMap)readUser);
        }
        ois.close();
        userRead.close();
    }
    public static void writetodisk(File file, Map map) throws IOException {
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fout=new FileOutputStream(file);
        ObjectOutputStream oss=new ObjectOutputStream(fout);
        oss.writeObject(map);
        fout.close();
        oss.close();

    }
    public static MediaPlayer Music(String string)
    {

        Media hit = new Media(Paths.get(string).toUri().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        return mediaPlayer;
    }
}
