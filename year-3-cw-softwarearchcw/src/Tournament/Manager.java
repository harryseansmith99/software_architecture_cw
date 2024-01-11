package Tournament;

public class Manager {

    private GUI gui;
    private CompetitorList competitorList;

    public void run() {
        competitorList = new CompetitorList();
        gui = new GUI(competitorList);
        gui.setVisible(true);
    }

    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.run();
    }

}
