package com.frcteam1939.ui;

import javax.swing.JLabel;

/**
 * Use this object to ensure this label is written to the .csv file.
 * @author Grayson Spidle
 */
public final class ScoreLabel extends JLabel {
	
	private static final long serialVersionUID = 893696234559085710L;
	
	public ScoreLabel(String arg0) {
		super(arg0);
		this.setName(arg0);
	}
	
	public String toString() {
		return this.getText();
	}
	
}
