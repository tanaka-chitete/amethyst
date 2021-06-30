package amethyst.view;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

import amethyst.model.ResultTableModel;

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
        setSize(1_080, 720);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        // Configure filename text field
        filenameTextField = new JTextField();
        filenameTextField.setName("FileNameTextField");
        filenameTextField.setBounds(10, 10, 955, 30);
        filenameTextField.setFont(Fonts.ARCHIVO);
        add(filenameTextField);

        // Configure open database button
        openDatabaseButton = new JButton();
        openDatabaseButton.setName("OpenFileButton");
        openDatabaseButton.setText("OPEN");
        openDatabaseButton.setBounds(970, 10, 100, 30);
        openDatabaseButton.setFont(Fonts.ARCHIVO);
        add(openDatabaseButton);

        // Configure tables dropdown menu
        tablesComboBox = new JComboBox<String>();
        tablesComboBox.setName("TablesComboBox");
        tablesComboBox.setBounds(10, 45, 1_060, 30);
        tablesComboBox.setFont(Fonts.ARCHIVO);
        add(tablesComboBox);

        // Configure query text area
        queryTextArea = new JTextArea();
        queryTextArea.setName("QueryTextArea");
        queryTextArea.setBounds(15, 80, 945, 270);
        queryTextArea.setFont(Fonts.DM_MONO_REG);
        queryTextArea.setEnabled(false);
        add(queryTextArea);

        // Configure execute query button
        executeQueryButton = new JButton();
        executeQueryButton.setName("ExecuteQueryButton");
        executeQueryButton.setText("EXECUTE");
        executeQueryButton.setBounds(970, 80, 100, 270);
        executeQueryButton.setFont(Fonts.ARCHIVO);
        executeQueryButton.setEnabled(false);
        add(executeQueryButton);

        // Configure data table and associated scroll pane
        resultTable = new JTable(new ResultTableModel());
        resultTable.setName("Table");
        resultTable.setFont(Fonts.DM_MONO_SML);
        resultTable.getTableHeader().setFont(Fonts.DM_MONO_SML);
        JScrollPane scrollPane = new JScrollPane(resultTable, 
                                                 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                                                 JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane.setBounds(15, 360, 1_050, 320);
        add(scrollPane);

        // Configure file error dialog and ok button
        fileErrorDialog = new JDialog(this, "File Error", true);
        fileErrorDialog.setSize(200, 100);
        fileErrorDialog.setLocationRelativeTo(null);
        fileErrorDialog.setLayout(new FlowLayout());
        JLabel fileErrorLabel = new JLabel("Invalid filename provided");
        fileErrorLabel.setFont(Fonts.ARCHIVO);
        fileErrorDialog.add(fileErrorLabel);
        fileErrorOkButton = new JButton("OK");
        fileErrorOkButton.setFont(Fonts.ARCHIVO);
        fileErrorDialog.add(fileErrorOkButton);

        // Configure syntax error dialog and ok button
        syntaxErrorDialog = new JDialog(this, "Syntax Error", true);
        syntaxErrorDialog.setSize(200, 100);
        syntaxErrorDialog.setLocationRelativeTo(null);
        syntaxErrorDialog.setLayout(new FlowLayout());
        JLabel syntaxErrorLabel = new JLabel("Invalid query entered");
        syntaxErrorLabel.setFont(Fonts.ARCHIVO);
        syntaxErrorDialog.add(syntaxErrorLabel);
        syntaxErrorOkButton = new JButton("OK");
        syntaxErrorOkButton.setFont(Fonts.ARCHIVO);
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
