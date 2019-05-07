import java.util.ArrayList;

/**Task: This is the class group rounds where the group rounds are carried out
 *
 */
public class GroupRounds {

    private Group a;
    private Group b;
    private Group c;
    private Group d;
    private Group e;
    private Group f;
    private Group g;
    private Group h;

    private ArrayList<Group> groupList = new ArrayList<Group>();
    private ArrayList<Team> tournamentTeams;
    public GroupRounds(ArrayList<Team> finalist){
        a= new Group();
        b= new Group();
        c= new Group();
        d= new Group();
        e= new Group();
        f= new Group();
        g= new Group();
        h= new Group();

        groupList.add(a);
        groupList.add(b);
        groupList.add(c);
        groupList.add(d);
        groupList.add(e);
        groupList.add(f);
        groupList.add(g);
        groupList.add(h); 
        
        //added by zion, creates a new array list using finalist list as the base, and creates empty spots to set
        //up tree
        tournamentTeams = new ArrayList<Team>(finalist);
        while(tournamentTeams.size() < finalist.size()*2-1) {
        	tournamentTeams.add(0, new Team());
        }
        //assignGroups(finalist);//here teams are assigned to groups
    }

    public void assignGroups(ArrayList<Team> finalist){

        //How a seed is assigned to each group
        /*for(int i = 0; i < 8;i++){
            for(int j = 0; j < i; j++){
                groupList.get(j).add(finalist.get(i));
                finalist.remove(i);
            }
        } */
        //Group a
        groupList.get(0).add(finalist.get(8));
        groupList.get(0).add(finalist.get(9));
        groupList.get(0).add(finalist.get(10));

       // Group B
        groupList.get(1).add(finalist.get(11));
        groupList.get(1).add(finalist.get(12));
        groupList.get(1).add(finalist.get(13));

        //Group C
        groupList.get(2).add(finalist.get(14));
        groupList.get(2).add(finalist.get(15));
        groupList.get(2).add(finalist.get(16));

        //Group D
        groupList.get(3).add(finalist.get(17));
        groupList.get(3).add(finalist.get(18));
        groupList.get(3).add(finalist.get(19));

        //Group E
        groupList.get(4).add(finalist.get(20));
        groupList.get(4).add(finalist.get(21));
        groupList.get(4).add(finalist.get(22));

        //Group F
        groupList.get(5).add(finalist.get(23));
        groupList.get(5).add(finalist.get(24));
        groupList.get(5).add(finalist.get(25));


        //Group G
        groupList.get(6).add(finalist.get(26));
        groupList.get(6).add(finalist.get(27));
        groupList.get(6).add(finalist.get(28));


        //Group H
        groupList.get(7).add(finalist.get(29));
        groupList.get(7).add(finalist.get(30));
        groupList.get(7).add(finalist.get(31));


        }
        Match groupMatch ;
    // simulate group round takes the list of groups, makes a league for each group an runs the specific group sim so each team play once.
    // the top two players get added to the Finalist list of teams (final 16)
    public void simulateGroupRound(){

        //This simulates each group playing round robin
        for (Group g:groupList) {
            for (int i = 0; i < g.getTeams().size(); i++) {
            	for(int k = g.getTeams().size()-1; k < i; k--) {
            		groupMatch = new Match(g.getList(i), g.getList(k));
                    groupMatch.playMatch();
                    g.addMatchHistory(groupMatch);
            	}
                
            }
        }
        //Sorting each team in each group based on points
        for(Group g: groupList){
            g.sortGroup();
        }

        //This loop then removes the last two teams from the list
        for(Group g: groupList){
            g.remove(g.getTeams().size());
            g.remove(g.getTeams().size()-1);//Removes last placeing teams from all groups
        }
        //Here the groups are refactored and advance.
        for(int i = 0; i < 4; i++){
            groupList.get(i).add(groupList.get(groupList.size()).getList(0));
            groupList.get(i).add(groupList.get(groupList.size()).getList(1));
    }
        //Empty groups left over from refactoring are removed. Leaveing only 4 groups with 4 teams 16 total
        for(int i = 4; i < groupList.size(); i++){
            groupList.remove(4);
        }
    }


    public void simulateFinals(){
    	
    	for(int i = 14; i >= 0; i--) {
    		int firstTeamIndex = 2*i+1;
    		int secondTeamIndex = 2*i+2;
    		
    		Team team1 = tournamentTeams.get(firstTeamIndex);
    		Team team2 = tournamentTeams.get(secondTeamIndex);
    		System.out.println("Team1: " + team1.getName());
    		System.out.println("Team2: " + team2.getName());
    		groupMatch = new Match(team1,team2);
    		
    		groupMatch.playMatch();
    		
    		
    		int teamOneScore = groupMatch.getTotalHomeScore();
    		int teamTwoScore = groupMatch.getTotalAwayScore();
    		
    		if(teamOneScore > teamTwoScore) {
    			moveTeamUp(firstTeamIndex);
    		}
    		else if(teamOneScore == teamTwoScore) {
    			groupMatch.runTieBreakerLoop();
    		}
    		else {
    			moveTeamUp(secondTeamIndex);
    		}
    	}
    }
    
    public void moveTeamUp(int position) {
    	int newPos = (int)((position-1)/2);
    	if(!tournamentTeams.get(position).equals(tournamentTeams.get(newPos))) {
    		tournamentTeams.set(newPos, tournamentTeams.get(position));
    	}
    }
    
    
    public static void main(String[] args) {
    	FileInitializer fz = new FileInitializer();
    	ArrayList<Team> preQualifer = fz.getRegionTeams("CONCACAF");
    	ArrayList<Team> qualifiers = new ArrayList<Team>();
    	for(int i = 0; i < 16; i++) {
    		qualifiers.add(preQualifer.get(i));
    	}
    	
    	
    	GroupRounds groupStage = new GroupRounds(qualifiers);
    	groupStage.simulateFinals();
    	
    }

    }





