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
        setLayout(new BorderLayout());
        // setResizable(false);
        setLocationRelativeTo(null);

        // // Configure filename text field
        filenameTextField = new JTextField();
        // filenameTextField.setName("FileNameTextField");
        // filenameTextField.setBounds(10, 10, 955, 30);
        // filenameTextField.setFont(Fonts.ARCHIVO);
        // add(filenameTextField);


        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);

        JMenu aboutMenu = new JMenu("About");
        JMenuItem versionMenuItem = new JMenuItem("Version");
        JMenuItem developerMenuItem = new JMenuItem("Development");
        aboutMenu.add(versionMenuItem);
        aboutMenu.add(developerMenuItem);        

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        JMenuItem padding = new JMenuItem();
        padding.setEnabled(false);
        menuBar.add(aboutMenu);
        menuBar.add(padding);
        // menuBar.add(aboutMenu);




        JToolBar toolBar = new JToolBar();
        JButton executeBtn = new JButton("Execute");
        toolBar.add(executeBtn);

        JPanel bars = new JPanel();
        bars.setLayout(new BoxLayout(bars, BoxLayout.PAGE_AXIS));
        menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        bars.add(menuBar);
        toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        bars.add(toolBar);

        add(bars, BorderLayout.NORTH);




        // // Configure open database button
        openDatabaseButton = new JButton();
        // openDatabaseButton.setName("OpenFileButton");
        // openDatabaseButton.setText("OPEN");
        // openDatabaseButton.setBounds(970, 10, 100, 30);
        // openDatabaseButton.setFont(Fonts.ARCHIVO_EXP);
        // add(openDatabaseButton);


        JList<String> tablesList = new JList<>();
        add(tablesList, BorderLayout.WEST);

        // Configure tables dropdown menu
        tablesComboBox = new JComboBox<String>();
        // tablesComboBox.setName("TablesComboBox");
        // tablesComboBox.setBounds(10, 45, 1_060, 30);
        // tablesComboBox.setFont(Fonts.ARCHIVO);
        // add(tablesComboBox);

        // Configure query text area
        queryTextArea = new JTextArea();
        queryTextArea.setTabSize(4);
        queryTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        // queryTextArea.setFont(Fonts.DM_MONO_REG);
        // queryTextArea.setEnabled(false);
        // add(queryTextArea, BorderLayout.CENTER);

        
        // add(northPane, BorderLayout.CENTER);

        // Configure execute query button
        executeQueryButton = new JButton();
        // executeQueryButton.setName("ExecuteQueryButton");
        // executeQueryButton.setText("EXECUTE");
        // executeQueryButton.setBounds(970, 80, 100, 270);
        // executeQueryButton.setFont(Fonts.ARCHIVO_EXP);
        // executeQueryButton.setEnabled(false);
        // add(executeQueryButton);

        // // Configure data table and associated scroll pane
        resultTable = new JTable(new ResultTableModel());
        resultTable.setName("Table");
        // resultTable.setFont(Fonts.DM_MONO_SML);
        // resultTable.getTableHeader().setFont(Fonts.DM_MONO_SML);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        scrollPane.setBounds(15, 360, 1_050, 320);
        // add(scrollPane, BorderLayout.SOUTH);

        JSplitPane northPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tablesList, queryTextArea);
        JSplitPane southPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, northPane, resultTable);
        add(southPane, BorderLayout.CENTER);

        // Configure file error dialog and ok button
        fileErrorDialog = new JDialog(this, "File Error", true);
        // fileErrorDialog.setSize(200, 100);
        // fileErrorDialog.setLocationRelativeTo(null);
        // fileErrorDialog.setLayout(new FlowLayout());
        // JLabel fileErrorLabel = new JLabel("Invalid filename provided");
        // fileErrorLabel.setFont(Fonts.ARCHIVO);
        // fileErrorDialog.add(fileErrorLabel);
        fileErrorOkButton = new JButton("OK");
        // fileErrorOkButton.setFont(Fonts.ARCHIVO_EXP);
        // fileErrorDialog.add(fileErrorOkButton);

        // Configure syntax error dialog and ok button
        syntaxErrorDialog = new JDialog(this, "Syntax Error", true);
        // syntaxErrorDialog.setSize(200, 100);
        // syntaxErrorDialog.setLocationRelativeTo(null);
        // syntaxErrorDialog.setLayout(new FlowLayout());
        // JLabel syntaxErrorLabel = new JLabel("Invalid query entered");
        // syntaxErrorLabel.setFont(Fonts.ARCHIVO);
        // syntaxErrorDialog.add(syntaxErrorLabel);
        syntaxErrorOkButton = new JButton("OK");
        // syntaxErrorOkButton.setFont(Fonts.ARCHIVO_EXP);
        // syntaxErrorDialog.add(syntaxErrorOkButton);

        // Display GUI only once all components have been added to it
        setVisible(true);

        // Attempting to set the divider location before the components are visible doesn't work
        northPane.setDividerLocation(0.1);
        // Attempting to set the divider location before the components are visible doesn't work
        southPane.setDividerLocation(0.5);
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
