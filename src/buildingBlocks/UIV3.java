package buildingBlocks;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import tools.ExportData;
import tools.FileUtils;

/**
 * The UI class where the main UI can be found. Is a subclass to JFrame and is an ActionListener.
 * @author Grayson Spidle
 *
 */
public abstract class UIV3 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 8974473527856329569L;
	
	public Vector<RobotTabbedPanel> panels = new Vector<RobotTabbedPanel>();
	
	public final JToggleButton TOGGLE_VALUES_EDITABILITY = new JToggleButton("Toggle Editability");

	public final JMenuBar MENU_BAR = new JMenuBar();

	public final JMenu MENU_EXPORT = new JMenu("Export");
	public final JMenu MENU_COMPETITION = new JMenu("Competition");
	public final JMenu MENU_DEBUG = new JMenu("Debug");

	public JMenuItem ITEM_IMPORT_TEAM_NUMBERS = new JMenuItem("Import Team Numbers");
	public final JMenuItem ITEM_TO_CSV = new JMenuItem("to .csv");
	public JMenuItem ITEM_SHOW_CONSOLE = new JMenuItem("Show Console");

	public JPanel contentPane = new JPanel();

	public File autonomousSaveFile = null;
	public File teleoperatedSaveFile = null;
	public File defaultSaveFile = new File((System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));

	public final ConsoleWindow CONSOLE = new ConsoleWindow();
	
	private boolean editability = false;

	/**
	 * Create the frame. Is not initially set to be visible
	 */
	public UIV3() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setJMenuBar(MENU_BAR);

		ITEM_TO_CSV.setActionCommand("convert to csv");
		ITEM_TO_CSV.addActionListener(this);

		ITEM_IMPORT_TEAM_NUMBERS.setActionCommand("update team numbers");
		ITEM_IMPORT_TEAM_NUMBERS.addActionListener(this);
		
		ITEM_SHOW_CONSOLE.setActionCommand("show system log");
		ITEM_SHOW_CONSOLE.addActionListener(this);
		
		TOGGLE_VALUES_EDITABILITY.setActionCommand("toggle editability");
		TOGGLE_VALUES_EDITABILITY.addActionListener(this);
		
		MENU_EXPORT.setText("Export");
		MENU_EXPORT.add(ITEM_TO_CSV);
		MENU_BAR.add(MENU_EXPORT);

		MENU_COMPETITION.setText("Competition");
		MENU_COMPETITION.add(ITEM_IMPORT_TEAM_NUMBERS);
		MENU_BAR.add(MENU_COMPETITION);
		
		MENU_DEBUG.setText("Debug");
		MENU_DEBUG.add(ITEM_SHOW_CONSOLE);
		MENU_DEBUG.add(TOGGLE_VALUES_EDITABILITY);
		MENU_BAR.add(MENU_DEBUG);
		
		this.addContainerListener(new ContainerListener() {

			@Override
			public void componentAdded(ContainerEvent arg0) {
				try {
					panels.add((RobotTabbedPanel) arg0.getSource());
				} catch (ClassCastException e) {
					System.err.println("Any subclasses of " + this.getClass().getName() + " should only add objects of type RobotTabbedPanel");
				}
			}

			@Override
			public void componentRemoved(ContainerEvent arg0) {
				try {
					panels.add((RobotTabbedPanel) arg0.getSource());
				} catch (ClassCastException e) {
					System.err.println("Any subclasses of " + this.getClass().getName() + " should only remove objects of type RobotTabbedPanel");
				}
			}
		});
		
		contentPane.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setContentPane(contentPane);
		
	}

	// This is where the action listeners are managed for this class (not for the controller inputs)
	@Override
	public void actionPerformed(ActionEvent event) { 
		if (event.getActionCommand().equals("convert to csv")) {// CSV conversion
			getSaveLocation();
			ExportData.toCSV(UIV3.this);
		} else if (event.getActionCommand().equals("update team numbers")) { // Updates team numbers from a comma separated value list in a txt file
			try {
				File file = getEvent();
				List<String> lines = FileUtils.read(file);
				if (lines.size() == 1) {
					String str = lines.toString();
					str = str.replace("[", "");
					str = str.replace("]", "");
					
//					Goes through the text file and gets the indexes of all the ','
					Vector<Integer> indexes = new Vector<Integer>();
					char[] arr = str.toCharArray();
					for (int i = 0; i < arr.length; i++) {
						if (arr[i] == ',') {
							indexes.add(i);
						}
					}

//					Adds 6 teams using the indexes from above
					Vector<String> teamNames = new Vector<String>();
					teamNames.add(str.substring(0, indexes.get(0)).trim());
					teamNames.add(str.substring(indexes.get(0) + 1, indexes.get(1)).trim());
					teamNames.add(str.substring(indexes.get(1) + 1, indexes.get(2)).trim());
					teamNames.add(str.substring(indexes.get(2) + 1, indexes.get(3)).trim());
					teamNames.add(str.substring(indexes.get(3) + 1, indexes.get(4)).trim());
					teamNames.add(str.substring(indexes.get(4) + 1).trim());

//					Sets all team names in the panels with their respective name
					for (int i = 0; i < teamNames.size(); i++) {
						panels.get(i).setTeamNumber(teamNames.get(i));
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		else if (event.getActionCommand().equals("show system log")) { // Shows the ConsoleWindow 
			CONSOLE.setVisible(true);
		}
		else if (event.getActionCommand().equals("toggle editability")) {
			editability = !editability;
			for (RobotTabbedPanel rtp : panels) {
				rtp.setContentEditable(editability);
			}
		}
	}

	/**
	 * Gets a specified RobotTabbedPanel
	 * 
	 * @param index Valid numbers are 0-5
	 * @return Retruns a RobotPanel. Returns null if you put any value other than 0-5.
	 * @throws ArrayIndexOutOfBoundsException Throws when the index is not equal to 0-5.
	 */
	public RobotTabbedPanel getRobotTabbedPanel(int index) throws ArrayIndexOutOfBoundsException {
		try {
			return panels.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw e;
		}
	}

	/**
	 * Gets all autonomous scores in a specified index.
	 * @param index The index of the score in scoreLabels in the AutonomousRobotPanel class.
	 * @return Returns a Vector.
	 */
	public Vector<String> getAutonomousScores(int index) {
		Vector<String> scores = new Vector<String>();

		// Getting the specified autonomous score in each RobotPanel
		for (RobotTabbedPanel rp : panels) {
			scores.add(rp.autonomous.fields.get(index).getText());
		}
		//
		return scores;
	}

	/**
	 * Gets all teleoperated scores in a specified index.
	 * @param index The index of the score in scoreLabels in the TeleoperatedRobotPanel class.
	 * @return Returns a Vector.
	 */
	public Vector<String> getTeleoperatedScores(int index) {
		Vector<String> scores = new Vector<String>();

		// Getting the specified autonomous score in each RobotPanel
		for (RobotTabbedPanel rp : panels) {
			scores.add(rp.teleoperated.fields.get(index).getText());
		}
		//
		return scores;

	}
	
	public Vector<String> getAutonomousLabels() {
		Vector<String> labels = new Vector<String>();
		Vector<ScoreLabel> temp = panels.get(0).autonomous.labels;
		for (ScoreLabel label : temp) {
			labels.add(label.getText());
		}
		return labels;
	}
	
	public Vector<String> getTeleoperatedLabels() {
		Vector<String> labels = new Vector<String>();
		Vector<ScoreLabel> temp = panels.get(0).teleoperated.labels;
		for (ScoreLabel label : temp) {
			labels.add(label.getText());
		}
		return labels;
	}

	/**
	 * Gets the save locations for the Autonomous and Teleoperated csv's by displaying its respective JFileChooser.
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

	/**
	 * Gets a text document which has a comma separated list of the team numbers that will be playing.
	 * @return Returns the file that points to the text document.
	 */
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
	
	/**
	 * Adds a RobotTabbedPanel to the UI.
	 * @param panel The panel to add.	
	 */
	public void add(RobotTabbedPanel panel) {
		panels.add(panel);
		contentPane.add(panel);
	}

}
