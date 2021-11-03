package presentacion;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;

public class DialogApp extends JDialog{
    AbstractTableModel model;

    public DialogApp(JFrame frame, String texto, AbstractTableModel model) {
        super(frame, texto);
        this.model = model;
    }	
    public AbstractTableModel getModel() {
    	return this.model;
    }

}
