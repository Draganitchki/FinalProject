import java.util.ArrayList;

/**Task: This is class group which hold teams that have qualified for the world cup.
 * Each groupd has a seed and a match history to keep track of who has won and lost*/
public class Group {

    private ArrayList<Team> teamsInThisGroup;
    private MatchHistory stats;


    public Group(){
        teamsInThisGroup = new ArrayList<>();
        MatchHistory stats = new MatchHistory();
        Match match = new Match();
    }

    public void add(Team t){
        teamsInThisGroup.add(t);
    }

    public void remove(String s){
        for(Team t: teamsInThisGroup){
            t.getName().equals(s);
            teamsInThisGroup.remove(t);
        }
    }

    public void remove(int i){
        teamsInThisGroup.remove(i);
    }

    public String toString(){
        String s = "These are the teams, " ;
        for (Team t: teamsInThisGroup){
            s = s+ t.toString()+ "\n";
        }
        s=s+stats.toString();

        return s;
    }

    public Team getList(int i){
        return teamsInThisGroup.get(i);
    }

    public ArrayList<Team> getTeams() {
        return teamsInThisGroup;
    }
    public void addMatchHistory(Match m){
        stats.addMatch(m);
    }

    /**Task: This method will sort a group based on their score
     *
     */
    public void sortGroup(){
        // has the smallest value
        int smallestIndex;

        // of the outer loop
        Team smallest;

        // for each index in the array list
        for (int curIndex = 0; curIndex < teamsInThisGroup.size(); curIndex++) {

            /* find the index at which the element has smallest value */
            // initialize variables
            smallest = teamsInThisGroup.get(curIndex);
            smallestIndex = curIndex;

            for (int i = curIndex + 1; i < teamsInThisGroup.size(); i++) {
                if (smallest.getPoints() > teamsInThisGroup.get(i).getPoints()) {
                    // update smallest
                    smallest = teamsInThisGroup.get(i);
                    smallestIndex = i;
                }
            }

            /* swap the value */
            // do nothing if the curIndex has the smallest value
            if (smallestIndex == curIndex)
                ;
                // swap values otherwise
            else {
                Team temp = teamsInThisGroup.get(curIndex);
                teamsInThisGroup.set(curIndex, teamsInThisGroup.get(smallestIndex));
                teamsInThisGroup.set(smallestIndex, temp);
            }

        }
    }
    }



