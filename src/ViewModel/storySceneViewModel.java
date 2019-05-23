package ViewModel;

import Model.storySceneModel;
import View.ConfigurationsProperties;
import View.Player;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class storySceneViewModel extends Observable implements Observer {

    private storySceneModel model;
    public StringProperty mazeWidth = new SimpleStringProperty();
    public StringProperty mazeHeight = new SimpleStringProperty();
    public StringProperty mazeLevel = new SimpleStringProperty();
    public StringProperty storyTextArea1 = new ReadOnlyStringWrapper("...");
    public StringProperty storyTextArea2 = new ReadOnlyStringWrapper("...");


    public storySceneViewModel(storySceneModel model) throws IOException {
        this.model = (storySceneModel) model;
        setStory();
    }

    public storySceneViewModel() {

    }

    @Override
    public void update(Observable o, Object arg) {
        if (o==model){
            //mazeWidth.set(model.getWidth()+"");
            //mazeHeight.set(model.getHeight()+"");
            //mazeLevel.set(mazeLevel+"");
            try {
                setStory();
            } catch (IOException e) {
                e.printStackTrace();
            }

            setChanged();
            notifyObservers();
        }
    }

    private void setStory() throws IOException {
        Player player= EnterceViewModel.getPlayer();
        String story=getStory(player);
        switch (story){
            case "superman":
                storyTextArea1.setValue("Surviving the destruction of the planet Krypton, young Kal-El is sent by his father Jor-El to live among mankind on Earth\n" +
                                        "Under the parenting of Jonathan and Martha Kent, Clark Kent discovers that he was born with extraordinary powers and abilities beyond imagination\n" +
                                        "Now living in Metropolis, Clark takes up a job as a reporter for the Daily Planet and starts to fall head over heals for fellow reporter Lois Lane.\n" +
                                        " But then Clark is also using his powers to help the better of mankind as Superman, who the people of Metropolis start turning to for help\n" +
                                        "But in the shadows, criminal genius Lex Luthor launches a sinister plan to make himself known by detonating two missiles ,with only Superman to oppose him\n" +
                                        "Will Superman save us? Or will Luthor's plan succeed?");
                storyTextArea2.setValue("\n" +"Help superman find those missile and save us all" );
                break;
            case "spiderman":
                storyTextArea1.setValue("A rather odd thing has just occurred in the life of nerdy high school student Peter Parker:\n" +
                                        "after being bitten by a genetically modified spider, his body chemistry is altered mutagenically\n" +
                                        "He can now scale walls and ceilings, he has superhuman strength and super-fast reflexes, and he develops a precognitive sense that warns him of approaching danger\n" +
                                        "Adopting the name Spider-Man, Peter first uses his newfound powers to make money,\n" +
                                        "but after his uncle is murdered at the hands of a criminal Peter failed to stop, he swears to use his powers to fight the evil that killed his uncle\n" +
                                        "At the same time, scientist and businessman Norman Osborn, after exposure to an experimental nerve gas, develops an alternate personality himself:\n" +
                                        "the super-strong, psychotic Green Goblin! Peter Parker must now juggle three things in his life:\n" +
                                        "his new job at a local newspaper under a perpetually on-edge employer, his battle against the evil Green Goblin, and his fight to win the affections of beautiful classmate Mary Jane Watson,\n" +
                                         "against none other than his best friend Harry Osborn, son of Norman Osborn!\n" +
                                        "Is this challenge too much for even the Amazing Spider-Man to handle?");
                storyTextArea2.setValue("Help spider-man save Mary Jane from the green goblin");
                break;
            case "snowwhite":
                storyTextArea1.setValue("One beautiful morning Snow White and the seven dwarfs woke up early to go to hunting.\n" +
                                         "Then suddenly they heard a noise of the qween's carriage and someone called \"Snow White\".\n" +
                                         "Snow White didn't want the qween to find her because she wanted to kill her, so they all hided in the forest.\n" +
                                        "Each of them found another place to hide there.\n" +
                                        "Time passed and the voices disapeared, but Snow White got lost and now she cannot find not her friends and not her their home.\n" +
                                         " \n" +
                                        "She needs to find her freinds before the qween does.\n");
                storyTextArea2.setValue("Help snow white to find the seven dwarfs.");
                break;
            case "Alice":
                storyTextArea1.setValue("Alice just got to Wanderland after she drank potion that make her samller.\n" +
                                        "A white rabbit got by her in a very hurry. He held a watch and told to himself \"oh I am late again!, must hurry up or the qween will cut my head\".\n" +
                                        "Alice tried to talk to him but he didn't listen to her and continued to tell the same sentence again and again  \"oh I am late again!, must hurry up or the qween will cut my head\".\n" +
                                         "\n" +
                                          "Alice ran after him into the labyrinth and got lost.\n");
                    storyTextArea2.setValue("Help Alice to find the rabbit.");
                break;
        }

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



    public String getStoryTextArea1() {
        return storyTextArea1.get();
    }
    public String getStoryTextArea2() {
        return storyTextArea2.get();
    }
}
