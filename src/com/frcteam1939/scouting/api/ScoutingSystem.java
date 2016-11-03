package com.frcteam1939.scouting.api;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ScoutingSystem {

	private ScoutingSystemFrame frame;

	private String data = "";

	private String[][] matches;
	private int currentMatch = 0;

	public ScoutingSystem(Class<? extends RobotPanel> panel) {
		try {
			this.frame = new ScoutingSystemFrame(this, panel);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	private String saveLocation;

	private void save() {
		if (this.saveLocation == null) {
			saveAs();
		} else {
			saveToFile(this.saveLocation);
		}
	}

	private void saveAs() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			this.saveLocation = fileChooser.getSelectedFile().getPath();
			if (this.saveLocation.contains(".")) {
				this.saveLocation = this.saveLocation.split(".")[0] + ".csv";
			} else {
				this.saveLocation += ".csv";
			}
			saveToFile(this.saveLocation);
		}
	}

	private void saveToFile(String file) {

	}

	public void goToMatch() {
		try {
			int match = Integer.valueOf(JOptionPane.showInputDialog("Match Number:"));
			loadMatch(match - 1);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "You Must Enter an Integer!");
		}
	}

	public void loadMatch(int match) {
		try {
			for (int i = 0; i < this.panels.length; i++) {
				this.panels[i].reset();
				this.panels[i].setTeamNumber(this.matches[match][i]);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error Loading Match: " + match);
		}
	}

	public void nextMatch() {
		for (RobotPanel panel : this.panels) {
			this.data += panel.toString() + "\n";
		}
		this.currentMatch++;
		loadMatch(this.currentMatch);
	}

	public void loadMatches() {

	}

}
