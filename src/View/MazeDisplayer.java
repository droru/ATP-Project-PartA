package View;

import ViewModel.GameSceneViewModel;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class MazeDisplayer extends Canvas {

    private Maze maze;
    private Image wallImage;
    private Image characterImage;

    private int characterPositionRow = 0;
    private int characterPositionColumn = 0;

    public void setMaze(Maze maze, GameSceneViewModel viewModel) throws FileNotFoundException {
        this.maze = maze;
        switch (viewModel.getStory()){
            case "superman":
                wallImage=new Image(new FileInputStream("Resources\\Image\\wallSuperman.jpg"));
                break;
            case "spiderman":
                wallImage=new Image(new FileInputStream("Resources\\Image\\wallSuperman.jpg"));
                break;
            case "snowwhite":
                wallImage=new Image(new FileInputStream("Resources\\Image\\wallSuperman.jpg"));
                break;
            case "Alice":
                wallImage=new Image(new FileInputStream("Resources\\Image\\wallSuperman.jpg"));
                break;
        }
        characterImage = viewModel.img_avatar.get(); //new Image(new FileInputStream("Resources\\Image\\1.png"));//new FileInputStream(ImageFileNameCharacter.get()));

        redraw();
    }

    public void setCharacterPosition(int row, int column) throws FileNotFoundException {
        characterPositionRow = row;
        characterPositionColumn = column;
        redraw();
    }

    public int getCharacterPositionRow() {
        return characterPositionRow;
    }

    public int getCharacterPositionColumn() {
        return characterPositionColumn;
    }

    public void redraw() throws FileNotFoundException {
        if (maze != null) {
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            double cellHeight = canvasHeight / maze.getNumRows();
            double cellWidth = canvasWidth / maze.getNumColumns();

            //try {
                GraphicsContext gc = getGraphicsContext2D();
                gc.clearRect(0, 0, getWidth(), getHeight());

                //Draw Maze
                for (int i = 0; i < maze.getNumRows(); i++) {
                    for (int j = 0; j < maze.getNumColumns(); j++) {
                        if (maze.getPosition(new Position(i,j)) == 1) {
                            //gc.fillRect(i * cellHeight, j * cellWidth, cellHeight, cellWidth);
                            gc.drawImage(wallImage, i * cellHeight, j * cellWidth, cellHeight, cellWidth);
                        }
                    }
                }

                //Draw Character
                //gc.setFill(Color.RED);
                //gc.fillOval(characterPositionColumn * cellHeight, characterPositionRow * cellWidth, cellHeight, cellWidth);
                gc.drawImage(characterImage, characterPositionColumn * cellHeight, characterPositionRow * cellWidth, cellHeight, cellWidth);
            //} catch (FileNotFoundException e) {
                //e.printStackTrace();
            //}
        }
    }

    //region Properties
    private StringProperty ImageFileNameWall = new SimpleStringProperty();
    private StringProperty ImageFileNameCharacter = new SimpleStringProperty();

    public String getImageFileNameWall() {
        return ImageFileNameWall.get();
    }

    public void setImageFileNameWall(String imageFileNameWall) {
        this.ImageFileNameWall.set(imageFileNameWall);
    }

    public String getImageFileNameCharacter() {
        return ImageFileNameCharacter.get();
    }

    public void setImageFileNameCharacter(String imageFileNameCharacter) {
        this.ImageFileNameCharacter.set(imageFileNameCharacter);
    }
    //endregion

}
