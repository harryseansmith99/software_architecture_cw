package Tournament;

public class ProFootballer extends HSSFootballer {

    public ProFootballer(int competitorNumber, Name name, String email, String age,
                         String country, League league) {

        super(competitorNumber, name, email, age, country, league);
        setLeague(League.PRO);
    }


    @Override
    public double getOverallScore() {

        // count up all match scores from scores array
        int score = 0;
        for (int i : this.getScoreArray()) {
            score += i;
        }

        // return overall score as is
        return score;
    }
}
