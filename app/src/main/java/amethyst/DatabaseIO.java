package amethyst;

import javax.swing.*;
import javax.swing.table.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

public class DatabaseIO {
    // Establish connection with database, select all public tables and put them into tablesComboBox
    public static DefaultListModel<String> openDatabase(String filename) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:" + filename);
        DefaultListModel<String> tablesList = null;
        if (con.isValid(5)) {
            tablesList = new DefaultListModel<String>();

            // Select all public tables
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table' AND name NOT LIKE 'sqlite_%';");

            while (rs.next()) {
                tablesList.addElement(rs.getString("name"));
            }
        }
        return tablesList;
    }

    // Establish connection with database, Execute query and put results into dataTable
    public static TableModel executeQuery(String filename, String query) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:sqlite:" + filename);
        TableModel resultsTable = null;
        if (con.isValid(5)) {
            Vector<String> columns = new Vector<>();
            Vector<Vector<Object>> data = new Vector<>();

            Statement stmt = con.createStatement();

            ResultSet rs = null;
            boolean isResultSet = stmt.execute(query);
            // Query returns a ResultSet object (e.g. SELECT)
            if (isResultSet) {
                rs = stmt.getResultSet();

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

                resultsTable = new DefaultTableModel(data, columns);
            } 
        }
        return resultsTable;
    }
}
