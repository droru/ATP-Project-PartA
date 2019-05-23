package ViewModel;

import View.ConfigurationsProperties;
import View.Player;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
public class EnterceViewModel extends Observable implements Observer {

    public StringProperty user = new SimpleStringProperty();
    public BooleanProperty gender1= new SimpleBooleanProperty();
    public BooleanProperty gender2= new SimpleBooleanProperty();
    public BooleanProperty firstAvatar= new SimpleBooleanProperty();
    public BooleanProperty secondAvatar= new SimpleBooleanProperty();

    static Player player;

    public static Player getPlayer() {
        return player;
    }

    Map<String, Player> userMap = (new HashMap());
    File savedUser = new File("Resources/Users/SavedUsers.list");

    public EnterceViewModel() {}

    public EnterceViewModel(Player player) throws IOException, ClassNotFoundException {
        this.player = player;
        if (savedUser.exists()) {
            loadUser();
        }
        if (userMap.containsKey(player.getName())) {
            userMap.remove(player.getName());
            userMap.put(player.getName(), player);
            //ConfigurationsProperties.addProp(player.getName(),);
        }
        else {
            System.out.println("non");
            userMap.put(player.getName(), player);
        }
        writetodisk();
        System.out.println("nextScene");

    }

    private void writetodisk() throws IOException {
        if (this.savedUser.exists()) {
            this.savedUser.delete();
        }
        FileOutputStream fout=new FileOutputStream(savedUser);
        ObjectOutputStream oss=new ObjectOutputStream(fout);
        oss.writeObject(userMap);
        fout.close();
        oss.close();

    }

    private void loadUser() throws IOException, ClassNotFoundException {
        this.userMap.clear();
        FileReader userRead = new FileReader(this.savedUser);
        userRead.ready();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.savedUser));
        Object readUser = ois.readObject();
        if (readUser != null && readUser instanceof HashMap) {
            this.userMap.putAll((HashMap)readUser);
        }
        ois.close();
        userRead.close();
    }



    @Override
    public void update(java.util.Observable o, Object arg) {

    }

    public void updatePlayerData() throws IOException, ClassNotFoundException {
        loadUser();
        Player player= userMap.get(user.get());
        if(player!=null) {
            setChanged();
            notifyObservers(player);
        }
        else {
            Erormsg();
            setChanged();
            notifyObservers(player);
        }

    }
    private void Erormsg() {
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Player not exists");
        alert.show();

    }
}
