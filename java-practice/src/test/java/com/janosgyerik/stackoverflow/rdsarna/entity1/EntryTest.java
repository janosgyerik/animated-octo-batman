package com.janosgyerik.stackoverflow.rdsarna.entity1;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Entry {

    /**
     * Represents the JCombobox data which can be larger than, smaller than
     * or equal to the population given in the field.
     */
    public enum Population {
        LARGER,
        SMALLER,
        EQUAL
    }

    /**
     * Represents the JCombobox data from the GUI, which can be exact match
     * or partial match.
     */
    public enum Match {
        EXACT,
        PARTIAL
    }

    private String metropolis;
    private String continent;
    private String population;
    private Population populationCriteria;
    private Match matchCriteria;
    //  private static final String TABLE_NAME = "metropolises";


    /**
     * Construct an Entry/criteria to be searched/queried from
     * the database. Any or all of the String parameters can be empty or
     * null if they are not to be included in the query condition.
     * @param metropolis name of the metropolis to be included in the query,
     * can be left empty
     * @param continent name of the continent to be included in the query,
     * can be left empty
     * @param population population to be included in the query, can be left empty
     * @param populationCriteria larger than, smaller than or equal to the given
     * population parameter. Ignored if population field is empty
     */
    public Entry(String metropolis, String continent,
                 String population, Population populationCriteria, Match matchCriteria) {
        this.metropolis = metropolis;
        this.continent = continent;
        this.population = population;
        this.populationCriteria = populationCriteria;
        this.matchCriteria = matchCriteria;
    }


    /**
     * An entry that is constructed when it has to be added to the database.
     * All the parameter cannot be null.
     */
    public Entry(String metropolis, String continent, String population) {
        this(metropolis, continent, population, Population.EQUAL, Match.EXACT);
    }


    /**
     * Generates and returns an SQL query statement as a String. Takes into
     * account the state of the Entry object. All the properties of the object
     * that have been supplied while constructing the Entry are used to generate the
     * statement.<br>
     * <br>
     * For example, if the Entry was created with:<br>
     * Entry e = new Entry("", "", "200000",  Population.LARGER, Match.EXACT);<br>
     * then the call<br>
     * e.getQueryStatement(metropolises)<br>
     * will return<br>
     * "SELECT * FROM metropolises WHERE population > 200000;"<br>
     *
     * @param tableName Name of the table from which data is to be queried
     * @return SQL query statement according to the state of this object
     */
    public String getQueryStatement(String tableName) {
        StringBuilder result = new StringBuilder("SELECT * FROM " + tableName);
        if (areAllFieldsEmpty(this)) return result.toString();
        result.append(" WHERE");

        generateQuery(this, result);
        result.append(";");
        return result.toString();
    }

    /**
     * Generates and returns an SQL insertion statement as a String. Takes into
     * account the state of the Entry object. All the properties of the object
     * that have been supplied while constructing the Entry are used to generate the
     * statement. <br>
     * <br>
     * If any of the fields are empty then null is returned.<br>
     * <br>
     * For example, if the entry was created with:<br>
     * Entry e = new Entry("Mumbai", "Asia", "200000");<br>
     * then the call<br>
     * e.getInsertStatement(metropolises)<br>
     * will return<br>
     * "INSERT INTO metropolises VALUES("Mumbai", "Asia", "200000");"<br>
     * <br>
     * @param tableName Name of the table into which data is to be inserted
     * @return SQL insert statement according to the state of this object OR
     * null if any of the fields are empty
     */
    public String getInsertStatement(String tableName) {
        if (anyFieldEmpty(this)) return null;
        String result = "INSERT INTO " + tableName + " VALUES("
                + "\"" + this.metropolis + "\","
                + " \"" + this.continent + "\","
                + " " + this.population + ");";
        return result;
    }


    /* Returns true iff any of the fields of the entry is empty */
    private boolean anyFieldEmpty(Entry entry) {
        return entry.metropolis.isEmpty() || entry.continent.isEmpty()
                || entry.population.isEmpty();
    }

    /* Returns iff all fields of the given entry are empty Strings */
    private boolean areAllFieldsEmpty(Entry entry) {
        return entry.metropolis.isEmpty() && entry.continent.isEmpty()
                && entry.population.isEmpty();
    }


    /* Generates a query SQL statement and appends it to the given result
     * based on the properties of the given entry */
    private void generateQuery(Entry entry, StringBuilder result) {
        String populationOperator = getPopulationOperator(entry.populationCriteria);

        if (!entry.metropolis.isEmpty()) {
            result.append(entry.matchCriteria == Match.EXACT ?
                    " metropolis = \"" + entry.metropolis + "\"" :
                    " metropolis LIKE \"%" + entry.metropolis + "%\"");
            if (!entry.continent.isEmpty() || !entry.population.isEmpty())
                result.append(" AND");
        }
        if (!entry.continent.isEmpty()) {
            result.append(entry.matchCriteria == Match.EXACT ?
                    " continent = \"" + entry.continent + "\"" :
                    " continent LIKE \"%" + entry.continent + "%\"");
            if (!entry.population.isEmpty())
                result.append(" AND");
        }
        result.append((entry.population.isEmpty()) ?
                "" : " population " + populationOperator + " " + entry.population);
    }

    /**
     * Returns true iff the given object is an Entry with all properties
     * equal to this Entry object's properties.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Entry)) return false;
        Entry that = (Entry) o;

        return this.metropolis.equals(that.metropolis) &&
                this.continent.equals(that.continent) &&
                this.population.equals(that.population) &&
                this.populationCriteria.equals(that.populationCriteria) &&
                this.matchCriteria.equals(that.matchCriteria);
    }


    /* Returns the operator equivalent to the populationCriteria given */
    private String getPopulationOperator(Population populationCriteria) {
        switch (populationCriteria) {
            case LARGER: return ">";
            case SMALLER: return "<";
            default: return "=";
        }
    }

}

