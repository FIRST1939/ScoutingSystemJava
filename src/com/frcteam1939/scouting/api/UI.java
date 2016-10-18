package com.frcteam1939.scouting.api;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.JFormattedTextField;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
public class UI extends JFrame {
	
	private Timer timer;
	private TimerTask automTask;
	private TimerTask teleopTask;
	
	private JMenuBar menuBar;
	private JMenu competition;
	private JMenuItem start;
	private JMenuItem reset;
	private JMenu export;
	private JMenuItem toCSV;
	
	private ActionListener startMatch;
	private ActionListener resetMatch;
	private ActionListener exportDataToCSV;
	
	private JPanel teleopPanel;
	private JPanel automPanel;
	
	private JPanel automRobot1;
	private JPanel automRobot2;
	private JPanel automRobot3;
	private JPanel automRobot4;
	private JPanel automRobot5;
	private JPanel automRobot6;
	
	private JPanel robot1;
	private JPanel robot2;
	private JPanel robot3;
	private JPanel robot4;
	private JPanel robot5;
	private JPanel robot6;
	
	private JFormattedTextField robot1Name;
	private JFormattedTextField robot2Name;
	private JFormattedTextField robot3Name;
	private JFormattedTextField robot4Name;
	private JFormattedTextField robot5Name;
	private JFormattedTextField robot6Name;
	
	private GridLayout grid;
	private GridLayout automGrid;
	private GridLayout teleopGrid;
	
	private GroupLayout teleopGroupLayout1;
	private GroupLayout teleopGroupLayout2;
	private GroupLayout teleopGroupLayout3;
	private GroupLayout teleopGroupLayout4;
	private GroupLayout teleopGroupLayout5;
	private GroupLayout teleopGroupLayout6;
	
	private final int TIME_AUTONOMOUS = 30;
	private final int TIME_TELEOP = 240;
	
	public UI() {
		
		timer = new Timer();
		
		grid = new GridLayout(2,3);
		
		teleopGrid = new GridLayout(2,3);
		
		automGrid = new GridLayout(2,3);
		
		initializeTimerTasks();
		
		initializeActionListeners();
		
		initializeMenuBar();
		
		initializeTeamNameFields();
		
		initializeTeamAutomPanels();
		
		initializeTeamTeleopPanels();
		
		this.setJMenuBar(menuBar);
		
		this.setTitle("1939 Scouting Program");
		
		// For Debugging will delete
		this.automPanel.setVisible(false);
		this.setLayout(grid);
		this.add(robot1);
		this.add(robot2);
		this.add(robot3);
		this.add(robot4);
		this.add(robot5);
		this.add(robot6);
		// *************
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(200, 200);
		
	}
	
	private void initializeMenuBar() { // TODO need to add action listeners to all of these JMenuItems
		
		start = new JMenuItem();
			start.setText("Start Match");
		
		reset = new JMenuItem();
			reset.setText("Reset");
			
		toCSV = new JMenuItem();
			toCSV.setText("Export to .csv");
			
		competition = new JMenu();
			competition.setText("Competition");
			competition.add(start);
			competition.add(reset);
		
		export = new JMenu();
			export.setText("Export");
			export.add(toCSV);
		
		menuBar = new JMenuBar();
			menuBar.add(competition);
			menuBar.add(export);
		
	}
	
	private void initializeTeamNameFields() {
		
		robot1Name = new JFormattedTextField();
			robot1Name.setEditable(false);
			robot1Name.setText("Team1");
	
		robot2Name = new JFormattedTextField();
			robot2Name.setEditable(false);
			robot2Name.setText("Team2");
			
		robot3Name = new JFormattedTextField();
			robot3Name.setEditable(false);
			robot3Name.setText("Team3");
			
		robot4Name = new JFormattedTextField();
			robot4Name.setEditable(false);
			robot4Name.setText("Team4");
			
		robot5Name = new JFormattedTextField();
			robot5Name.setEditable(false);
			robot5Name.setText("Team5");
			
		robot6Name = new JFormattedTextField();
			robot6Name.setEditable(false);
			robot6Name.setText("Team6");
			
		
	}

