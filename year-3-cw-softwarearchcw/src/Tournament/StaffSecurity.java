package Tournament;

public enum StaffSecurity {
    STANDARD("Standard staff security"), OFFICIAL("Higher level security");

    private String securityLevel;

    private StaffSecurity(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public String toString() {
        return this.securityLevel;
    }
}
