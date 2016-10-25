package elements;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.BoxLayout;
import java.awt.Component;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Dimension;
import java.awt.Frame;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class UIV2 extends JFrame {

	private JPanel contentPane;
	
	private JPanel autonomousPanel;
	
	public static AutonomousRobotPanel autonomousRobot1;
	public static AutonomousRobotPanel autonomousRobot2;
	public static AutonomousRobotPanel autonomousRobot3;
	public static AutonomousRobotPanel autonomousRobot4;
	public static AutonomousRobotPanel autonomousRobot5;
	public static AutonomousRobotPanel autonomousRobot6;

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
		setBounds(-6, 0, widthOfScreen, heightOfScreen);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCompetition = new JMenu("Competition");
		mnCompetition.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnCompetition);
		
		JMenuItem mntmStartMatch = new JMenuItem("Start Match");
		mntmStartMatch.setHorizontalAlignment(SwingConstants.CENTER);
		mnCompetition.add(mntmStartMatch);
		
		JMenuItem mntmReset = new JMenuItem("Reset");
		mnCompetition.add(mntmReset);
		
		JMenu mnExport = new JMenu("Export");
		menuBar.add(mnExport);
		
		JMenuItem mntmTocsv = new JMenuItem("to .csv");
		mnExport.add(mntmTocsv);
		
		autonomousRobot1 = new AutonomousRobotPanel("Team 1", Color.RED);
		autonomousRobot2 = new AutonomousRobotPanel("Team 2", Color.RED);
		autonomousRobot3 = new AutonomousRobotPanel("Team 3", Color.RED);
		autonomousRobot4 = new AutonomousRobotPanel("Team 4", Color.BLUE);
		autonomousRobot5 = new AutonomousRobotPanel("Team 5", Color.BLUE);
		autonomousRobot6 = new AutonomousRobotPanel("Team 6", Color.BLUE);
		
		autonomousPanel = new JPanel();
			autonomousPanel.setLayout(new GridLayout(0, 3, 1, 1));
			autonomousPanel.add(autonomousRobot1);
			autonomousPanel.add(autonomousRobot2);
			autonomousPanel.add(autonomousRobot3);
			autonomousPanel.add(autonomousRobot4);
			autonomousPanel.add(autonomousRobot5);
			autonomousPanel.add(autonomousRobot6);
		
		contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		try {
			changeContentPane(autonomousPanel);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Unable to display the autonomousPanel");
			System.exit(0);
		}
		
	}
	
	/**
	 * Used to change the content pane between autonomous and teleoperated panels
	 * @param arg0 Either put in the autonomousPanel or teleoperatedPanel
	 * @throws ArrayIndexOutOfBoundsException Throws this exception if arg0 does not have 6 elements (recommended not to have more because they won't be used)
	 */
	private void changeContentPane(JPanel arg0) throws ArrayIndexOutOfBoundsException {
		try {
			arg0.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{arg0.getComponent(0), arg0.getComponent(1), arg0.getComponent(2), arg0.getComponent(3), arg0.getComponent(4), arg0.getComponent(5)}));
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException();
		}
		contentPane = arg0;
		setContentPane(contentPane);
	}
}
