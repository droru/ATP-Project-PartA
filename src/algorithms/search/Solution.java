package algorithms.search;
import java.util.ArrayList;

public class Solution {
    private ArrayList<AState> path;
    private int i=0;

    public Solution() {
        path=new ArrayList<>();
    }



    public ArrayList getSolutionPath(){
        return path;
    }

    public void AddState(AState state){
        path.add(i,state);
        i++;
    }

    public void RemoveState(int index){
        path.remove(index);
    }
}
