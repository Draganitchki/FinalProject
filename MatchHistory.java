import java.util.ArrayList;

//chris
// This class is ued to display the information of each winning and losing match or if there is a tie
public class MatchHistory {
	
	private ArrayList<Match> history;
	
	//constructor
	public MatchHistory()
	{
		history=new ArrayList<Match>();
	}
	//adds a specific match
	public void addMatch(Match m)
	{
		history.add(m);
	}
	//returns a specific match
	public Match getMatch(int i)
	{
		return history.get(i);
	}
	//returns the number of winners
	public int getNumWinners()
	{
		int counter=0;
		for(int i=0;i<history.size();i++)
		{
			if(history.get(i).hasWinner())
				counter++;
		}
		return counter;
	}
	//returns the number of losers
	public int getNumLosers()
	{
		int counter=0;
		for(int i=0;i<history.size();i++)
		{
			if(history.get(i).hasLoser())
				counter++;
		}
		return counter;
	}
	//returns the number of ties
	public int getNumTies()
	{
		int counter=0;
		for(int i=0;i<history.size();i++)
		{
			if(history.get(i).isTie())
				counter++;
		}
		return counter;
	}
	//returns a list of teams that won any of their games
	public ArrayList<Team> getWinningTeamList()
	{
		ArrayList<Team >list=new ArrayList<Team>();
		for(Match m: history)
		{
			if(m.hasWinner())
				if(!list.contains(m.getWinner()))
				list.add(m.getWinner());
		}
		return list;
	}
	//returns a list of teams that lost any of their games
	public ArrayList<Team> getLosingTeamList()
	{
		ArrayList<Team >list=new ArrayList<Team>();
		for(Match m: history)
		{
			if(m.hasLoser())
				if(!list.contains(m.getLoser()))
				list.add(m.getLoser());
		}
		return list;
	}
	//returns a list of teams that tied in any of their games
	public ArrayList<Team> getTiedTeamList()
	{
		ArrayList<Team >list=new ArrayList<Team>();
		for(Match m: history)
		{
			if(m.isTie())
			{
				if(!list.contains(m.getHome()))
				list.add(m.getHome());
				if(!list.contains(m.getAway()))
				list.add(m.getAway());
			}
		}
		return list;
	}
	//returns the full list of matches
	public ArrayList<Match> getHistory()
	{
		return history;
	}
	//returns a number representing how many overtime games were played
	public int getNumOverTimes()
	{
		int counter=0;
		for(int i=0;i<history.size();i++)
		{
			if(history.get(i).playedOverTime())
				counter++;
		}
		return counter;
	}
	//returns a list of matches that contain this team
	public ArrayList<Match> getMatchesFromTeam(Team t)
	{
		ArrayList<Match >list=new ArrayList<Match>();
		for(Match m: history)
		{
			if(m.getHome()==t||m.getAway()==t)
				list.add(m);
		}
		return list;
	}
	//returns a list of matches where the specified team is the home team
	public ArrayList<Match> getMatchesFromHomeTeam(Team t)
	{
		ArrayList<Match >list=new ArrayList<Match>();
		for(Match m: history)
		{
			if(m.getHome()==t)
				list.add(m);
		}
		return list;
	}
	//returns a list of matches where the specified team is the away team
	public ArrayList<Match> getMatchesFromAwayTeam(Team t)
	{
		ArrayList<Match >list=new ArrayList<Match>();
		for(Match m: history)
		{
			if(m.getAway()==t)
				list.add(m);
		}
		return list;
	}

}
