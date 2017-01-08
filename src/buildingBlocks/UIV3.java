package buildingBlocks;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
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
import tools.ImageUtils;

/**
 * The UI class where the main UI can be found. Is a subclass to JFrame and is an ActionListener.
 * @author Grayson Spidle
 *
 */
public abstract class UIV3 extends JFrame implements ActionListener, ContainerListener {

	private static final long serialVersionUID = 8974473527856329569L;
	
	public Vector<RobotTabbedPanel> panels = new Vector<RobotTabbedPanel>();
	
	protected final JToggleButton TOGGLE_VALUES_EDITABILITY = new JToggleButton("Toggle Editability");

	protected final JMenuBar MENU_BAR = new JMenuBar();

	protected final JMenu MENU_EXPORT = new JMenu("Export");
	protected final JMenu MENU_COMPETITION = new JMenu("Competition");
	protected final JMenu MENU_DEBUG = new JMenu("Debug");

	protected final JMenuItem ITEM_IMPORT_TEAM_NUMBERS = new JMenuItem("Import Team Numbers");
	protected final JMenuItem ITEM_TO_CSV = new JMenuItem("to .csv");
	protected final JMenuItem ITEM_SHOW_CONSOLE = new JMenuItem("Show Console");
	
	public JPanel contentPane = new JPanel();

	protected File logoFile = new File("logo.png");
	public File autonomousSaveFile = null;
	public File teleoperatedSaveFile = null;
	protected File defaultSaveFile = new File((System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));

	public final ConsoleWindow CONSOLE = new ConsoleWindow();
	
	private boolean editability = false;

	/**
	 * Create the frame. Is not initially set to be visible
	 */
	public UIV3() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MENU_BAR.setName("menuBar");
		setJMenuBar(MENU_BAR);
		
		ITEM_TO_CSV.setActionCommand("convert to csv");
		ITEM_TO_CSV.addActionListener(this);
		ITEM_TO_CSV.setName("itemToCSV");

		ITEM_IMPORT_TEAM_NUMBERS.setActionCommand("update team numbers");
		ITEM_IMPORT_TEAM_NUMBERS.addActionListener(this);
		ITEM_IMPORT_TEAM_NUMBERS.setName("itemImportTeamNumbers");
		
		ITEM_SHOW_CONSOLE.setActionCommand("show system log");
		ITEM_SHOW_CONSOLE.addActionListener(this);
		ITEM_SHOW_CONSOLE.setName("itemShowConsole");
		
		TOGGLE_VALUES_EDITABILITY.setActionCommand("toggle editability");
		TOGGLE_VALUES_EDITABILITY.addActionListener(this);
		TOGGLE_VALUES_EDITABILITY.setName("toggleValuesEditability");
		
		MENU_EXPORT.setText("Export");
		MENU_EXPORT.setName("menuExport");
		MENU_EXPORT.add(ITEM_TO_CSV);
		MENU_BAR.add(MENU_EXPORT);

		MENU_COMPETITION.setText("Competition");
		MENU_COMPETITION.add(ITEM_IMPORT_TEAM_NUMBERS);
		MENU_COMPETITION.setName("menuCompetition");
		MENU_BAR.add(MENU_COMPETITION);
		
		MENU_DEBUG.setText("Debug");
		MENU_DEBUG.setName("menuDebug");
		MENU_DEBUG.add(ITEM_SHOW_CONSOLE);
		MENU_DEBUG.add(TOGGLE_VALUES_EDITABILITY);
		MENU_BAR.add(MENU_DEBUG);
		
		this.setIconImage(ImageUtils.read(logoFile));
		this.addContainerListener(this);
		
