package Tournament;

public enum League {
    AMATEUR("Amateur player"), SEMIPRO("SemiPro player"), PRO("Professional player");

    private String type;

    private League(String type) {
        this.type = type.toUpperCase();
    }

    public String toString() {return this.type;}

}