class MyDBInfo {

    public static final String MYSQL_USERNAME = "";
    public static final String MYSQL_PASSWORD = "";
    public static final String MYSQL_DATABASE_SERVER = "";
    public static final String MYSQL_DATABASE_NAME = "";
}

class DataClass {

    private static final String account = MyDBInfo.MYSQL_USERNAME;
    private static final String password = MyDBInfo.MYSQL_PASSWORD;
    private static final String server = MyDBInfo.MYSQL_DATABASE_SERVER;
    private static final String database = MyDBInfo.MYSQL_DATABASE_NAME;
    private static final String table = "metropolises";

    private static final String DEFAULT_STATEMENT = "SELECT * FROM " + table;

    private Connection connection;
    private Statement stmt;
    private ResultSet rs;
    private ResultSetMetaData metadata;
    private final List<String> columnNames;


    /**
     * Constructs a DataClass object and connects to the back end
     * database.
     */
    public DataClass() {
        columnNames = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + server, account, password);
            stmt = connection.createStatement();
            stmt.executeQuery("USE " + database);

            rs = stmt.executeQuery(DEFAULT_STATEMENT);
            metadata = rs.getMetaData();
            int count = metadata.getColumnCount();
            for (int i = 1; i <= count; i++) {
                columnNames.add(metadata.getColumnLabel(i));
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class Exception");
            e.getStackTrace();
        } catch (SQLException se) {
            System.out.println("SQL Exception");
            se.getStackTrace();
        }
    }


    /**
     * Retrieves data from the database based on the given Entry object.
     * Uses the properties of Entry object to determine the conditions for
     * the retrieval query. The data retrieved is stored in the given List
     * "data". The given entry should not be null.
     * @param entry The object that holds information about conditions to be used to
     * query the database
     */
    public void retrieveData(Entry entry, List<List<String>> data) {
        /* Clear the existing data to get new data based on the current query */
        data.clear();

        try {
            rs = stmt.executeQuery(entry.getQueryStatement(table));

            convertResultSetToList(rs, data);
        } catch (SQLException se) {
            System.out.println("SQL Exception");
            se.getStackTrace();
        }
    }


    /**
     * Inserts a row into the table in the database based on the given Entry
     * object. The data inserted is based on the properties of the Entry object.
     * The given List of rows "data" is cleared and the newly inserted row is stored
     * in it.
     * a List of Strings.
     * @param entry The object based on which a new row is inserted
     * @return returns the row index where the new row is added in the table.
     * Or -1 if row is not inserted due to invalid information given
     */
    public int insertData(Entry entry, List<List<String>> data) {
        int rowIndex = -1;

        /* Clear the existing data to get new data based on the current query */
        data.clear();
        /* List to store the single data in the row added */
        List<String> rowList = new ArrayList<>();

        try {
            String insertStatement = entry.getInsertStatement(table);

            if (insertStatement == null) return rowIndex;

            stmt.executeUpdate(insertStatement);
            rs = stmt.executeQuery(DEFAULT_STATEMENT);
            rs.last();

            rowIndex = rs.getRow();

            for (int i = 1; i <= metadata.getColumnCount(); i++)
                rowList.add(rs.getString(i));

            data.add(rowList);

            rs.first();
        } catch (SQLException se) {
            System.out.println("Insert Error");
            se.getStackTrace();
        }

        return rowIndex;
    }


    /**
     * Returns the column names from the database as a List of Strings
     * @return names of the column
     */
    public List<String> retrieveColumnNames() {
        return columnNames;
    }

    /* Converts the given ResultSet to a List of rows and the given list points to it.
     * The given list should ideally be empty before this method is called. */
    private void convertResultSetToList(ResultSet rs, List<List<String>> list)
            throws SQLException {
        while (rs.next()) {
            List<String> row = new ArrayList<>();
            for (int i = 1; i <= metadata.getColumnCount(); i++)
                row.add(rs.getString(i));
            list.add(row);
        }
    }

}

