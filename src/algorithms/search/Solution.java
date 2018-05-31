package algorithms.search;
import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {
    private ArrayList<AState> path;
    private int i=0;

    /**
     * constructor for Solution
     * initialize the path property
     */
    public Solution() {
        path=new ArrayList<>();
    }

    /**
     * getter for the solution path
     * @return the array list of AState that represents the path
     */
    public ArrayList getSolutionPath(){
        return path;
    }

    /**
     * getter for length of the solution path
     * @return the length of path
     */
    public int getSolutionLength(){return path.size();}

    /**
     * add the given state to the solution path
     * @param state - the state to add
     */
    public void AddState(AState state){
        if(state != null) {
            path.add(i, state);
            i++;
        }
    }

    /**
     * remove the given state to the solution path
     * @param index - the index for the state to delete
     */
    public void RemoveState(int index){
        if(index >= 0)
            path.remove(index);
    }
}
