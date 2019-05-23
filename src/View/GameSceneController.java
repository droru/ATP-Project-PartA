package View;

import ViewModel.EnterceViewModel;
import ViewModel.GameSceneViewModel;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.skin.LabeledSkinBase;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class GameSceneController implements IView, Observer {
    private GameSceneViewModel viewModel;
    private Timer timer;
    private int minute;
    private int second;

    private Timeline timeline;
    private Duration time = Duration.ZERO, splitTime = Duration.ZERO;
    private DoubleProperty timeSeconds = new SimpleDoubleProperty(), splitTimeSeconds = new SimpleDoubleProperty();

    @FXML
    public MazeDisplayer mazeDisplayer;
    public Label lbl_userName;
    public ImageView img_avatar;
    public Label lbl_time;
    public Label lbl_row;
    public Label lbl_column;

    @Override
    public void setViewModel(Object viewModel) {
        if(viewModel instanceof GameSceneViewModel) {
            this.viewModel = (GameSceneViewModel) viewModel;
            bindProperties(viewModel);

            //setTimer();
        }
    }

    public void setTimer() {
        /*timer = new Timer();
        Date start = Calendar.getInstance().getTime();
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        Date now = Calendar.getInstance().getTime();
                        long seconds = ChronoUnit.SECONDS.between(start, now);
                        long minutes = ChronoUnit.MINUTES.between(start, now);
                        lbl_time.setText(String.format("%d min, %02d sec", this., startTimeSec));
                    }
                }, 0, 1000);*/
        /*Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            Calendar cal = Calendar.getInstance();
            second = cal.get(Calendar.SECOND);
            minute = cal.get(Calendar.MINUTE);
            //System.out.println(hour + ":" + (minute) + ":" + second);
            lbl_time.setText(minute + ":" + second);
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();*/
        /*timeline = new Timeline(
                new KeyFrame(Duration.millis(100),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                Duration duration = ((KeyFrame)t.getSource()).getTime();
                                time = time.add(duration);
                                splitTime = splitTime.add(duration);
                                timeSeconds.set(time.toSeconds());
                                splitTimeSeconds.set(splitTime.toSeconds());
                                lbl_time.setText(String.format("%d min, %02d sec", time.toMinutes(), time.toSeconds()));
                            }
                        })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();*/
    }

    @Override
    public void bindProperties(Object viewModel) {
        if(viewModel instanceof GameSceneViewModel) {
            lbl_userName.textProperty().bind(((GameSceneViewModel) viewModel).userName);
            img_avatar.imageProperty().bind(((GameSceneViewModel) viewModel).img_avatar);
            lbl_row.textProperty().bind(((GameSceneViewModel) viewModel).lbl_row);
            lbl_column.textProperty().bind(((GameSceneViewModel) viewModel).lbl_col);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o == viewModel){
            displayMaze(viewModel.getMaze());
        }
    }

    public void displayMaze(Maze maze){
        try {
            mazeDisplayer.setMaze(maze, viewModel);
            mazeDisplayer.setCharacterPosition(Integer.parseInt(lbl_row.getText()), Integer.parseInt(lbl_column.getText()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void KeyPressed(KeyEvent keyEvent) {
        System.out.println("key pressed");
        viewModel.moveCharacter(keyEvent.getCode());
        keyEvent.consume();
    }


    //menu controller
    private MenuController menuController = new MenuController();
    File savedGame;
    Map<String, Pair<Maze,Position>> userGames=new HashMap<>();


    public MenuBar m1;
    public MenuItem New;
    public MenuItem Load;
    public MenuItem Save;
    public MenuItem Properties;

    public Menu About;
    public Menu Exit;
    public Menu Help;





    public void clickNew() {
        Main.switchScene("Enterce.fxml",(Stage) m1.getScene().getWindow(),550,350);
        //menuController.clickNew(((Stage)m1.getScene().getWindow()));
    }

    public void clickAbout() throws IOException {
        Stage about=new Stage();
        about.setTitle("About");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("About.fxml").openStream());
        Scene scene = new Scene(root, 550, 400);
        scene.getStylesheets().add(getClass().getResource("MYViewStyle.css").toExternalForm());
        about.setScene(scene);
        about.show();
    }
    public void clickLoad() throws IOException, ClassNotFoundException {
        Player p= EnterceViewModel.getPlayer();
        savedGame = new File("Resources\\Users\\" + p.getName() + "\\savedGame.list");
        String name=openLoadDialog();
        Main.loadUser(savedGame,userGames);
        if(name.compareTo("")!=0)
        {
            Pair pair=userGames.get((String)name);
            //mazeDisplayer.setMaze((Maze)pair.getKey());
            //mazeDisplayer.redraw();
        }
    }


    public void clickSave() throws IOException, ClassNotFoundException {
        //בדיקה עבור מבוכות וקובץ קיים
        Player p = EnterceViewModel.getPlayer();
        String name = openSaveDialog();
        savedGame = new File("Resources\\Users\\" + p.getName() + "\\savedGame.list");
        if (savedGame.exists()) {
            Main.loadUser(savedGame, userGames);
            while (userGames.containsKey(name)) {
                if (userGames.containsKey(name)) {
                    Errormsg();
                    name = openSaveDialog();
                }
            }
            userGames.put(name, new Pair<Maze, Position>(viewModel.getMaze(), new Position(0, 0)));

        }
        else
            userGames.put(name, new Pair<Maze, Position>(viewModel.getMaze(), new Position(0, 0)));

        Main.writetodisk(savedGame, userGames);

    }

    private void Errormsg() {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setContentText("name already exist \nPlease Choose Another name");
        alert.showAndWait();

    }

    public String openLoadDialog() throws IOException, ClassNotFoundException {
        if (savedGame.exists()) {
            Main.loadUser(savedGame, userGames);
            ArrayList <String> arrayData=new ArrayList<>();

            Iterator it = userGames.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                arrayData.add((String) pair.getKey());
                it.remove(); // avoids a ConcurrentModificationException
            }


            List<String> dialogData;
            dialogData =arrayData;
            ChoiceDialog dialog = new ChoiceDialog(dialogData.get(0), dialogData);
            dialog.setTitle("Load Game");
            dialog.setHeaderText("Select the game you want to load");
            Optional <String>result = dialog.showAndWait();
            return result.get();
        }
        return "";
    }

    public String openSaveDialog(){
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("save game");
        dialog.setHeaderText("Type name of file to save\n" +
                "press Okay (or click title bar 'X' for cancel).");
        dialog.setResizable(true);
        Label label2 = new Label("name:");
        TextField text1 = new TextField();
        GridPane grid = new GridPane();
        grid.add(text1, 2, 1);
        grid.add(label2, 1, 1);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.showAndWait();
        return text1.getText();
    }
}
