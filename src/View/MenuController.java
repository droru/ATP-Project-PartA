package View;

import javafx.stage.Stage;

import java.util.Observable;
import java.util.Observer;

public class MenuController implements Observer {



    public MenuController() { }

    public void clickNew(Object o){
        Main.switchScene("Entrece.fxml",  ((Stage)o), 550,350);

    }



    @Override
    public void update(Observable o, Object arg) {

    }
}
