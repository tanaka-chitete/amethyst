package amethyst.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.xml.transform.Result;
import java.awt.*;
import java.sql.*;

public class Amethyst extends JFrame {
    private final JTextComponent    filenameTextField;
    private final JButton           openDatabaseButton;
    private final JComboBox<String> tablesComboBox;
    private final JTextArea         queryTextArea;
    private final JButton           executeQueryButton;
    private final JTable            resultTable;
    private final JDialog           fileErrorDialog;
    private final JButton           fileErrorOkButton;
    private final JDialog           syntaxErrorDialog;
    private final JButton           syntaxErrorOkButton;
    private       String            url;

    public Amethyst() {
        // Construct frame
        super("Amethyst");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        // Configure filename text field
        filenameTextField = new JTextField();
        filenameTextField.setName("FileNameTextField");
        filenameTextField.setBounds(10, 10, 150, 50);
        add(filenameTextField);

        // Configure open database button
        openDatabaseButton = new JButton();
        openDatabaseButton.setName("OpenFileButton");
        openDatabaseButton.setText("Open");
        openDatabaseButton.setBounds(600, 10, 50, 50);
        add(openDatabaseButton);

        // Configure tables dropdown menu
        tablesComboBox = new JComboBox<String>();
        tablesComboBox.setName("TablesComboBox");
        tablesComboBox.setBounds(10, 35, 150, 50);
        add(tablesComboBox);

        // Configure query text area
        queryTextArea = new JTextArea();
        queryTextArea.setName("QueryTextArea");
        queryTextArea.setBounds(10, 60, 200, 200);
        queryTextArea.setEnabled(false);
        add(queryTextArea);

        // Configure execute query button
        executeQueryButton = new JButton();
        executeQueryButton.setName("ExecuteQueryButton");
        executeQueryButton.setText("Execute");
        executeQueryButton.setBounds(600, 60, 60, 75);
        executeQueryButton.setEnabled(false);
        add(executeQueryButton);

        // Configure data table
        resultTable = new JTable(new DefaultTableModel());
        resultTable.setName("Table");
        resultTable.setBounds(0, 500, 350, 350);
        add(resultTable);

        // Configure file error dialog and ok button
        fileErrorDialog = new JDialog(this, "File Error", true);
        fileErrorDialog.setLayout(new FlowLayout());
        fileErrorOkButton = new JButton("OK");
        fileErrorDialog.add(new JLabel("Invalid filename"));
        fileErrorDialog.setSize(200, 100);
        fileErrorDialog.add(fileErrorOkButton);

        // Configure syntax error dialog and ok button
        syntaxErrorDialog = new JDialog(this, "Syntax Error", true);
        syntaxErrorDialog.setLayout(new FlowLayout());
        syntaxErrorOkButton = new JButton("OK");
        syntaxErrorDialog.add(new JLabel("Invalid query"));
        syntaxErrorDialog.setSize(200, 100);
        syntaxErrorDialog.add(syntaxErrorOkButton);

        // Display GUI only once all components have been added to it
        setVisible(true);
    }

    public JTextComponent getFilenameTextField() {
        return filenameTextField;
    }

    public JButton getOpenDatabaseButton() {
        return openDatabaseButton;
    }

    public JComboBox<String> getTablesComboBox() {
        return tablesComboBox;
    }

    public JTextArea getQueryTextArea() {
        return queryTextArea;
    }

    public JButton getExecuteQueryButton() {
        return executeQueryButton;
    }

    public JTable getResultTable() {
        return resultTable;
    }

    public JDialog getFileErrorDialog() {
        return fileErrorDialog;
    }

    public JButton getFileErrorOkButton() {
        return fileErrorOkButton;
    }

    public JDialog getSyntaxErrorDialog() {
        return syntaxErrorDialog;
    }

    public JButton getSyntaxErrorOkButton() {
        return syntaxErrorOkButton;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
