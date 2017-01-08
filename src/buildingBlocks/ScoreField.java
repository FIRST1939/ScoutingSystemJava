package buildingBlocks;

import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;

/**
 * Use this object to ensure this field is written to the .csv file.
 * @author Grayson Spidle
 */
public class ScoreField extends JTextField {
	
	private static final long serialVersionUID = 27047816357232198L;
	protected Vector<ValueChangeListener> listeners;

	public ScoreField() {
		super();
		this.putClientProperty("JComponent.sizeVariant", "large");
		listeners = new Vector<ValueChangeListener>();
	}
	
	public void setText(String arg0) {
		super.setText(arg0);
		updateListeners(new ValueChangeEvent(this, "change"));
	}
	
	public void addValueChangeListener(ValueChangeListener listener) {
		listeners.add(listener);
	}
	
	public List<ValueChangeListener> getListeners() {
		return listeners;
	}
	
	protected void updateListeners(ValueChangeEvent e) {
		for (ValueChangeListener l : listeners) {
			l.valueChanged(e);
		}
	}
	
	public String toString() {
		return this.getText();
	}
	
}