class DBTableModel extends AbstractTableModel {

    private final List<String> colNames;  // defines the number of cols
    private final List<List<String>> data;    // one List for each row
    final DataClass dataFromDB;


    public DBTableModel() {
        dataFromDB = new DataClass();
        colNames = dataFromDB.retrieveColumnNames();
        data = new ArrayList<>();
    }


    /*
     Basic getXXX methods required by a class implementing TableModel
    */

    /**
     * Returns the name of the column at columnIndex. This is used to
     * initialize the table's column header name. Note: this name does
     * not need to be unique; two columns in a table can have the same name.
     * @return a string containing the default name of column
     */
    public String getColumnName(int col) {
        return colNames.get(col);
    }

    /**
     * Returns the number of columns in the model. A JTable uses
     * this method to determine how many columns it should create
     * and display by default.
     * @return the number of columns in the model
     */
    public int getColumnCount() {
        return colNames.size();
    }

    /**
     * Returns the number of rows in the model. A JTable uses this
     * method to determine how many rows it should display.
     * @return the number of rows in the model
     */
    public int getRowCount() {
        return(data.size());
    }

    /**
     * Returns the value for the cell at col and row.
     * @param row the row whose value is to be queried
     * @param col the column whose value is to be queried
     * @return the value Object at the specified cell
     */
    public Object getValueAt(int row, int col) {
        List rowList = data.get(row);
        Object result = null;
        if (col<rowList.size()) {
            result = rowList.get(col);
        }
        return(result);
    }


    /**
     * Returns true if a cell should be editable in the table otherwise false.
     * @param row the row whose value to be queried
     * @param col the column whose value to be queried
     * @return true if the cell is editable
     */
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    /**
     * Retrieves new data from the back end database by calling on the
     * DataClass and updates the data in "this" object. The data retrieved
     * is based on the properties of the given Entry object.
     * @param entry The properties of this entry object relay what data needs
     * to be retrieved
     */
    public void getNewDataFromDB(Entry entry) {
        dataFromDB.retrieveData(entry, data);
        fireTableDataChanged();
    }


    /**
     * Adds a new row to the back end database by calling on the DataClass.
     * The data added in the new row is based on the properties of the given
     * Entry object.
     * @param entry The properties of this entry object relay what data needs
     * to be stored in the added row.
     * @return The row index of the table in the back end DB where the new row
     * is added.
     */
    public int addRow(Entry entry) {
        int rowIndex = dataFromDB.insertData(entry, data);
        fireTableDataChanged();
        return rowIndex;
    }

}