		contentPane.setName("contentPane");
		contentPane.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setContentPane(contentPane);
		
	}

	// This is where the action listeners are managed for this class (not for the controller inputs)
	@Override
	public void actionPerformed(ActionEvent event) { 
		if (event.getActionCommand().equals("convert to csv")) {// CSV conversion
			getSaveLocation();
			ExportData.toCSV(this);
		} 
		else if (event.getActionCommand().equals("update team numbers")) { // Updates team numbers from a comma separated value list in a txt file
			try {
				File file = getEvent();
				List<String> lines = FileUtils.read(file);
				if (lines.size() == 1) {
					String str = lines.toString();
					str = str.replace("[", "");
					str = str.replace("]", "");
					
					StringTokenizer tokenizer = new StringTokenizer(str, ",");
					Vector<String> teamNames = new Vector<String>();
					while (tokenizer.hasMoreTokens()) {
						teamNames.add(tokenizer.nextToken());
					}
					
					for (String s : teamNames) System.out.println(s);
					
//					Sets all team names in the panels with their respective name
					for (int i = 0; i < teamNames.size(); i++) {
						panels.get(i).autonomous.number.setText(teamNames.get(i));
						panels.get(i).teleoperated.number.setText(teamNames.get(i));
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
	
	@Override
	public void componentAdded(ContainerEvent arg0) {
		Object o = arg0.getChild();
		if (o.getClass().isAssignableFrom(RobotTabbedPanel.class)) {
			panels.add((RobotTabbedPanel) o);
		}
	}

	@Override
	public void componentRemoved(ContainerEvent arg0) {
		Object o = arg0.getChild();
		if (o.getClass().isAssignableFrom(RobotTabbedPanel.class)) {
			this.remove((RobotTabbedPanel) o);
		}
	}
	
	/**
	 * Gets a specified RobotTabbedPanel
	 * 
	 * @param index Valid numbers are 0-5
	 * @return Retruns a RobotPanel. Returns null if you put any value other than 0-5.
	 * @throws ArrayIndexOutOfBoundsException Throws when the index is not equal to 0-5.
	 */
	public final RobotTabbedPanel getRobotTabbedPanel(int index) throws ArrayIndexOutOfBoundsException {
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
	public final Vector<String> getAutonomousScores(int index) {
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
	public final Vector<String> getTeleoperatedScores(int index) {
		Vector<String> scores = new Vector<String>();

		// Getting the specified autonomous score in each RobotPanel
		for (RobotTabbedPanel rp : panels) {
			scores.add(rp.teleoperated.fields.get(index).getText());
		}
		//
		return scores;

	}
	
	/**
	 * Gets all the {@link buildingBlocks.ScoreLabel ScoreLabels} in autonomous panel which should be a subclass of {@link buildingBlocks.RobotPanel RobotPanel}. 
	 * @return Returns a {@link java.util.List List} of {@link java.lang.String Strings} that represent the labels for autonomous.
	 */
	public final List<String> getAutonomousLabels() {
		Vector<String> labels = new Vector<String>();
		Collection<ScoreLabel> temp = panels.get(0).autonomous.labels;
		for (ScoreLabel label : temp) {
			labels.add(label.getText());
		}
		return labels;
	}
	
	/**
	 * Gets all the {@link buildingBlocks.ScoreLabel ScoreLabels} in teleoperated panel which should be a subclass of {@link buildingBlocks.RobotPanel RobotPanel}. 
	 * @return Returns a {@link java.util.List List} of {@link java.lang.String Strings} that represent the labels for teleoperated.
	 */
	public final List<String> getTeleoperatedLabels() {
		Vector<String> labels = new Vector<String>();
		Collection<ScoreLabel> temp = panels.get(0).teleoperated.labels;
		for (ScoreLabel label : temp) {
			labels.add(label.getText());
		}
		return labels;
	}

	/**
	 * Gets the save locations for the Autonomous and Teleoperated csv's by displaying its respective JFileChooser.
	 */
	private final void getSaveLocation() {
		// Autonomous Save File
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		chooser.setCurrentDirectory(defaultSaveFile);
		chooser.setDialogTitle("Export Autonomous Data");
		int result = chooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			autonomousSaveFile = chooser.getSelectedFile();
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
		else if (result == JFileChooser.CANCEL_OPTION) {
			autonomousSaveFile = null;
			teleoperatedSaveFile = null;
		}
		else {
			autonomousSaveFile = null;
			teleoperatedSaveFile = null;
		}
	}

	/**
	 * Gets a text document which has a comma separated list of the team numbers that will be playing.
	 * @return Returns the file that points to the text document.
	 */
	private final File getEvent() {
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
	public final void add(RobotTabbedPanel panel) {
		panels.add(panel);
		contentPane.add(panel);
	}

}
