package com.frcteam1939.scouting.api;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class RobotPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	protected JLabel team;
	{
		this.team = new JLabel("0");
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	}

	public final void setTeamNumber(String team) {
		this.team.setText(team);
		this.repaint();
	}

	public final String getTeam() {
		return this.team.getText();
	}

	@Override
	public abstract String toString();

	public abstract void reset();

}
