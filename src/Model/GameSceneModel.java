package Model;


import algorithms.mazeGenerators.IMazeGenerator;
import algorithms.mazeGenerators.Maze;
import javafx.scene.input.KeyCode;

import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameSceneModel extends Observable implements IModel {
    private ExecutorService threadPool = Executors.newCachedThreadPool();
    private Maze maze;
    private int characterPositionRow = 0;
    private int characterPositionColumn = 0;

    public int getCharacterPositionRow() {
        return characterPositionRow;
    }

    public int getCharacterPositionColumn() {
        return characterPositionColumn;
    }

    public void generateMaze(int width, int height, IMazeGenerator generator) {
        threadPool.execute(() -> {
            maze = generator.generate(height, width);
            maze.print();
            setChanged();
            notifyObservers();
        });
    }

    public Maze getMaze() {
        return maze;
    }

    public void moveCharacter(KeyCode movement) {
        switch (movement) {
            case UP:
                characterPositionRow--;
                break;
            case DOWN:
                characterPositionRow++;
                break;
            case RIGHT:
                characterPositionColumn++;
                break;
            case LEFT:
                characterPositionColumn--;
                break;
        }
        setChanged();
        notifyObservers();
    }
}
