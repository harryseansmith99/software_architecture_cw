package Tournament;

public abstract class HSSFootballer {
    // this class serves as my "Competitor" class in this system, refactoring has taken place
    private Name name;
    private String email;
    private String age;
    private String country;
    private int competitorNumber;
    private League league;
    private String category;

    private int[] scores;

    public HSSFootballer(int competitorNumber,Name name, String email, String age,
                         String country, League league) {
        this.competitorNumber = competitorNumber; // competitor number
        this.name = name;
        this.email = email;
        this.age = age;
        this.country = country;
        this.league = league;
    }


    /**
     * @return name field of type Name
     */
    public Name getName() {
        return this.name;
    }


    /**
     * @return firstname from name field of type Name
     */
    public String getFirstName() {
        return name.getFirstName();
    }


    /**
     * set firstname for name field of type Name
     * @param firstName String parameter set as name.firstName
     */
    public void setFirstName(String firstName) {
        this.name.setFirstName(firstName);
    }


    /**
     * @return middlename from name field of type Name
     */
    public String getMiddleName() {
        return name.getMiddleName();
    }


    /**
     * set middle name for name field of type Name
     * @param middleName String parameter set as name.middleName
     */
    public void setMiddleName(String middleName) {
        this.name.setMiddleName(middleName);
    }


    /**
     * @return lastname from name field of type Name
     */
    public String getLastName() {
        return name.getLastName();
    }


    /**
     * set lastname for name field of type Name
     * @param lastName String parameter set as new name.lastName
     */
    public void setLastName(String lastName) {
        this.name.setLastName(lastName);
    }


    /**
     * @return email field
     */
    public String getEmail() {
        return this.email;
    }


    /**
     * set email field
     * @param email String parameter set as email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @return competitorNumber field
     */
    public int getCompetitorNumber() {
        return this.competitorNumber;
    }


    /**
     * set competitorNumber field
     * @param competitorNumber int parameter set as new competitorNumber
     */
    public void setCompetitorNumber(int competitorNumber) {
        this.competitorNumber = competitorNumber;
    }


    /**
     * @return country field
     */
    public String getCountry() {
        return this.country;
    }


    /**
     * set country field
     * @param country String parameter set as this.country
     */
    public void setCountry(String country) {
        this.country = country;
    }


    /**
     * @return age field
     */
    public String getAge() {
        return this.age;
    }


    /**
     * set age field
     * @param age String parameter set as this.age
     */
    public void setAge(String age) {
        this.age = age;
    }


    /**
     * @return League constant value
     */
    public League getLeague() {
        return this.league;
    }


    /**
     * set new League constant
     * @param league League parameter set as this.league
     */
    public void setLeague(League league) {
        this.league = league;
    }


    /**
     * Abstract method to calculate overall score for a competitor.
     * A mean will be calculated first of all, then depending on the
     * league that the competitor is in, there will be considerations for the score.
     * Consult the methods in AmateurFootballer, SemiproFootballer, and ProFootballer classes.
     *
     * @return return overallScore, calculated from scores array
     */
    public abstract double getOverallScore();


    /**
     * @return scores array
     */
    public int[] getScoreArray() {
        return this.scores;
    }


    public void setScoresArray(int[] scores) {
        if (scores.length == 5) {
            this.scores = scores;
        }
    }


    /**
     * returns full details of footballer, consisting of:
     * - competitorNumber
     * - fullname
     * - country
     * - league
     * - age
     * - overall score
     * @return full details of Footballer
     */
    public String getFullDetails() {
        return "Competitor number " + this.getCompetitorNumber() + ", name " + name.getFullName() + ", country: " + this.getCountry()
                + ".\n" + name.getFirstName() + " is a " + this.league.toString() + " aged " + this.getAge() +
                " and has an overall score of " + this.getOverallScore();
    }


    /**
     * returns short details, consisting of:
     * Competitor Number (INITIALS) overall score
     * @return short details
     */
    public String getShortDetails() {
        String firstNameInitial = this.name.getFirstName().substring(0, 1);
        String middleNameInitial = this.name.getMiddleName().substring(0, 1);
        String lastNameInitial = this.name.getLastName().substring(0, 1);

        String initials = firstNameInitial + middleNameInitial + lastNameInitial;

        return "CN " + this.getCompetitorNumber() + " (" + initials + ") has overall score " + this.getOverallScore();
    }

}