	private void initializeTeamAutomPanels() {
		
		automRobot1 = new JPanel();
			automRobot1.add(robot1Name);
			automRobot1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			automRobot1.setBackground(Color.RED);
		
		automRobot2 = new JPanel();
			automRobot2.add(robot2Name);
			automRobot2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			automRobot2.setBackground(Color.RED);
			
		automRobot3 = new JPanel();
			automRobot3.add(robot3Name);
			automRobot3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			automRobot3.setBackground(Color.RED);
			
		automRobot4 = new JPanel();
			automRobot4.add(robot4Name);
			automRobot4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			automRobot4.setBackground(Color.RED);
			
		automRobot5 = new JPanel();
			automRobot5.add(robot5Name);
			automRobot5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			automRobot5.setBackground(Color.RED);
			
		automRobot6 = new JPanel();
			automRobot6.add(robot6Name);
			automRobot6.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			automRobot6.setBackground(Color.RED);
			
		automPanel = new JPanel();
			automPanel.setLayout(grid);
			automPanel.add(automRobot1);
			automPanel.add(automRobot2);
			automPanel.add(automRobot3);
			automPanel.add(automRobot4);
			automPanel.add(automRobot5);
			automPanel.add(automRobot6);
		
		automPanel.setVisible(true);
			
	}
	
	private void initializeTeamTeleopPanels() {
		
		robot1 = new JPanel();
			robot1.add(robot1Name);
			robot1.setBackground(Color.RED);
			robot1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			robot1.setLayout(teleopGrid);
			
		robot2 = new JPanel();
			robot2.add(robot2Name);
			robot2.setBackground(Color.RED);
			robot2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			robot2.setLayout(teleopGrid);
			
		robot3 = new JPanel();
			robot3.add(robot3Name);
			robot3.setBackground(Color.RED);
			robot3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			robot3.setLayout(teleopGrid);
			
		robot4 = new JPanel();
			robot4.add(robot4Name);
			robot4.setBackground(Color.BLUE);
			robot4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			robot4.setLayout(teleopGrid);
			
		robot5 = new JPanel();
			robot5.add(robot5Name);
			robot5.setBackground(Color.BLUE);
			robot5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			robot5.setLayout(teleopGrid);
			
		robot6 = new JPanel();
			robot6.add(robot6Name);
			robot6.setBackground(Color.BLUE);
			robot6.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			robot6.setLayout(teleopGrid);
			
		teleopPanel = new JPanel();
			teleopPanel.setLayout(grid);
			teleopPanel.add(automRobot1);
			teleopPanel.add(automRobot2);
			teleopPanel.add(automRobot3);
			teleopPanel.add(automRobot4);
			teleopPanel.add(automRobot5);
			teleopPanel.add(automRobot6);
			
		teleopPanel.setVisible(false);
			
	}
	
	private void initializeTimerTasks() {
		
		automTask = new TimerTask() {
			
			boolean doThis = true;
			
			@Override
			public void run() {
				
				if (doThis) {
					
					UI.this.add(automPanel);
					automPanel.setVisible(true);
					
				}
				
				UI.this.setTitle("Autonomous");
				doThis = false;
				
			}
			
		};
		
		teleopTask = new TimerTask() {

			boolean doThis = true;
			
			@Override
			public void run() {
				
				if (doThis) {
					
					UI.this.remove(automPanel);
					UI.this.add(teleopPanel);
					teleopPanel.setVisible(true);
					
				}
				
				UI.this.setTitle("Teleop");
				doThis = false;
				
			}
			
		};
		
	}
	
	private void initializeActionListeners() { // TODO needs some code that I do not have
		
		startMatch = new ActionListener() { // TODO might not work but it will

			@Override
			public void actionPerformed(ActionEvent e) {
				
				timer.schedule(automTask, 0, TIME_AUTONOMOUS);
				timer.schedule(teleopTask, 0, TIME_TELEOP);
				
			}
			
		};
		
		resetMatch = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// This will implement Joey's code that will get the teams that are playing for each match
				
				
			}
			
			
		};

		exportDataToCSV = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Need to figure out how to do this
				
				
			}
			
		};
		
	}
	
	public static void main(String[] args) {
		
		UI t = new UI();
		
	}

}
