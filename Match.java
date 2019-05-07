//chris
public class Match {

	private Team home,away,winner,loser;
	private boolean isTie,isOverTime;
	private int homePts,awayPts;
	private int numKicks;
	private int homeScore,awayScore;
	
	public Match()
	{
		home=new Team();
		away=new Team();
		this.numKicks=10;
		homePts=0;
		awayPts=0;
		isTie=false;
		homeScore=0;
		awayScore=0;
		isOverTime=false;
	}
	public Match(Team home, Team away)
	{
		this.home=home;
		this.away=away;
		this.numKicks=10;
		homePts=0;
		awayPts=0;
		homeScore=0;
		awayScore=0;
		isTie=false;
		isOverTime=false;
	}
	public Team  getHome()
	{
		return home;
	}
	public Team getAway()
	{
		return away;
	}
	public int getTotalHomeScore()
	{
		return homeScore;
	}
	public int getTotalAwayScore()
	{
		return awayScore;
	}
	public void setHome(Team t)
	{
		this.home=t;
	}
	public void setAway(Team t)
	{
		this.away=t;
	}
	public void setNumKicks(int i)
	{
		this.numKicks=i;
	}
	public int getNumKicks()
	{
		return this.numKicks;
	}
	//returns the winning team. Or null if it is a tie
	public void playMatch()
	{
		int temp=home.score(numKicks);
		int temp2=away.score(numKicks);
		homeScore+=temp;
		awayScore+=temp2;
		home.setScore(temp);
		away.setScore(temp2);
		if(homeScore>awayScore)
		{
			winner=home;
			loser=away;
			homePts=3;
			awayPts=0;
			isTie=false;
			home.setPoints(homePts);
			away.setPoints(awayPts);
			System.out.println("The "+home.getName()+" have won!");
		}
		else if(homeScore<awayScore)
		{
			winner=away;
			awayPts=3;
			homePts=0;
			loser=home;
			isTie=false;
			home.setPoints(homePts);
			away.setPoints(awayPts);
			System.out.println("The "+away.getName()+" have won!");
		}
		else
		{
			if(isOverTime==false)
			{
				System.out.println("Its a tie!");
			}
			isTie=true;
			winner=null;
			loser=null;
			homePts=1;
			awayPts=1;
			if(this.isOverTime==true)
			{
				System.out.println("Its an OverTime Tie");
				home.setPoints(1);
				away.setPoints(1);
			}
			runTieBreakerLoop(); //added by zion
		}	
			
	}
	public int getHomePts()
	{
		return homePts;
	}
	public int getAwayPts()
	{
		return awayPts;
	}
	//tells you whether the match resulted in a tie
	public boolean isTie()
	{
		return isTie;
	}

	public boolean hasWinner() {

		if(winner!=null)

			return true;

		return false;

	}

	public boolean hasLoser() {

		if(loser!=null) {
			return true;
		}
		return false;
	}
	//returns the winner, or null if there isnt one
	public Team getWinner()
	{
		if(hasWinner())
			return winner;
		return null;
	}
	//returns the loser, or null if there isnt one
	public Team getLoser()
	{
		if(hasLoser())
			return loser;
		return null;
	}
	public void runTieBreaker()
	{
		if(isTie())
		{
			playMatch();
			isOverTime=true;
		}
	}
	public void runTieBreakerLoop() {	//added by zion 5/5
		while(isTie()) {
			playMatch();
		}
			
	}
	public boolean playedOverTime()
	{
		return isOverTime;
	}
	public void setHomeScore(int x) {
		homeScore = x;
	}
	public void setAwayScore(int x) {
		awayScore = x;
	}

}
