package Tournament;

public class Name {

    private String firstName;
    private String middleName;
    private String lastName;

    public Name(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }


    /**
     * @return firstName field
     */
    public String getFirstName() {
        return this.firstName;
    }


    /**
     * @return middleName field
     */
    public String getMiddleName() {
        return middleName;
    }


    /**
     * @return lastName field
     */
    public String getLastName() {
        return this.lastName;
    }


    /**
     * returns concatenation of getFirstName(), getMiddleName(), and getLastName()
     * @return full name as String
     */
    public String getFullName() {
        return this.getFirstName() + " " + this.getMiddleName() + " " + this.getLastName();
    }


    /**
     * sets firstName field as given parameter
     * @param firstName String to be set as firstName field
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * sets middleName field as given parameter
     * @param middleName String to be set as middleName field
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }


    /**
     * sets lastName field as given parameter
     * @param lastName String to be set as lastName field
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * @return "Name: full name", by calling getFullName()
     */
    public String toString() {
        return "Name: " + this.getFullName();
    }

}
