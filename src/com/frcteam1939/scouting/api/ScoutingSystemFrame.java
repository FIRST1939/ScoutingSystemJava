package com.frcteam1939.scouting.api;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ScoutingSystemFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private ScoutingSystem system;

	private RobotPanel[] panels;

	public ScoutingSystemFrame(ScoutingSystem system, Class<? extends RobotPanel> panel) throws Exception {
		setLayout(new GridLayout(2, 3));
		this.panels = new RobotPanel[6];
		for (int i = 0; i < 6; i++) {
			this.panels[i] = panel.newInstance();
			this.add(this.panels[i]);
		}

		initializeMenuBar();

		setTitle("1939 Scouting Program");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		this.setSize(640, 480);
	}

	private void initializeMenuBar() {
		JMenuItem nextMatch = new JMenuItem("Next Match");
		nextMatch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ScoutingSystemFrame.this.system.nextMatch();
			}
		});

		JMenuItem goToMatch = new JMenuItem("Go To Match...");
		goToMatch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				goToMatch();
			}
		});

		JMenuItem loadMatches = new JMenuItem("Load Match Schedule");
		loadMatches.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadMatches();
			}
		});

		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		JMenuItem saveAs = new JMenuItem("Save As...");
		saveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveAs();
			}
		});

		JMenuItem reset = new JMenuItem("Reset");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetAll();
			}
		});

		JMenu file = new JMenu("File");
		file.add(save);
		file.add(saveAs);
		file.add(reset);

		JMenu match = new JMenu("Match");
		match.add(loadMatches);

		JMenuBar menu = new JMenuBar();
		menu.add(file);
		menu.add(match);

		setJMenuBar(menu);
	}

}
