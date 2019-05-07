import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Regions {
    //private variables used throughout the class
    private ArrayList<League> Americas;
    private ArrayList<League> Africa;
    private ArrayList<League> Asia;
    private ArrayList<League> Europe;
    private ArrayList<League> Oceania;
    private ArrayList<League> SouthAmerica;
    private ArrayList<Team> Finalist;
    //Constructor used to intialize Arraylists of America,Afirca,Asia,Europe,Oceania,SouthAmerica.
    public Regions(ArrayList<League> americas, ArrayList<League> africa, ArrayList<League> asia, ArrayList<League> europe, ArrayList<League> oceania, ArrayList<League> southAmerica) {
        Americas = americas;
        Africa = africa;
        Asia = asia;
        Europe = europe;
        Oceania = oceania;
        SouthAmerica = southAmerica;
    }

    public void simulateSeason(){
        // season sim to be done in League class for pre sorting and scoring decided by league matches
    }

    public ArrayList<ArrayList<Team>> setupWorldCup(){

        ArrayList<Team> AmericanTeams = new ArrayList<>();
        for(League l: Americas){
            AmericanTeams.addAll(l.getSortedTeams());
        }

        ArrayList<Team> AfricanTeams = new ArrayList<>();
        for(League l: Africa){
            AmericanTeams.addAll(l.getSortedTeams());
        }

        ArrayList<Team> AsianTeams = new ArrayList<>();
        for(League l: Asia){
            AmericanTeams.addAll(l.getSortedTeams());
        }

        ArrayList<Team> EuropianTeams = new ArrayList<>();
        for(League l: Europe){
            AmericanTeams.addAll(l.getSortedTeams());
        }

        ArrayList<Team> OceanicTeams = new ArrayList<>();
        for(League l: Oceania){
            AmericanTeams.addAll(l.getSortedTeams());
        }

        ArrayList<Team> SouthAmericanTeams = new ArrayList<>();
        for(League l: SouthAmerica){
            AmericanTeams.addAll(l.getSortedTeams());
        }


        ArrayList<Team> AmericasQual = getRegionQuals(AmericanTeams, 4);
        ArrayList<Team> AfricanQual = getRegionQuals(AfricanTeams,5);
        ArrayList<Team> AsianQual = getRegionQuals(AsianTeams, 5);
        ArrayList<Team> OcianicQual = getRegionQuals(OceanicTeams,1);
        ArrayList<Team> EuropeQual = getRegionQuals(EuropianTeams, 13);
        ArrayList<Team> SouthAmericanQual = getRegionQuals(SouthAmericanTeams,5);

        ArrayList<Team> allQuals = new ArrayList<>(34);

        allQuals.addAll(AmericasQual);
        allQuals.addAll(AfricanQual);
        allQuals.addAll(AsianQual);
        allQuals.addAll(OcianicQual);
        allQuals.addAll(EuropeQual);
        allQuals.addAll(SouthAmericanQual);

        ArrayList<Team> seeded32 =  get32Seeded(allQuals);

        ArrayList<ArrayList<Team>> pots = new ArrayList<ArrayList<Team>>(4);
        pots.add(new ArrayList<>(8));
        pots.add(new ArrayList<>(8));
        pots.add(new ArrayList<>(8));
        pots.add(new ArrayList<>(8));


        ArrayList<ArrayList<Team>> groups = new ArrayList<ArrayList<Team>>(8);

        groups.add(new ArrayList<Team>(4));
        groups.add(new ArrayList<Team>(4));
        groups.add(new ArrayList<Team>(4));
        groups.add(new ArrayList<Team>(4));
        groups.add(new ArrayList<Team>(4));
        groups.add(new ArrayList<Team>(4));
        groups.add(new ArrayList<Team>(4));
        groups.add(new ArrayList<Team>(4));


        /*ArrayList<Team> groupA;
        ArrayList<Team> groupB;
        ArrayList<Team> groupC;
        ArrayList<Team> groupD;
        ArrayList<Team> groupE;
        ArrayList<Team> groupF;
        ArrayList<Team> groupH;*/

        for(int i = 0; i < seeded32.size(); i++){
            if(i >= 0 && i < 8){
                pots.get(1).add(seeded32.get(i));
            }
            if(i >= 8 && i < 16){
                pots.get(2).add(seeded32.get(i));
            }
            if(i >= 16 && i < 24){
                pots.get(3).add(seeded32.get(i));
            }
            if(i >= 24 && i < 32){
                pots.get(4).add(seeded32.get(i));
            }
        }

        for(int j = 0;j<8;j++) {
            for (int i = 0; i < 4; i++) {
                int randSize = pots.get(i).size();
                Random r = new Random();
                int rand = r.nextInt((randSize)+1);
                groups.get(j).add(pots.get(i).get(rand));
                pots.get(i).remove(rand);
            }
        }
        return groups;

    }

    private ArrayList<Team> getRegionQuals(ArrayList<Team> l, int n){

        ArrayList<Team> tempList = l;
        ArrayList<Team> qualifiers = new ArrayList<>(n);

        int topScore = 0;
        int topIndex = -1;

        for(int j = 0; j < l.size(); j++) {
            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i).getRank() > topScore) {
                    topScore = tempList.get(i).getRank();
                    topIndex = i;
                }
                qualifiers.add(tempList.get(topIndex));
                tempList.remove(topIndex);

                topScore = 0;

            }
        }
        return qualifiers;
    }

    private ArrayList<Team> get32Seeded(ArrayList<Team> l){

        ArrayList<Team> tempList = new ArrayList<Team>(34);
        ArrayList<Team> seeded = new ArrayList<>();

        int topSeed = 0;
        int topIndex = -1;

        for(int j = 0; j < l.size(); j++) {
            for (int i = 0; i < tempList.size(); i++) {
                if (tempList.get(i).getSeedRank() > topSeed) {
                    topSeed = tempList.get(i).getRank();
                    topIndex = i;
                }
                seeded.add(tempList.get(topIndex));
                tempList.remove(topIndex);

                topSeed = 0;

            }
        }
        return seeded;
    }


    // simulate group round takes the list of groups, makes a league for each group an runs the specific group sim so each team play once.
    // the top two players get added to the Finalist list of teams (final 16)
    public void simulateGroupRound(ArrayList<ArrayList<Team>> g){

//        for(int f = 0; f < g.size(); f++) {
//
//            League groupRound = new League(g.get(f));
//
//            groupRound.simulateGroupMatch();
//
//            ArrayList<Team> sortedGroup = groupRound.getSortedGroupTeams();
//
//            Finalist.add(sortedGroup.get(4));
//            Finalist.add(sortedGroup.get(3));
//        }

    }

    public void simulateFinals(ArrayList<League> l){

    }

}
