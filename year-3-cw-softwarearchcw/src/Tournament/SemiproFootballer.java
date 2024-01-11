package Tournament;

public class SemiproFootballer extends HSSFootballer {


    public SemiproFootballer(int competitorNumber, Name name, String email, String age,
                             String country, League league) {

        super(competitorNumber, name, email, age, country, league);
        setLeague(League.SEMIPRO);
    }


    @Override
    public double getOverallScore() {

        // count up all match scores from scores array
        int score = 0;
        for (int i : this.getScoreArray()) {
            score += i;
        }

        // return overall score with a 5% buff
        return score * 1.05;
    }

}
