package View;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable {
    private String name="";
    private String gender="";
    private String image="";


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(getName(), player.getName()) &&
                Objects.equals(getGender(), player.getGender()) &&
                Objects.equals(getImage(), player.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public Player() { }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", image=" + image +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Player(String name, String gender, String image) {
        this.name = name;
        this.gender = gender;
        this.image = image;
    }
}
