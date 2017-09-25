package buildingBlocks;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import tools.FileUtils;
import tools.ImageUtils;

/**
 * The UI class where the main UI can be found. Is a subclass to JFrame and is
 * an ActionListener.
 * 
 * @author Grayson Spidle
 *
 */
public class UIV3<Panel extends RobotTabbedPanel<? extends RobotPanel, ? extends RobotPanel>> extends JFrame
		implements ActionListener, ContainerListener {

	private static final long serialVersionUID = 8974473527856329569L;

	protected List<Panel> panels = new Vector<Panel>();

//	protected final JMenuBar MENU_BAR = new JMenuBar();
//	protected final JMenu MENU_EXPORT = new JMenu("Export");
//	protected final JMenu MENU_DEBUG = new JMenu("Debug");

	protected File logoFile = new File("logo.png");
	protected File autonomousSaveFile = null;
	protected File teleoperatedSaveFile = null;
	/**
	 * This is the default location where the program will save the data.
	 */
	public File defaultSaveFile = new File(
			(System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));

	private boolean editability = false;

	/**
	 * Creates the frame. Is not initially set to be visible
	 */
	public UIV3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		MENU_BAR.setName("menuBar");
//		setJMenuBar(MENU_BAR);

//		ITEM_IMPORT_TEAM_NUMBERS.setActionCommand("update team numbers");
//		ITEM_IMPORT_TEAM_NUMBERS.addActionListener(this);
//		ITEM_IMPORT_TEAM_NUMBERS.setName("itemImportTeamNumbers");

//		TOGGLE_VALUES_EDITABILITY.setActionCommand("toggle editability");
//		TOGGLE_VALUES_EDITABILITY.addActionListener(this);
//		TOGGLE_VALUES_EDITABILITY.setName("toggleValuesEditability");

//		MENU_EXPORT.setText("Export");
//		MENU_EXPORT.setName("menuExport");
//		MENU_BAR.add(MENU_EXPORT);

//		MENU_COMPETITION.setText("Competition");
//		TEAM_GETTER.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseReleased(MouseEvent arg0) {
//				TI.setVisible(true);
//			}
//		});

//		MENU_COMPETITION.add(TEAM_GETTER);
//		MENU_COMPETITION.add(ITEM_IMPORT_TEAM_NUMBERS);
//		MENU_COMPETITION.setName("menuCompetition");
//		MENU_BAR.add(MENU_COMPETITION);

//		MENU_DEBUG.setText("Debug");
//		MENU_DEBUG.setName("menuDebug");
//		MENU_DEBUG.add(TOGGLE_VALUES_EDITABILITY);
//		MENU_BAR.add(MENU_DEBUG);

		this.setIconImage(ImageUtils.read(logoFile));
		this.addContainerListener(this);

		this.getContentPane().setName("contentPane");
		this.getContentPane().setSize(Toolkit.getDefaultToolkit().getScreenSize());

	}

	// This is where the action listeners are managed for this class (not for the
	// controller inputs)
	@Override
	public void actionPerformed(ActionEvent event) {

		if (event.getActionCommand().equals("convert to csv")) {// CSV conversion
			writeData();
		} else if (event.getActionCommand().equals("toggle editability")) {
			editability = !editability;
			for (Panel p : panels) {
				p.setContentEditable(editability);
			}
		} else {
			System.err.println("Unrecognized action command: " + event.getActionCommand());
		}
	}

	@Override
	public void componentAdded(ContainerEvent arg0) {
		Object o = arg0.getChild();
		if (o.getClass().isAssignableFrom(RobotTabbedPanel.class)) {
			try {
				panels.add((Panel) o);
			} catch (ClassCastException e) {
				System.err.println("Attempted to cast the panel with the provided class but was met with an error.");
				e.printStackTrace();
			}

		}
	}

	@Override
	public void componentRemoved(ContainerEvent arg0) {
		Object o = arg0.getChild();
		if (o.getClass().isAssignableFrom(RobotTabbedPanel.class)) {
			try {
				panels.add((Panel) o);
			} catch (ClassCastException e) {
				System.err.println("Attempted to cast the panel with the provided class but was met with an error.");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets a specified RobotTabbedPanel
	 * 
	 * @param index
	 *            Valid numbers are 0-5
	 * @return Returns a RobotPanel. Returns null if you put any value other than
	 *         0-5.
	 * @throws ArrayIndexOutOfBoundsException
	 *             Throws when the index is not equal to 0-5.
	 */
	public final Panel getRobotTabbedPanel(int index) throws ArrayIndexOutOfBoundsException {
		try {
			return panels.get(index);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw e;
		}
	}

	/**
	 * Gets all autonomous scores in a specified index.
	 * 
	 * @param index
	 *            The index of the score in scoreLabels in the AutonomousRobotPanel
	 *            class.
	 * @return Returns a Vector.
	 */
	public final List<String> getAutonomousScores(int index) {
		Vector<String> scores = new Vector<String>();

		// Getting the specified autonomous score in each RobotPanel
		for (Panel rp : panels) {
			scores.add(rp.autonomous.fields.get(index).getText());
		}
		//
		return scores;
	}

	/**
	 * Gets all teleoperated scores in a specified index.
	 * 
	 * @param index
	 *            The index of the score in scoreLabels in the
	 *            TeleoperatedRobotPanel class.
	 * @return Returns a Vector.
	 */
	public final List<String> getTeleoperatedScores(int index) {
		Vector<String> scores = new Vector<String>();

		// Getting the specified autonomous score in each RobotPanel
		for (Panel rp : panels) {
			scores.add(rp.teleoperated.fields.get(index).getText());
		}
		//
		return scores;

	}

	/**
	 * Gets all the {@link buildingBlocks.ScoreLabel ScoreLabels} in autonomous
	 * panel which should be a subclass of {@link buildingBlocks.RobotPanel
	 * RobotPanel}.
	 * 
	 * @return Returns a {@link java.util.List List} of {@link java.lang.String
	 *         Strings} that represent the labels for autonomous.
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
	 * Gets all the {@link buildingBlocks.ScoreLabel ScoreLabels} in teleoperated
	 * panel which should be a subclass of {@link buildingBlocks.RobotPanel
	 * RobotPanel}.
	 * 
	 * @return Returns a {@link java.util.List List} of {@link java.lang.String
	 *         Strings} that represent the labels for teleoperated.
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
	 * Gets the save locations for the Autonomous and Teleoperated csv's by
	 * displaying its respective JFileChooser.
	 */
	@Deprecated
	public void setNewFile(ArrayList<String> AL) {
		File f = getEvent();
		AL = makeArrayList(f);
	}

	/**
	 * Finds the save file for autosavefile and telesavefile
	 */
	public final void getSaveLocation() {
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
		} else if (result == JFileChooser.CANCEL_OPTION) {
			autonomousSaveFile = null;
			teleoperatedSaveFile = null;
		} else {
			autonomousSaveFile = null;
			teleoperatedSaveFile = null;
		}
	}

	/**
	 * Gets a text document which has a comma separated list of the team numbers
	 * that will be playing.
	 * 
	 * @return Returns the file that points to the text document.
	 */
	@Deprecated
	public File getEvent() {
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
	 * Loads the numbers specified into their respective panels.
	 * @param bot1 The top left panel.
	 * @param bot2 The top middle panel.
	 * @param bot3 The top right panel.
	 * @param bot4 The bottom left panel.
	 * @param bot5 The bottom middle panel.
	 * @param bot6 The bottom right panel.
	 */
	protected void loadTeamNumbers(int bot1, int bot2, int bot3, int bot4, int bot5, int bot6) {
		int[] numbers = new int[] {bot1, bot2, bot3, bot4, bot5, bot6};
		for (int i = 0; i < 5; i++) {
			panels.get(i).autonomous.number.setText(String.valueOf(numbers[i]));
			panels.get(i).teleoperated.number.setText(String.valueOf(numbers[i]));
		}
	}
	
	protected void toggleEditability() {
		editability = !editability;
		for (Panel p : panels) {
			p.setContentEditable(editability);
		}
	}
	
	

	/**
	 * reads in the team numbers and puts them in a ArrayList
	 * 
	 * @author PaulC
	 * @param file
	 *            the File with the Team numbers
	 * @return ArrayList with team numbers
	 */
	@Deprecated
	public ArrayList<String> makeArrayList(File file) {
		ArrayList<String> out = new ArrayList<String>();
		try {
			out = (ArrayList<String>) FileUtils.read(file);

			System.out.println(out.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return out;

	}

	/**
	 * Adds a RobotTabbedPanel to the UI.
	 * 
	 * @param panel
	 *            The panel to add.
	 * @author Grayson Spidle
	 */
	public final void add(Panel panel) {
		panels.add(panel);
		this.getContentPane().add(panel);
	}

	@Deprecated
	public ArrayList<ArrayList<String>> makeFullArray() {
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		ArrayList<String> OuterArray = new ArrayList<String>();
		File file = getEvent();
		OuterArray = makeArrayList(file);
		for (int i = 0; i < OuterArray.size(); i++) {
			String nums = OuterArray.get(i);
//			String[] numsArray = new String[6];
//			numsArray = nums.split(".");
			StringTokenizer ST = new StringTokenizer(nums, ".");
			ArrayList<String> innerArray = new ArrayList<String>();
			while (ST.hasMoreTokens()) {
				innerArray.add(ST.nextToken());
			}
			output.add(innerArray);
		}
		System.out.println("ArrayList of ArrayList: " + output.toString());

		return output;
	}

	@Deprecated
	public void setMatchReset(int matchCount) {
		matchCount = 0;
	}
	
	/**
	 * Inquires the save location for both autonomous and teleoperated files. Then writes all data that are recorded in the ScoreFields. 
	 */
	protected void writeData() {
		getSaveLocation();
		List<List<String>> autonomousData = new Vector<List<String>>();
		List<List<String>> teleoperatedData = new Vector<List<String>>();
		for (int i = 0; i < 5; i++) {
			autonomousData.add(getAutonomousScores(i));
			teleoperatedData.add(getTeleoperatedScores(i));
		}
		try {
			if (autonomousSaveFile.equals(null))
				FileUtils.write(defaultSaveFile, autonomousData, StandardOpenOption.APPEND);
			else
				FileUtils.write(autonomousSaveFile, autonomousData, StandardOpenOption.CREATE_NEW);
			if (teleoperatedSaveFile.equals(null))
				FileUtils.write(defaultSaveFile, teleoperatedData, StandardOpenOption.APPEND);
			else
				FileUtils.write(teleoperatedSaveFile, teleoperatedData, StandardOpenOption.CREATE_NEW);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void reset() {
		for (Panel p : panels) {
			for (ScoreField sf : p.autonomous.fields) {
				sf.reset();
			}
			for (ScoreField sf : p.teleoperated.fields) {
				sf.reset();
			}
		}
	}
	
}
