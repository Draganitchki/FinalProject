import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//Author Chris Williamson
public class FileInitializer {
	private String instructionPath,teamDataPath;
	private BufferedReader teamReader,insReader;
	private ArrayList<Team> teamList;
	private String instructions;
	public FileInitializer()
	{
		teamDataPath="TeamInfo.txt";
		instructionPath="Instructions.txt";
		File teamData=new File(teamDataPath);
		File instructionData=new File(instructionPath);
		teamList=new ArrayList<Team>();
		instructions=new String("");
		try {
			teamReader=new BufferedReader(new FileReader(teamData));
			//insReader=new BufferedReader(new FileReader(instructionData));
			//readInstructions();
			readTeamData();
			scaleTeamRank();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not read instructions or Team data. ");
			e.printStackTrace();
			
		}
		
	}
	//sets the rankings of team from 1 to 207
	public void scaleTeamRank()
	{
		double smallestRank=Double.POSITIVE_INFINITY;
		int index=0;
		ArrayList<Team> newList=new ArrayList<Team>();
		Team smallestTeam=new Team();
		for(int i=teamList.size();i>0;i--)
		{
			for(int j=0;j<teamList.size();j++)
			{
				if(teamList.get(j).getRank()<=smallestRank&&!newList.contains(teamList.get(j)))
				{
					smallestRank=teamList.get(j).getRank();
					smallestTeam=teamList.get(j);
					//System.out.println("Smallest Team: "+teamList.get(j).getName()+" "+teamList.get(j).getRank());
					index=j;
				}
			}
			
			smallestTeam.setRank(i);
			newList.add(smallestTeam);
			smallestRank=Double.POSITIVE_INFINITY;
			
		}
		this.teamList=newList;
	}
	public void setInstructionsPath(String s)
	{
		instructionPath=s;
		File instructionData=new File(instructionPath);
		try {
			insReader=new BufferedReader(new FileReader(instructionData));
			readInstructions();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not read instructions");
		}
			
	}
	public void setTeamPath(String s)
	{
		this.teamDataPath=s;
		File teamData=new File(teamDataPath);
		try {
			teamReader=new BufferedReader(new FileReader(teamData));
			readTeamData();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not read Team data. ");
			e.printStackTrace();
			
		}
	}
	//causes the FileReader to read all of the data from the provided files. 
	public void readData()
	{
		this.readInstructions();
		this.readTeamData();
	}
	public void readTeamData()
	{
		Team temp=new Team();
		String data;
		int counter=0;
		if(teamReader!=null)
		{
			while(true)
			{
				try {
					data=teamReader.readLine();
					if(data!=null)
					{
						//System.out.println("NUM: "+counter+" Data: "+data);
						if(counter==0)
						{
							temp.setName(data);
						}
						if(counter==1)
						{
							data=data.substring(1, data.length()-1);
							temp.setRank(Integer.parseInt(data));
							temp.setPoints((Integer.parseInt(data)));
						}
						if(counter==2)
						{
							data=data.substring(1, data.length()-1);
							temp.setPreRank(Integer.parseInt(data));
						}
						if(counter==3) 
						{
							temp.setRegion(data);
						}
							
						counter=(counter+1);
						if(counter==4)
						{
							this.teamList.add(temp);
							temp=new Team();
						}
						data="";
						counter=counter%4;
					}
					else
						break;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Error reading Team Data");
					e.printStackTrace();
				}
			}
		}
	}
	public void readInstructions()
	{
		if(insReader!=null)
		while(true)
		{
			String data;
			try {
				data = insReader.readLine();
				if(data==null)
					break;
				else
				{
					instructions=instructions+" "+data;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error reading data from instructions");
				e.printStackTrace();
			}
			
		}
	}
	public ArrayList<Team> getTeams()
	{
		return this.teamList;
	}
	public String getInstructions()
	{
		return this.instructions;
	}
	public String toString()
	{
		String result="";
		for(int i= 0;i<this.teamList.size();i++)
		{
			String temp="Team: "+teamList.get(i).getName()+" Rank: "+teamList.get(i).getRank()+ " Region: " + teamList.get(i).getRegion() + ". \n";
			result+=temp;
		}
		return result;
	}
	
	
	public ArrayList<Team> getRegionTeams(String regionName) {	//added by zion
		String region = " #" + regionName + "# ";
		ArrayList<Team> temp = new ArrayList<Team>();
		for(Team t: teamList) {
			if(t.getRegion().equals(region)) {
				temp.add(t);
			}
				
		}
		return temp;
	}
	
	
	
	public static void main(String[] args)
	{
		FileInitializer fz = new FileInitializer();
		ArrayList<Team> CONCACAF = fz.getRegionTeams("CONCACAF");
		for(Team t: CONCACAF) {
			System.out.println(t.toString());
		}
		ArrayList<Team> AFC = fz.getRegionTeams("AFC");
		for(Team t: AFC) {
			System.out.println(t.toString());
		}
		ArrayList<Team> UEFA = fz.getRegionTeams("UEFA");
		for(Team t: UEFA) {
			System.out.println(t.toString());
		}
		ArrayList<Team> CAF = fz.getRegionTeams("CAF");
		for(Team t: CAF) {
			System.out.println(t.toString());
		}
		ArrayList<Team> OFC = fz.getRegionTeams("OFC");
		for(Team t: OFC) {
			System.out.println(t.toString());
		}
		ArrayList<Team> CONMEBOL = fz.getRegionTeams("CONMEBOL");
		for(Team t: CONMEBOL) {
			System.out.println(t.toString());
		}
		
		
		//System.out.println(fz.toString());
	}

}