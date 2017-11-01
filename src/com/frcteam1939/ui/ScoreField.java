package com.frcteam1939.ui;

import javax.swing.JTextField;

/**
 * Use this object to ensure this field is written to the .csv file.
 * @author Grayson Spidle
 */
public class ScoreField extends JTextField {
	
	private static final long serialVersionUID = 27047816357232198L;
	
	private String defaultValue = "";
	
	public ScoreField() {
		super();
		this.putClientProperty("JComponent.sizeVariant", "large");
	}
	
	public void setDefaultValue(String arg0) {
		defaultValue = arg0;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setText(String arg0) {
		super.setText(arg0);
	}
	
	/**
	 * A convenience method for setText(getDefaultValue)
	 */
	public void reset() {
		this.setText(defaultValue);
	}
	
	public String toString() {
		return this.getText();
	}
	
}
