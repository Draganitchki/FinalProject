import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;

/* 
 * @Zion Chilagan
 * Class league is responsible for simulating qualifying tournaments for a specific region. Each team 
 * plays against each other twice, with both teams switching between home and away. 
 * This is in a very basic state, posting this now to get some feedback from others.
 */

public class League implements Playable {

	private ArrayList<Team> teams = new ArrayList<Team>();
	private MatchHistory matchHistory;
	
	
	//League passes in a list of teams
	public League(ArrayList<Team> newTeams) {
		teams = newTeams;
		matchHistory=new MatchHistory();
	}
	
	/*
	 * SimulateQualifiers uses an advance for loop to traverse through the array so that each team plays 
	 * each other twice. 
	 * 
	 * To-do: Right now, the teams are playing each other in a linear order, would
	 * like to add some sort of randomizing effect to simulate two teams "waiting" before playing each other
	 * again, and of course to randomize order of matches.
	 * 
	 * This is last priority but implementing how each region actually formats their qualifiers
	 */
	public void simulateQualifiers() {
		for(int i = 0; i < teams.size(); i++) {						
			for(int k = teams.size()-1; k > i; k--) {
				
				
				//Home vs Away
				System.out.println(teams.get(i).getName() + " vs. " + teams.get(k).getName());
				Match HomeVsAway=new Match(teams.get(i),teams.get(k));
				HomeVsAway.playMatch();
				if(HomeVsAway.isTie())
					HomeVsAway.runTieBreaker();
				matchHistory.addMatch(HomeVsAway);
				teams.get(i).setPoints(HomeVsAway.getHomePts());
				teams.get(k).setPoints(HomeVsAway.getAwayPts());
				teams.get(i).setScore(HomeVsAway.getTotalHomeScore());
				teams.get(k).setScore(HomeVsAway.getTotalAwayScore());
				
				
				//Away vs Home
				System.out.println(teams.get(k).getName() + " vs. " + teams.get(i).getName());
				Match AwayVsHome=new Match(teams.get(k),teams.get(i));
				AwayVsHome.playMatch();
				if(AwayVsHome.isTie())
					AwayVsHome.runTieBreaker();
				matchHistory.addMatch(AwayVsHome);
				teams.get(k).setPoints(AwayVsHome.getHomePts());
				teams.get(i).setPoints(AwayVsHome.getAwayPts());
				teams.get(k).setScore(AwayVsHome.getTotalHomeScore());
				teams.get(i).setScore(AwayVsHome.getTotalAwayScore());
				
				
			}
		}
	}
	
	
	/*
	 * getTournamentScore prints the tournament score for each team
	 * 
	 * 
	 */
	
	public void getTournamentScore() {
		getSortedTeams();
		for(Team t: teams) {
			System.out.println(t.getName() + " tournamnet points: " + t.getPoints() + " total goals scored: " + t.getScore());
		}
	}
	
	/*
	 * getSortedTeams() creates a custom comparator object which is called by Collections then reverses the order
	 * since it sorts in ascending order based on team's score
	 */
	
	public ArrayList<Team> getSortedTeams() {
		Comparator<Team> newRankComparator = Comparator.comparingInt(Team::getPoints);	
		Collections.sort(teams, newRankComparator);
		Collections.reverse(teams);
		tieBreaker(teams);
		return teams;
	}
	
	/*
	 * tieBreaker method takes in an array list which should already be sorted based on highest 
	 * tournament score to lowest, then it traverses through the list and looks for any ties based on score.
	 * If there is a tie, whichever team had the highest amount of goals scored in the tournament gets the edge.
	 * 
	 * I'm not sure if this is the format but during group stage in the world cup tie breakers are broken down as follows:
	 * 1) Highest amount of points
	 * 2) Goal difference (The team who scored the most points between their match up gets the edge)
	 * 3) Goals scored (Team with the highest amount of goals scored gets the edge)
	 * 
	 * TO-DO
	 * -What happens when the teams had the same amount of points? What should we do...........hmm...
	 * 
	 */
	
	//improved tie breaker to handle an x-way ties
	public void tieBreaker(ArrayList<Team> teams) {
		
		for(int i = 0; i < teams.size(); i++) {
			for(int k = teams.size() - 1; k > i; k--) {
				if(teams.get(i).getPoints() == teams.get(k).getPoints()) {
					if( teams.get(i).getScore() < teams.get(k).getScore()) {
						Collections.swap(teams, i, k);
					}
				}
			}
		}
	}
	
	
	
	/*
	 * Testing using nba teams
	 */
	public static void main(String [] args) {
		ArrayList<Team> teams = new ArrayList<Team>();
		
		//Constructs teams with only having the knowledge of their name and rank
		Team celtics = new Team("Celtics",1);
		//celtics.setPoints(100);
		//celtics.setScore(500);
		teams.add(celtics);
		
		
		Team gsw = new Team("GSW", 8);
		//gsw.setPoints(100);
		//gsw.setScore(400);
		teams.add(gsw);
		
		Team okc = new Team("OKC", 7);
		///okc.setPoints(100);
		//okc.setScore(300);
		teams.add(okc);
		
		Team rockets = new Team("Rockets", 5);
		//rockets.setPoints(100);
		//rockets.setScore(200);
		teams.add(rockets);
		
		Team knicks = new Team("Knicks", 3);
		//knicks.setPoints(100);
		//knicks.setScore(500);
		teams.add(knicks);
		
		
		
		
		
		League league = new League(teams);
		league.simulateQualifiers();
		
		System.out.println("-------SORTED--------");
		
		league.getTournamentScore();
		
	}
}
