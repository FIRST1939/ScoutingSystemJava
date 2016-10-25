package elements;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class UIV2 extends JFrame {

	public RobotPanel robot1;
	public RobotPanel robot2;
	public RobotPanel robot3;
	public RobotPanel robot4;
	public RobotPanel robot5;
	public RobotPanel robot6;
	
	public JMenuBar menuBar;
	
	public JMenu menuExport;
	public JMenu menuCompetition;
	
	public JMenuItem itemStartMatch;
	public JMenuItem itemReset;
	public JMenuItem itemToCSV;
	
	public static JPanel contentPane = new JPanel();

	/**
	 * Create the frame. Is not initially set to be visible
	 */
	public UIV2() {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int widthOfScreen = (int) screenSize.getWidth();
		int heightOfScreen = (int) screenSize.getHeight();
		
		setMaximizedBounds(new Rectangle(0, 0, widthOfScreen, heightOfScreen));
		setSize(screenSize);
		setType(Type.NORMAL);
		setAlwaysOnTop(true);
		setTitle("Scouting Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, widthOfScreen, heightOfScreen);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuCompetition = new JMenu("Competition");
		menuCompetition.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menuCompetition);
		
		itemStartMatch = new JMenuItem("Start Match");
		itemStartMatch.setHorizontalAlignment(SwingConstants.CENTER);
		menuCompetition.add(itemStartMatch);
		
		itemReset = new JMenuItem("Reset");
		menuCompetition.add(itemReset);
		
		menuExport = new JMenu("Export");
		menuBar.add(menuExport);
		
		itemToCSV = new JMenuItem("to .csv");
		menuExport.add(itemToCSV);
		
		robot1 = new RobotPanel("Team 1", Color.RED);
			robot1.setTabPlacement(JTabbedPane.TOP);
		robot2 = new RobotPanel("Team 2", Color.RED);
			robot2.setTabPlacement(JTabbedPane.TOP);
		robot3 = new RobotPanel("Team 3", Color.RED);
			robot3.setTabPlacement(JTabbedPane.TOP);
		robot4 = new RobotPanel("Team 4", Color.BLUE);
			robot4.setTabPlacement(JTabbedPane.BOTTOM);
		robot5 = new RobotPanel("Team 5", Color.BLUE);
			robot5.setTabPlacement(JTabbedPane.BOTTOM);
		robot6 = new RobotPanel("Team 6", Color.BLUE);
			robot6.setTabPlacement(JTabbedPane.BOTTOM);
			
		contentPane.setLayout(new GridLayout(2, 3, 1, 1));
		contentPane.add(robot1);
		contentPane.add(robot2);
		contentPane.add(robot3);
		contentPane.add(robot4);
		contentPane.add(robot5);
		contentPane.add(robot6);
		
		this.setContentPane(contentPane);
		
	}
	
	/**
	 * Gets RobotPanel
	 * @param index Valid numbers are 0-5
	 * @return Retruns a RobotPanel. Returns null if you put any value other than 0-5.
	 */
	public RobotPanel getRobotPanel(int index) {
		
		if (index==0) return robot1;
		else if (index==1) return robot2;
		else if (index==2) return robot3;
		else if (index==3) return robot4;
		else if (index==4) return robot5;
		else if (index==5) return robot6;
		else return null;
		
	}
}
