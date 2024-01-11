package Tournament;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI extends JFrame  implements ActionListener {

    private CompetitorList competitorList;

    JTextField result;
    JTextField searchField;
    JButton searchShortDetails, searchFullDetails, removeCompetitor;
    JScrollPane scrollList;
    JButton showListById, showListByName, showListByScore, showListByAmateur,
            showListBySemipro, showListByPro, stage4Report, close;
    JTextArea displayList;


    public GUI(CompetitorList competitorList) {
        this.competitorList = competitorList;

        setTitle("Competitor List");
        setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);

        northPanel();
        centerPanel();
        southPanel();

        pack();
        setVisible(true);
    }


    /**
     * Set up the north panel with buttons
     */
    private void northPanel() {
        //add north panel containing some buttons
        JPanel northPanel = new JPanel();
        showListById = new JButton("List By ID");
        showListById.addActionListener(this);

        showListByName = new JButton("List By Name");
        showListByName.addActionListener(this);

        showListByScore = new JButton("List By Overall Score");
        showListByScore.addActionListener(this);

        showListByAmateur = new JButton("List By Amateurs");
        showListByAmateur.addActionListener(this);

        showListBySemipro = new JButton("List By Semipros");
        showListBySemipro.addActionListener(this);

        showListByPro = new JButton("List By Pros");
        showListByPro.addActionListener(this);

        stage4Report = new JButton("Print stage_4_report.txt");
        stage4Report.addActionListener(this);

        close = new JButton("Close");
        close.addActionListener(this);

        northPanel.add (showListById);
        northPanel.add(showListByName);
        northPanel.add(showListByScore);
        northPanel.add(showListByAmateur);
        northPanel.add(showListBySemipro);
        northPanel.add(showListByPro);
        northPanel.add(stage4Report);
        northPanel.add(close);
        this.add(northPanel, BorderLayout.NORTH);
    }

    /**
     * set up the center panel where the returned string from north panel
     * buttons can appear
     */
    private void centerPanel() {
        displayList = new JTextArea(12, 30);
        displayList.setFont(new Font (Font.MONOSPACED, Font.PLAIN,14));
        displayList.setEditable(false);
        scrollList = new JScrollPane(displayList);
        this.add(scrollList,BorderLayout.CENTER);
    }


    /**
     * set up south panel with search bar, button to search for short details,
     * and button to search for long details
     */
    private void southPanel() {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1,3));
        searchPanel.add(new JLabel("Enter Competitor Number"));
        searchField = new JTextField(5);
        searchPanel.add(searchField);

        searchShortDetails = new JButton("Search Short Details");
        searchPanel.add(searchShortDetails);
        searchShortDetails.addActionListener(this);

        searchFullDetails = new JButton("Search Full Details");
        searchPanel.add(searchFullDetails);
        searchFullDetails.addActionListener(this);

        removeCompetitor = new JButton("Remove This Competitor");
        searchPanel.add(removeCompetitor);
        removeCompetitor.addActionListener(this);


        //Set up the area where the results will be displayed.
        result = new JTextField(30);
        result.setEditable(false);

        //set up south panel containing 2 previous areas
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2,1));
        southPanel.add(searchPanel);
        southPanel.add(result);

        //add south panel to the content pane
        this.add(southPanel, BorderLayout.SOUTH);
    }


    /**
     * perform actions when a button is pressed
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchFullDetails) {
            searchFullDetails();
        }
        else if (e.getSource() == searchShortDetails) {
            searchShortDetails();
        }
        else if (e.getSource() == removeCompetitor) {
            removeThisCompetitor();
        }
        else if (e.getSource() == showListById) {
            displayList.setText(competitorList.getCompetitorsTable());
        }
        else if (e.getSource() == showListByName) {
            displayList.setText(competitorList.getCompetitorsTableByName());
        }
        else if (e.getSource() == showListByScore) {
            displayList.setText(competitorList.getCompetitorsTableByHighestScore());
        }
        else if (e.getSource() == showListByAmateur) {
            displayList.setText(competitorList.getCompetitorsTableByAmateurs());
        }
        else if (e.getSource() == showListBySemipro) {
            displayList.setText(competitorList.getCompetitorsTableBySemipros());
        }
        else if (e.getSource() == showListByPro) {
            displayList.setText(competitorList.getCompetitorsTableByPros());
        }
        else if (e.getSource() == stage4Report) {
            displayList.setText("Printing text report. Will appear when this program is closed...");
            competitorList.printStage4TextReport();
        }
        else if (e.getSource() == close) {
            JOptionPane.showMessageDialog(this,
                    "Closing down ..");
            System.exit(0);
        }
    }


    /**
     * populate the result field with full details of a competitor,
     * depending on the competitor number typed in
     */
    private void searchFullDetails() {
        //get search text and search staff list
        //setting result text
        String searchString = searchField.getText().trim();
        if(searchString.length() > 0) {
            HSSFootballer footballer = competitorList.getFootballer(Integer.parseInt(searchString));
            if (footballer != null ) {
                result.setText(footballer.getFullDetails());
            }
            else
                result.setText("not found");
        }
        else
            result.setText("no text entered");
    }


    /**
     * populate the result field with short details of a competitor,
     * depending on the competitor number typed in
     */
    private void searchShortDetails() {
        //get search text and search staff list
        //setting result text
        String searchString = searchField.getText().trim();
        if(searchString.length() > 0) {
            HSSFootballer footballer = competitorList.getFootballer(Integer.parseInt(searchString));
            if (footballer != null ) {
                result.setText(footballer.getShortDetails());
            }
            else
                result.setText("not found");
        }
        else
            result.setText("no text entered");
    }


    /**
     * Listener for remove competitor button, opens JOptionPane
     * to ask for staff ID so it can check security clearance.
     * There are 2 Staff objects added to competitorList.staffList,
     * an offial and a standard member of staff. The official's ID is 1, standard is 2.
     * The provided Competitor (by the id) will only be removed if the ID for the offical is
     * provided, for realism it is kept private by the actual GUI so you will need to consult the
     * README file that I will submit, and also this method!!!!
     */
    private void removeThisCompetitor() {
        String searchString = searchField.getText().trim();

        if (searchString.length() > 0) {
            HSSFootballer footballer = competitorList.getFootballer(Integer.parseInt(searchString));

            if (footballer != null) {

                // open the JOptionPane where the staff id is typed, create the staff
                String staffId = JOptionPane.showInputDialog("Staff ID Number: ");
                Staff staff = competitorList.getStaffMember(Integer.parseInt(staffId));

                // remove competitor if provided staff id has proper security clearance
                if (staff != null && competitorList.isOfficalStaff(Integer.parseInt(staffId))) {
                    competitorList.staffRemoveCompetitor(staff.getId(), footballer.getCompetitorNumber());
                    result.setText("removed competitor " + footballer.getCompetitorNumber());
                }

                // reject if the staff id security is too low
                else if (staff != null && !(competitorList.isOfficalStaff(staff.getId()))) {
                    result.setText("Cannot remove competitor, security clearance too low.");
                }
                else {
                    result.setText("not found");
                }
            }
        }
        else {
            result.setText("no text entered");
        }
    }

}
