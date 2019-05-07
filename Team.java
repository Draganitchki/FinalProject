/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Objects;
import java.util.Random;

/**
 *  @author chris_williamson
 *  This class is used to create an object of Team with rank,prRank,seedRank,groupPoints, region, name,points,score,match score.
 */
public class Team {
    //Private variables used throughout Team class
    private boolean seed;
    private int rank, preRank, seedRank, groupPoints;
    private String region,name;
    private int points, score, matchScore; //attribute matchSCore added by z
    //Default constructor
    public Team()
    {
        seed=true;
        rank=0;
        preRank=0;
        region="AFC";
        name="Default";
        points=0;
        score=0;
        matchScore = 0; //added by z
        groupPoints = 0;
    }
    //Constructor that initializes the name and rank of a team
    public Team(String Name, int rank)
    {
        this.name=Name;
        this.rank=rank;
        preRank=0;
        region="AFC";
        seed=true;
        points=0;
        score=0;
        groupPoints = 0;
    }
    //Constructor that initializes the name,rank,score and points of a team.
    public Team(String Name, int rank, int score, int points)
    {
        this.name=Name;
        this.rank=rank;
        this.score=score;
        this.points=points;
        groupPoints = 0;
    }
    
    //Getters and setters
    public void setPoints(int i)
    {
        this.points+=i;
    }
    public int getPoints()
    {
        return points;
    }
    public void setScore(int i)
    {
        score+=i;
    }
    public void setMatchScore(int i) { 	//method added by z...sets score for a specific match
        matchScore = i;
    }
    public int getMatchScore() {	//method added by z ...gets score from a specific match
        return matchScore;
    }
    public int getScore()
    {
        return score;
    }
    public void setSeed(boolean b)
    {
        this.seed=b;
    }
    public boolean getSeed()
    {
        return seed;
    }
    public int getSeedRank() {
        return seedRank;
    }

    public int getGroupPoints() {
        return groupPoints;
    }

    public void setGroupPoints(int groupPoints) {
        this.groupPoints = groupPoints;
    }

    public void setSeedRank(int seedRank) {
        this.seedRank = seedRank;
    }

    public int getRank()
    {
        return rank;
    }
    public void setRank(int r)
    {
        this.rank=r;
    }
    public void setName(String r)
    {
        this.name=r;
    }
    public String getName()
    {
        return name;
    }
    public void setPreRank(int i)
    {
        this.preRank=i;
    }
    public int getPreRank()
    {
        return preRank;
    }
    public String getRegion()
    {
        return region;
    }
    public void setRegion(String s)
    {
        this.region=s;
    }
    
    @Override 
    public String toString()
    {
        String info;
        info = "Team: " + getName() + " Rank: " + getRank() + " Region: " + getRegion();
        return info;
    }

    /*
     * This method computes the probability that this team will score based on their rank. And outputs a 1 if the team did score or a 0 if it did not.
    The higher the rank(closest to 1) the higher the probability that the team will score.
    Examples are based around 20 method calls 
    ex: if the rank is around 207, then there will be approximately 0-3 goals for every 20 calls of this method ->~0-10%score probability per call
    ex: if the rank is around 100, -> 1-6 goals ~5-25% score probability per method call
    ex: rank 50 -> ~5-9 goals ~10-45% score probability per method call
    ex: rank 25 -> ~7-11 goals ~35-60% score probability per method call
    ex: rank 2 -> ~14-20 goals ~65-100% score probability per method call
    ex: rank 1-> ~15-20 goals  ~75-100% score probability per method call
    */
    public int score()
    {
        Random r = new Random();
        double rankoffset=25;
        double minrank=207;
        double skillScale=.645956;
        double randomProb=r.nextDouble();
        double baseline = minrank-(rank+rankoffset)*randomProb;
        double skillLevel= baseline/(minrank+rankoffset);

        if(skillLevel<.3)
            skillLevel=.3;
        double scoreProb = 100*skillLevel*skillScale;

        if(scoreProb>=51)
            return 1;
        return 0;
    }
    /*
     * This method returns the amount of goals that this team scores after a certain amount of kicks
     * Parameters: The amount of kicks that this team performed during a game
     * returns: An amount of goals where 0<=return val<=numKicks
     */
    public int score(int numKicks)
    {
        int numGoals=0;
        for(int i=0;i<numKicks;i++)
        {
            numGoals+=score();
        }
        return numGoals;
    }

    public static void main(String[] args)
    {
        int a=0;
        int numIterations=20;
        int numScore=0;
        Team a1 = new Team();
        a1.setRank(207);
        a1.setName("Test");

        while(a<numIterations)
        {
            if(a1.score()==1)
                numScore+=1;
            a+=1;
        }
        System.out.println("Num scores: "+numScore+", Rank: "+a1.getRank()+", Num Iterations: "+numIterations);
        numScore=0;
        a=0;
        a1.setRank(100);
        while(a<numIterations)
        {
            if(a1.score()==1)
                numScore+=1;
            a+=1;
        }
        a=0;
        System.out.println("Num scores: "+numScore+", Rank: "+a1.getRank()+", Num Iterations: "+numIterations);
        a1.setRank(50);
        numScore=0;
        while(a<numIterations)
        {
            if(a1.score()==1)
                numScore+=1;
            a+=1;
        }
        System.out.println("Num scores: "+numScore+", Rank: "+a1.getRank()+", Num Iterations: "+numIterations);
        a1.setRank(25);
        numScore=0;
        a=0;
        while(a<numIterations)
        {
            if(a1.score()==1)
                numScore+=1;
            a+=1;
        }
        System.out.println("Num scores: "+numScore+", Rank: "+a1.getRank()+", Num Iterations: "+numIterations);
        a1.setRank(2);
        a=0;
        numScore=0;
        while(a<numIterations)
        {
            if(a1.score()==1)
                numScore+=1;
            a+=1;
        }
        System.out.println("Num scores: "+numScore+", Rank: "+a1.getRank()+", Num Iterations: "+numIterations);
        a1.setRank(1);
        numScore=a1.score(numIterations);
        System.out.println("Num scores: "+numScore+", Rank: "+a1.getRank()+", Num Iterations: "+numIterations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return seed == team.seed &&
                rank == team.rank &&
                preRank == team.preRank &&
                seedRank == team.seedRank &&
                points == team.points &&
                score == team.score &&
                matchScore == team.matchScore &&
                Objects.equals(region, team.region) &&
                Objects.equals(name, team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seed, rank, preRank, seedRank, region, name, points, score, matchScore);
    }
}

