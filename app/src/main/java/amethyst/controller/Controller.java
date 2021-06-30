package amethyst.controller;

import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import amethyst.view.Amethyst;
import amethyst.model.ResultTableModel;

public class Controller {
    private final Amethyst view;

    public Controller(Amethyst view) {
        this.view = view;
        view.getOpenDatabaseButton().addActionListener(e -> openDatabase());
        view.getTablesComboBox().addActionListener(e -> setDefaultQuery());
        view.getExecuteQueryButton().addActionListener(e -> executeQuery());
        view.getFileErrorOkButton().addActionListener(e -> view.getFileErrorDialog().setVisible(false));
        view.getSyntaxErrorOkButton().addActionListener(e -> view.getSyntaxErrorDialog().setVisible(false));
    } 

    // Add default query to queryTextArea for selected table
    public void setDefaultQuery() {
        String query = "SELECT * FROM " + view.getTablesComboBox().getSelectedItem().toString() + ";";
        view.getQueryTextArea().setText(query);
    }

    // Establish connection with database, select all public tables and put them into tablesComboBox
    public void openDatabase() {
        String filename = view.getFilenameTextField().getText();
        if (!(new File(filename).length() > 0)) {
            view.getQueryTextArea().setEnabled(false);
            view.getExecuteQueryButton().setEnabled(false);
            view.getFileErrorDialog().setVisible(true);
        } else {
            String url = "jdbc:sqlite:" + filename;
            try (Connection con = DriverManager.getConnection(url)) {
                if (con.isValid(5)) {
                    // Save URL for later queries
                    view.setUrl(url);

                    // Select all public tables
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table' AND name NOT LIKE 'sqlite_%';");

                    // Put public tables into tablesComboBox
                    JComboBox<String> tablesComboBox = view.getTablesComboBox();
                    DefaultComboBoxModel<String> tables = new DefaultComboBoxModel<>();
                    while (rs.next()) {
                        tables.addElement(rs.getString("name"));
                    }
                    tablesComboBox.setModel(tables);

                    // Add default query to queryTextArea for selected table
                    String query = "SELECT * FROM " + tablesComboBox.getSelectedItem().toString() + ";";
                    view.getQueryTextArea().setText(query);
                    view.getQueryTextArea().setEnabled(true);
                    view.getExecuteQueryButton().setEnabled(true);
                }
            }
            catch (SQLException exc) { 
                view.getQueryTextArea().setEnabled(false);
                view.getExecuteQueryButton().setEnabled(false);
                view.getFileErrorDialog().setVisible(true);
            }
        }
    }

    // Establish connection with database, Execute query and put results into dataTable
    public void executeQuery() {
        Vector<String> columns = new Vector<>();
        Vector<Vector<Object>> data = new Vector<>();
        try (Connection con = DriverManager.getConnection(view.getUrl())){
            if (con.isValid(5)) {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(view.getQueryTextArea().getText());

                // Get names of columns
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    columns.add(rsmd.getColumnName(i));
                }

                // Get each row of data
                while (rs.next()) {
                    Vector<Object> datum = new Vector<>();
                    for (int i = 1; i <= columnCount; i++) {
                        datum.add(rs.getObject(i));
                    }
                    data.add(datum);
                }
            }
        }
        catch (SQLException exc) { 
            view.getSyntaxErrorDialog().setVisible(true);
        }

        view.getResultTable().setModel(new ResultTableModel(data, columns));
    }
}