class TableFrame extends JFrame {

    private DBTableModel model;
    private JTable table;

    private JButton addBttn;
    private JButton searchBttn;
    private JLabel metropolisLabel;
    private JTextField metropolisField;
    private JLabel continentLabel;
    private JTextField continentField;
    private JLabel populationLabel;
    private JTextField populationField;
    private JComboBox populationCriteria;
    private JComboBox matchCriteria;

    final static String POPULATION_LARGER = "Population Larger Than";
    final static String POPULATION_SMALLER = "Population Smaller Than";
    final static String POPULATION_EQUAL = "Population Equal To";
    final static String MATCH_EXACT = "Exact Match";
    final static String MATCH_PARTIAL = "Partial Match";

    public TableFrame() {
        super("Metropolis Viewer");

        model = new DBTableModel();

        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(300, 400));
        add(scrollpane, BorderLayout.CENTER);

        /* Fields and labels to be added to the top part of the window */
        JPanel topPanel = new JPanel();
        add(topPanel, BorderLayout.NORTH);

        metropolisLabel = new JLabel("Metropolis:  ");
        metropolisField = new JTextField(17);
        continentLabel = new JLabel("Continent:  ");
        continentField = new JTextField(17);
        populationLabel = new JLabel("Population:  ");
        populationField = new JTextField(17);

        topPanel.add(metropolisLabel);
        topPanel.add(metropolisField);
        topPanel.add(continentLabel);
        topPanel.add(continentField);
        topPanel.add(populationLabel);
        topPanel.add(populationField);

        /* Buttons and ComboBoxes on the east side of the window */
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        add(eastPanel, BorderLayout.EAST);

        addBttn = new JButton("Add");
        searchBttn = new JButton("Search");
        eastPanel.add(addBttn);
        eastPanel.add(new JLabel(" "));
        eastPanel.add(searchBttn);
        eastPanel.add(new JLabel(" "));

        /* A panel for the ComboBoxes */
        JPanel comboBoxPanel = new JPanel();
        String[] populationOptions = new String[] {POPULATION_LARGER, POPULATION_SMALLER, POPULATION_EQUAL};
        populationCriteria = new JComboBox<>(populationOptions);
        populationCriteria.setPreferredSize(new Dimension(190, 28));

        String[] matchOptions = new String[] {MATCH_EXACT, MATCH_PARTIAL};
        matchCriteria = new JComboBox<>(matchOptions);
        matchCriteria.setPreferredSize(new Dimension(190, 28));

        comboBoxPanel.setBorder(new TitledBorder("Search Options"));
        comboBoxPanel.add(populationCriteria);
        comboBoxPanel.add(matchCriteria);
        comboBoxPanel.setPreferredSize(new Dimension(200, 80));
        eastPanel.add(comboBoxPanel);

        addBttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Entry entry = getNewEntry();
                model.addRow(entry);
            }
        });

        searchBttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Entry entry = getNewEntry();
                model.getNewDataFromDB(entry);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    protected Entry getNewEntry() {
        String metropolis = metropolisField.getText();
        String continent = continentField.getText();
        String population = populationField.getText();
        Entry.Population populationCrit = getPopulationCriteria(populationCriteria.getSelectedItem());
        Entry.Match matchCrit = matchCriteria.getSelectedItem().equals(MATCH_EXACT) ? Entry.Match.EXACT : Entry.Match.PARTIAL;
        return new Entry(metropolis, continent, population, populationCrit, matchCrit);
    }

    private Entry.Population getPopulationCriteria(Object selectedItem) {
        switch ((String)selectedItem) {
            case POPULATION_LARGER: return Entry.Population.LARGER;
            case POPULATION_SMALLER: return Entry.Population.SMALLER;
            case POPULATION_EQUAL: return Entry.Population.EQUAL;
            default: return null;
        }
    }


    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new TableFrame();
    }

}

public class EntryTest {
}
