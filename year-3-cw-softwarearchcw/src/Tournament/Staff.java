package Tournament;

import static Tournament.League.AMATEUR;
import static Tournament.League.SEMIPRO;
import static Tournament.StaffSecurity.OFFICIAL;

public class Staff{
    private Name name;
    private int id;
    private StaffSecurity securityAccess;

    public Staff(Name name, int id, StaffSecurity securityAccess) {
        this.name = name;
        this.id = id;
        this.securityAccess = securityAccess;
    }


    /**
     * @return id field
     */
    public int getId() {
        return id;
    }


    /**
     * @return securityAccess field
     */
    public StaffSecurity getSecurityAccess() {
        return securityAccess;
    }


    /**
     * set securityAccess field
     * @param securityAccess String parameter set as this.securityAccess
     */
    public void setSecurityAccess(StaffSecurity securityAccess) {
        this.securityAccess = securityAccess;
    }


    /**
     * create a new competitor as staff have the power to register
     * newcomers to the competition, thus creating a new object as far
     * as the program is concerned
     * @param competitorNumber competitor id
     * @param firstname competitor firstName
     * @param middleName competitor middleName
     * @param lastName competitor lastName
     * @param email competitor email
     * @param age competitor age
     * @param country competitor country
     * @return new Amateur, Semipro, or Pro HSSFootballer child object, depending on the league provided, or null
     */
    public HSSFootballer registerCompetitor(int competitorNumber, String firstname,
                                            String middleName, String lastName, String email,
                                            String age, String country, League league) {

        Name name = new Name(firstname, middleName, lastName);

        // only officials can register competitors
        if (!(this.securityAccess == OFFICIAL)) {
            System.out.println("security level not high enough, not permitted to register new competitor");
        }
        else {
            if (league == AMATEUR)
                return new AmateurFootballer(competitorNumber, name, email, age, country, league);

            else if (league == SEMIPRO)
                return new SemiproFootballer(competitorNumber, name, email, age, country, league);

            else
                return new ProFootballer(competitorNumber, name, email, age, country, league);
        }
        return null;
    }
}
