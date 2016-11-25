package buildingBlocks;

import javax.swing.JTextField;

public class ScoreField extends JTextField {
	
	public ScoreField() {
		super();
	}
	
	public void increase(int arg0) {
		this.setText("" + (Integer.parseInt(this.getText()) + arg0));
	}
	
	public void decrease(int arg0) {
		this.setText("" + (Integer.parseInt(this.getText()) - arg0));
	}
	
}
