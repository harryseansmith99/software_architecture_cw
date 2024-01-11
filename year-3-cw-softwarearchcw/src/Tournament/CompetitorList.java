package Tournament;

import java.io.*;
import java.util.*;

public class CompetitorList {
    private ArrayList<HSSFootballer> allFootballers;
    private ArrayList<Staff> staffMembers;
    private ArrayList<Integer> scoresList;


    public CompetitorList() {
        this.staffMembers = new ArrayList<Staff>();
        this.allFootballers = new ArrayList<HSSFootballer>();
        this.scoresList = new ArrayList<Integer>();
        addFootballersFromCsv();
        loadStaffMembers();
    }


    /**
     * Retrieves all the footballers.
     *
     * @return  an ArrayList of HSSFootballer containing all the footballers
     */
    public ArrayList<HSSFootballer> getAllFootballers() {
        return allFootballers;
    }


    /**
     * print the competitor number of each HSSFootballer to console
     */
    public void printAllFootballers() {
        for (HSSFootballer f : this.allFootballers) {
            System.out.println(f.getFullDetails() + "\n");
        }
    }


    /**
     * @return list of all staff members
     */
    public ArrayList<Staff> getAllStaffMembers() {
        return staffMembers;
    }


    /**
     * read csv file "HSScompetitors.csv and call processLine() for each line
     * populates the list of all footballers
     */
    public void addFootballersFromCsv() {
        BufferedReader bufferedReader = null;

        // read through each line and call processLine() to create footballer objects
        try {
            bufferedReader = new BufferedReader(new FileReader("src/Tournament/HSScompetitors.csv"));
            String inputLine = bufferedReader.readLine();
            while (inputLine != null) {
                processLine(inputLine);
                inputLine = bufferedReader.readLine();
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * create AmatuerFootballer, SemiproFootballer, or ProFootballer objects
     * by parsing the data in a csv by line. Each line hold details for a single
     * competitor, so each line will be used to create an object.
     * @param line the current line being processed in the csv file
     */
    private void processLine(String line) {

        try {
            // split apart the csv line at the commas
            String[] details = line.split(",");

            /*
            access the items at each segment of the line and
            init them as variables that we can pass to the constructor
            for HSSFootballer and its child classes
             */
            int competitorNumber = Integer.parseInt(details[0]);
            String[] nameArray = details[1].split(" ");
            Name name = new Name(nameArray[0], nameArray[1], nameArray[2]);
            String email = details[2];
            String age = details[3];
            String country = details[4];
            League league = League.valueOf(details[5]);

            // decide which child to instantiate based on the league
            HSSFootballer footballer;

            if (league == League.AMATEUR) {
                footballer = new AmateurFootballer(competitorNumber, name,
                        email, age, country, league);
            }
            else if (league == League.SEMIPRO) {
                footballer = new SemiproFootballer(competitorNumber, name,
                        email, age, country, league);
            }
            else {
                footballer = new ProFootballer(competitorNumber, name,
                        email, age, country, league);
            }

            // the rest of the line in csv is the scores which need to be set
            int[] scores = {
                    Integer.parseInt(details[6]), Integer.parseInt(details[7]),
                    Integer.parseInt(details[8]), Integer.parseInt(details[9]),
                    Integer.parseInt(details[10])
                    };

            footballer.setScoresArray(scores);
            this.allFootballers.add(footballer);

            // add these to the scores arraylist so we can find
            // most popular scores later
            this.scoresList.add(Integer.parseInt(details[6]));
            this.scoresList.add(Integer.parseInt(details[7]));
            this.scoresList.add(Integer.parseInt(details[8]));
            this.scoresList.add(Integer.parseInt(details[9]));
            this.scoresList.add(Integer.parseInt(details[10]));

        }

        // if error reading the file
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Add an official and a standard staff member to the staffMembers array list
     */
    public void loadStaffMembers() {
        String firstName = ""; String middleName = ""; String lastname = "";
        Name name1 = new Name(firstName, middleName, lastname);
        Name name2 = new Name(firstName, middleName, lastname);

        name1.setFirstName("Carlos"); name1.setMiddleName("Ali"); name1.setLastName("Frye");
        Staff official = new Staff(name1, 1, StaffSecurity.OFFICIAL);


        name2.setFirstName("Luca"); name2.setMiddleName("Barry"); name2.setLastName("Smith");
        Staff standard = new Staff(name2, 2, StaffSecurity.STANDARD);

        this.staffMembers.add(official);
        this.staffMembers.add(standard);
    }


    /**
     * @param competitorNumber competitorNumber field for a footballer
     * @return the footballer object that corresponds to the competitorNumber, or null if not found
     */
    public HSSFootballer getFootballer(int competitorNumber) {
        for (HSSFootballer f : this.allFootballers) {
            if (f.getCompetitorNumber() == competitorNumber) {
                return f;
            }
        }
        return null;
    }


    /**
     * @param competitorNumber competitorNumber field for a footballer
     * @return the short details of the footballer, or "competitor not found" if not found
     */
    public String getFootballerShortDetails(int competitorNumber) {
        for (HSSFootballer f : this.allFootballers) {
            if (f.getCompetitorNumber() == competitorNumber) {
                return f.getShortDetails();
            }
        }
        return "competitor not found";
    }


    /**
     * @param competitorNumber competitorNumber field for a footballer
     * @return the full details of the footballer, or "competitor not found" if not found
     */
    public String getFootballerFullDetails(int competitorNumber) {
        for (HSSFootballer f : this.allFootballers) {
            if (f.getCompetitorNumber() == competitorNumber) {
                return f.getFullDetails();
            }
        }
        return "competitor not found";
    }


    /**
     * @return getFullDetails() of all competitors in a neat, table fashion
     */
    public String getCompetitorsTable() {
        StringBuilder table = new StringBuilder();

        for (HSSFootballer f : this.allFootballers) {
            table.append(f.getFullDetails());
            table.append("\n\n");
        }
        return table.toString();
    }


    /**
     * @return getFullDetails() of all competitors in a neat, table fashion, sorted by firstname alphabetically
     */
    public String getCompetitorsTableByName() {
        StringBuilder table = new StringBuilder();

        ArrayList<String> firstNames = new ArrayList<>();

        for (HSSFootballer f : this.allFootballers) {
            firstNames.add(f.getFirstName());
        }

        firstNames.sort(String.CASE_INSENSITIVE_ORDER);

        for (String s : firstNames) {
            for (HSSFootballer f : this.allFootballers) {
                if (f.getFirstName().equals(s)) {
                    table.append(f.getFullDetails());
                    table.append("\n\n");
                }
            }
        }
        return "**************\nTable of Competitors\n\n\n" + table.toString();
    }


    /**
     * @return getFullDetails() of all competitors in a neat, table fashion, sorted by highest overall score
     */
    public String getCompetitorsTableByHighestScore() {
        StringBuilder table = new StringBuilder();

        ArrayList<Double> scores = new ArrayList<>();

        for (HSSFootballer f : this.allFootballers) {
            scores.add(f.getOverallScore());
        }

        scores.sort(Comparator.reverseOrder());

        for (Double d : scores) {
            for (HSSFootballer f : this.allFootballers) {
                if (f.getOverallScore() == d) {
                    table.append(f.getFullDetails());
                    table.append("\n\n");
                }
            }
        }
        return table.toString();
    }


    /**
     * @return getFullDetails() of all competitors in a neat, table fashion, only for amateur players
     */
    public String getCompetitorsTableByAmateurs() {
        StringBuilder table = new StringBuilder();

        for (HSSFootballer f : this.allFootballers) {
            if (f.getLeague() == League.AMATEUR) {
                table.append(f.getFullDetails());
                table.append("\n\n");
            }
        }
        return "**************\nTable of Competitors\n\n\n" + table.toString();
    }


    /**
     * @return getFullDetails() of all competitors in a neat, table fashion, only for semipro players
     */
    public String getCompetitorsTableBySemipros() {
        StringBuilder table = new StringBuilder();

        for (HSSFootballer f : this.allFootballers) {
            if (f.getLeague() == League.SEMIPRO) {
                table.append(f.getFullDetails());
                table.append("\n\n");
            }
        }
        return "**************\nTable of Competitors\n\n\n" + table.toString();
    }


    /**
     * @return getFullDetails() of all competitors in a neat, table fashion, only for pro players
     */
    public String getCompetitorsTableByPros() {
        StringBuilder table = new StringBuilder();

        for (HSSFootballer f : this.allFootballers) {
            if (f.getLeague() == League.PRO) {
                table.append(f.getFullDetails());
                table.append("\n\n");
            }
        }
        return "**************\nTable of Competitors\n\n\n" + table.toString();
    }


    /**
     * @return getFullDetails() of highest scoring competitor, or null if no competitors
     */
    public String getHighestScoringCompetitor() {
        Map<HSSFootballer, Double> allScores = new HashMap<>();

        // put all of our footballers intp a map, with overallscore as the value
        for (HSSFootballer f : this.allFootballers) {
            allScores.put(f, f.getOverallScore());
        }

        // use a lambda expression to find the highest value out of all entries in map
        double maxScore = (Collections.max(allScores.values()));

        for (Map.Entry<HSSFootballer, Double> entry : allScores.entrySet()) {
            if (entry.getValue() == maxScore) {
                return entry.getKey().getFullDetails();
            }
        }
        return null;
    }


    /**
     * @return report of average, total, min, and max scores of the tournament
     */
    public String getAverageScores() {
        Map<HSSFootballer, Double> allScores = new HashMap<>();

        // put all of our footballers intp a map, with overallscore as the value
        for (HSSFootballer f : this.allFootballers) {
            allScores.put(f, f.getOverallScore());
        }

        double maxScore = (Collections.max(allScores.values()));

        double minScore = (Collections.min(allScores.values()));

        double totalMeanScore = ( maxScore + minScore ) / 2;

        double totalScore = allScores.values().stream().mapToDouble(d -> d).sum();

        return  "Mean Score of Tournament: " + totalMeanScore +
                "\nTotal Score: " + totalScore +
                "\nMax Score: " + maxScore +
                "\nMin Score: " + minScore;
    }


    /**
     * count the frequency of which a score is found in the scoresList ArrayList
     * @param target the number you want to count frequency for
     * @return the frequency count for the target you provided
     */
    private int countScore(int target) {
        int count = 0;
        for (int value : this.scoresList) {
            if (value == target) {
                count++;
            }
        }
        return count;
    }

    /**
     * @return report of the frequency of every score in the tournament
     */
    public String getFreqOfScores() {

        // in this tournament you can only get a score of 0-10
        Map<Integer, Integer> scoreFreq = new HashMap<>();
        scoreFreq.put(0, 0);
        scoreFreq.put(1, 0);
        scoreFreq.put(2, 0);
        scoreFreq.put(3, 0);
        scoreFreq.put(4, 0);
        scoreFreq.put(5, 0);
        scoreFreq.put(6, 0);
        scoreFreq.put(7, 0);
        scoreFreq.put(8, 0);
        scoreFreq.put(9, 0);
        scoreFreq.put(10, 0);

        StringBuilder s = new StringBuilder();

        for (Map.Entry<Integer, Integer> entry : scoreFreq.entrySet()) {

            // count everytime the key of scoreFreq occurs and
            // set that as the value of that key
            scoreFreq.put(entry.getKey(), countScore(entry.getKey()));

            // append to StringBuilder
            s.append("Score ").append(entry.getKey())
                    .append(" was scored ")
                    .append(scoreFreq.get(entry.getKey())).append(" times\n");
        }

        return s.toString();
    }


    /**
     * producing the text file report laid out in stage 4 of the coursework specification
     */
    public void printStage4TextReport() {
        String table = "*********\nTable of Competitors:\n*********\n\n" + getCompetitorsTable();

        String highestScoringCompetitor = "\n\n*********\nHighest Scoring Competitor:\n*********\n\n" +
                                            getHighestScoringCompetitor();

        String tournamentAverages = "\n\n*********\nTournament Averages:\n" + getAverageScores();

        String frequencyReport = "\n\n*********\nScore Frequency Report:\n" + getFreqOfScores();

        String report = table + highestScoringCompetitor + tournamentAverages + frequencyReport;

        String path = "src/Tournament/stage_4_report.txt";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(report);
            writer.close();
        }
        catch (Exception e) {
            System.out.println("An error occurred when writing the stage 4 report to file");
            e.printStackTrace();
        }
    }


    public Staff getStaffMember(int staffId) {
        for (Staff s : this.staffMembers) {
            if (staffId == s.getId()) {
                return s;
            }
        }
        return null;
    }


    /**
     * @param staffId staffId of staff member you want to check is an official
     * @return true if the staff member can be found by id AND if the StaffSecurity constant is set to OFFICIAL
     */
    public boolean isOfficalStaff(int staffId) {
        for (Staff s : this.staffMembers) {
            if (s.getId() == staffId && s.getSecurityAccess() == StaffSecurity.OFFICIAL) {
                return true;
            }
        }
        return false;
    }


    /**
     * change the firstname of a competitor
     * @param staffId staff member making the change, has to be an official
     * @param competitorId competitor having details altered
     * @param firstName new firstname field to be set for competitor
     */
    public void changeCompetitorFirstName(int staffId, int competitorId, String firstName) {
        if (!isOfficalStaff(staffId)) {
            return;
        }
        this.getFootballer(competitorId).setFirstName(firstName);
    }


    /**
     * change the middlename of a competitor
     * @param staffId staff member making the change, has to be an official
     * @param competitorId competitor having details altered
     * @param middleName new middlename field to be set for competitor
     */
    public void changeCompetitorMiddleName(int staffId, int competitorId, String middleName) {
        if (!isOfficalStaff(staffId)) {
            return;
        }
        this.getFootballer(competitorId).setMiddleName(middleName);
    }


    /**
     * change the lastname of a competitor
     * @param staffId staff member making the change, has to be an official
     * @param competitorId competitor having details altered
     * @param lastName new lastname field to be set for competitor
     */
    public void changeCompetitorLastName(int staffId, int competitorId, String lastName) {
        if (!isOfficalStaff(staffId)) {
            return;
        }
        this.getFootballer(competitorId).setLastName(lastName);
    }


    /**
     * Method to remove a competitor from the array list allFootballers.
     * Calls isOfficialStaff(staffId) and removes a competitor if the result is true
     *
     * @param staffId staffMember ID that wants to remove the competitor
     * @param competitorId competitor to be removed
     */
    public void staffRemoveCompetitor(int staffId, int competitorId) {
        if (!isOfficalStaff(staffId)) {
            return;
        }
        this.allFootballers.remove(getFootballer(competitorId));
    }

}
