package elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import tools.ExportData;

@SuppressWarnings("serial")
public class UIV2 extends JFrame implements ActionListener {

	public RobotPanel robot1;
	public RobotPanel robot2;
	public RobotPanel robot3;
	public RobotPanel robot4;
	public RobotPanel robot5;
	public RobotPanel robot6;

	public Vector<RobotPanel> panels = new Vector<RobotPanel>();

	public JMenuBar menuBar;

	public JMenu menuExport;
	public JMenu menuCompetition;
	public JMenu menuDebug;

	public JMenuItem itemImportEventData;
	public JMenuItem itemToCSV;
	public JMenuItem itemGetLog;

	public static JPanel contentPane = new JPanel();

	public int maxNumberOfAutonomousScoreFields = 0;
	public int maxNumberOfTeleoperatedScoreFields = 0;

	public File autonomousSaveFile = null;
	public File teleoperatedSaveFile = null;
	public File defaultSaveFile = new File((System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));
	
	public ConsoleWindow console;

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
		setTitle("Scouting Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, widthOfScreen, heightOfScreen);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		itemToCSV = new JMenuItem("to .csv");
		itemToCSV.setActionCommand("convert to csv");
		itemToCSV.addActionListener(this);

		itemImportEventData = new JMenuItem("Import Event Data");
		itemImportEventData.setActionCommand("update team numbers");
		itemImportEventData.addActionListener(this);
		
		itemGetLog = new JMenuItem("Show System Log");
		itemGetLog.setActionCommand("show system log");
		itemGetLog.addActionListener(this);

		menuExport = new JMenu();
		menuExport.setText("Export");
		menuExport.add(itemToCSV);
		menuBar.add(menuExport);

		menuCompetition = new JMenu();
		menuCompetition.setText("Competition");
		menuCompetition.add(itemImportEventData);
		menuBar.add(menuCompetition);
		
		menuDebug = new JMenu();
		menuDebug.setText("Debug");
		menuDebug.add(itemGetLog);
		menuBar.add(menuDebug);
		
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

		panels.add(robot1);
		panels.add(robot2);
		panels.add(robot3);
		panels.add(robot4);
		panels.add(robot5);
		panels.add(robot6);

		contentPane.setLayout(new GridLayout(2, 3, 1, 1));
		for (RobotPanel rp : panels) {
			contentPane.add(rp);
		}
		
		console = new ConsoleWindow();
		
		this.setContentPane(contentPane);
		
	}

	// This is where the action listeners are managed for this class (not forthe controllers)
	@Override
	public void actionPerformed(ActionEvent event) { 
		if (event.getActionCommand().equals("convert to csv")) {// CSV conversion
			getSaveLocation();
			ExportData.toCSV(UIV2.this);
		} else if (event.getActionCommand().equals("update team numbers")) { // Updates team numbers from a comma separated value list in a txt file
			try {
				File file = getEvent();
				List<String> lines = FileUtils.read(file);
				if (lines.size() == 1) {
					String str = lines.toString();
					str = str.replace("[", "");
					str = str.replace("]", "");
					Vector<Integer> indexes = new Vector<Integer>();
					char[] arr = str.toCharArray();
					for (int i = 0; i < arr.length; i++) {
						if (arr[i] == ',') {
							indexes.add(i);
						}
					}

					Vector<String> teamNames = new Vector<String>();
					teamNames.add(str.substring(0, indexes.get(0)).trim());
					teamNames.add(str.substring(indexes.get(0) + 1, indexes.get(1)).trim());
					teamNames.add(str.substring(indexes.get(1) + 1, indexes.get(2)).trim());
					teamNames.add(str.substring(indexes.get(2) + 1, indexes.get(3)).trim());
					teamNames.add(str.substring(indexes.get(3) + 1, indexes.get(4)).trim());
					teamNames.add(str.substring(indexes.get(4) + 1).trim());

					for (int i = 0; i < teamNames.size(); i++) {
						panels.get(i).autonomous.name.setText(teamNames.get(i));
						panels.get(i).teleoperated.name.setText(teamNames.get(i));
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (event.getActionCommand().equals("show system log")) {
			console.setVisible(true);
		}
	}

	/**
	 * Gets a specified RobotPanel
	 * 
	 * @param index
	 *            Valid numbers are 0-5
	 * @return Retruns a RobotPanel. Returns null if you put any value other
	 *         than 0-5.
	 * @throws ArrayIndexOutOfBoundsException
	 *             Is thrown when the index is not equal to 0-5.
	 */
	public RobotPanel getRobotPanel(int index) throws ArrayIndexOutOfBoundsException {
		try {
			return panels.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw e;
		}
	}

	/**
	 * Self Explanatory.
	 * 
	 * @param index
	 *            The index of the score in scoreLabels in the
	 *            AutonomousRobotPanel class.
	 * @return Returns All autonomous scores in specified index in the form of a
	 *         Vector<String>
	 */
	public Vector<String> getAutonomousScores(int index) {
		Vector<String> scores = new Vector<String>();

		// Getting the specified autonomous score in each RobotPanel
		for (RobotPanel rp : panels) {
			scores.add(rp.autonomous.scoreFields.get(index).getText());
		}
		//
		return scores;
	}

	public Vector<String> getTeleoperatedScores(int index) {
		Vector<String> scores = new Vector<String>();

		// Getting the specified autonomous score in each RobotPanel
		for (RobotPanel rp : panels) {
			scores.add(rp.teleoperated.scoreFields.get(index).getText());
		}
		//
		return scores;

	}

	/**
	 * Gets the save locations for the Autonomous and Teleoperated csv's
	 */
	private void getSaveLocation() {
		// Autonomous Save File
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser.setCurrentDirectory(defaultSaveFile);
		chooser.setDialogTitle("Export Autonomous Data");
		int result = chooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			autonomousSaveFile = chooser.getSelectedFile();
		} else {
			autonomousSaveFile = null;
		}

		// Teleoperated Save File
		JFileChooser chooser2 = new JFileChooser();
		chooser2.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser2.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser2.setCurrentDirectory(defaultSaveFile);
		chooser2.setDialogTitle("Export Teleoperated Data");
		int result2 = chooser2.showSaveDialog(this);
		if (result2 == JFileChooser.APPROVE_OPTION) {
			teleoperatedSaveFile = chooser2.getSelectedFile();
		} else {
			teleoperatedSaveFile = null;
		}
	}

	private File getEvent() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setCurrentDirectory(defaultSaveFile);

		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			 return chooser.getSelectedFile();
		} else {
			return null;
		}
	}

}
