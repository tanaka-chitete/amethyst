package amethyst;

import javax.swing.*;
import javax.swing.text.*;
import javax.swing.table.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.sql.SQLException;
import com.formdev.flatlaf.FlatDarkLaf;
import java.io.File;

public class App extends JFrame {
    private String filename;

    public static void main(String[] args) {
        // FlatDarkLaf.setup();
        new App();
    }

    public App() {
        // Construct frame
        super("Amethyst");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1_080, 720);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Construct file menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New"); // TODO: Implement
        JMenuItem openMenuItem = new JMenuItem("Open");
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);

        // Construct about menu
        JMenu aboutMenu = new JMenu("About");
        JMenuItem versionMenuItem = new JMenuItem("Version");
        JMenuItem developerMenuItem = new JMenuItem("Development");
        aboutMenu.add(versionMenuItem);
        aboutMenu.add(developerMenuItem);        

        // Construct menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        JMenuItem padding = new JMenuItem();
        padding.setEnabled(false);
        menuBar.add(aboutMenu);
        menuBar.add(padding);

        // Construct tool bar
        JToolBar toolBar = new JToolBar();
        JButton executeBtn = new JButton("Execute");

        executeBtn.setEnabled(false);
        toolBar.add(executeBtn);

        // Stack menu bar on top of tool bar
        JPanel bars = new JPanel();
        bars.setLayout(new BoxLayout(bars, BoxLayout.PAGE_AXIS));
        menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        bars.add(menuBar);
        toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        bars.add(toolBar);

        // Add bars to the top of the frame
        add(bars, BorderLayout.NORTH);

        // Construct tables list
        JList<String> tablesList = new JList<>();
        tablesList.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Add tables list to the left of the frame
        add(tablesList, BorderLayout.WEST);

        // Configure query area
        JTextArea queryArea = new JTextArea();
        queryArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        queryArea.setTabSize(4);

        // Configure data table and associated scroll pane
        JTable resultsTable = new JTable(new DefaultTableModel());
        resultsTable.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(resultsTable);
        resultsTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);

        // Construct split pane with tables list and query area
        JSplitPane northPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tablesList, queryArea);
        // Construct split pane with above split pane and result table
        JSplitPane southPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, northPane, resultsTable);

        // Add above split pane to the center of the screen
        add(southPane, BorderLayout.CENTER);

        openMenuItem.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter dbFiles = new FileNameExtensionFilter("DB files", "db");
            fileChooser.setFileFilter(dbFiles);
            int option = fileChooser.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                try {
                    File database = fileChooser.getSelectedFile();
                    ListModel<String> tables = DatabaseIO.openDatabase(database.toString());
                    queryArea.setEnabled(false);
                    executeBtn.setEnabled(false);
                    tablesList.setModel(tables);
                    filename = database.toString();
                } catch (SQLException e) {
                    System.out.println("ERROR"); // TODO: Error-handle
                    e.printStackTrace();
                }
            }
        });

        executeBtn.addActionListener(event -> {
            try {
                TableModel results = DatabaseIO.executeQuery(filename, queryArea.getText());
                resultsTable.setModel(results);
            } catch (SQLException e) {
                System.out.println("ERROR"); // TODO: Error-handle
            }
        });

        tablesList.addListSelectionListener(event -> {
            String query = "SELECT * FROM " + tablesList.getSelectedValue().toString() + ";";
            queryArea.setEnabled(true);
            queryArea.setText(query);
            executeBtn.setEnabled(true);
        });

        // Display GUI only once all components have been added to it
        setVisible(true);

        // Attempting to set the divider location before the components are visible doesn't work
        northPane.setDividerLocation(0.1);
        // Attempting to set the divider location before the components are visible doesn't work
        southPane.setDividerLocation(0.5);
    }
}