package amethyst.model;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class ResultTableModel extends AbstractTableModel {
    private Vector<Vector<Object>> data;
    private Vector<String>         columns;

    public ResultTableModel() {
        data = new Vector<Vector<Object>>();
        columns = new Vector<String>();
    }

    public ResultTableModel(Vector<Vector<Object>> data, Vector<String> columns) {
        this.data = data;
        this.columns = columns;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.size();
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data.get(row).get(column);
    }
}
