import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;

public class TextAreaRenderer extends JTextArea
  implements TableCellRenderer {
  
  public TextAreaRenderer() {
    setLineWrap(true);
    setWrapStyleWord(true);
  }
  
  public Component getTableCellRendererComponent(JTable jTable,
      Object obj, boolean isSelected, boolean hasFocus, int row,
      int column) {
    setText((String)obj);
    
    TableColumnModel columnModel = jTable.getColumnModel();
    setSize(columnModel.getColumn(column).getWidth(), 100000);
    int height_wanted = (int)getPreferredSize().getHeight();
    if (height_wanted != jTable.getRowHeight(row))
      jTable.setRowHeight(row, height_wanted);
    return this;
  }
}