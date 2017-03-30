package tools;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;

import buildingBlocks.RobotTabbedPanel;
import buildingBlocks.ScoreField;
import buildingBlocks.ScoreLabel;
import buildingBlocks.UIV3;

/**
 * This class provides static methods to export data into .csv files. 
 * @author Grayson Spidle
 */
public class ExportData {
	
	private static List<StringBuilder> autoBuilders;
	private static List<StringBuilder> teleBuilders;
	
	/**
	 * Exports two files with the team numbers, score labels, and scores
	 * @param ui The UI to for the method to find all the necessary information.
	 */
	public static void toCSV(UIV3 ui) {
		
		System.out.println("Exporting data to .csv");
		
		autoBuilders = createAutonomousBuilders(ui);
		teleBuilders = createTeleoperatedBuilders(ui);
		
		writeNumbers(ui);
		writeLabels(ui);
		writeScores(ui);
		writeFiles(ui);	
		
	}
	
	private static List<String> getAutonomousScoreLabels(UIV3 ui) {
		List<String> output = new Vector<String>();
		for (ScoreLabel l : ui.panels.get(0).autonomous.labels) {
			output.add(l.getText());
		}
		return output;
	}
	
	private static List<String> getTeleoperatedScoreLabels(UIV3 ui) {
		List<String> output = new Vector<String>();
		for (ScoreLabel l : ui.panels.get(0).teleoperated.labels) {
			output.add(l.getText());
		}
		return output;
	}
	
	private static List<String> getAutonomousScores(UIV3 ui) {
		List<String> output = new Vector<String>();
		for (int y = 0; y < getAutonomousScoreLabels(ui).size(); y++) {
			StringBuilder b = new StringBuilder();
			for (int x = 0; x < ui.panels.size(); x++) {
				String field = ui.panels.get(x).autonomous.fields.get(y).toString();
				b.append(field + ",");
			}
			output.add(b.toString());
		}
		return output;
	}
	
	private static List<String> getTeleoperatedScores(UIV3 ui) {
		List<String> output = new Vector<String>();
		for (int y = 0; y < getTeleoperatedScoreLabels(ui).size(); y++) {
			StringBuilder b = new StringBuilder();
			for (int x = 0; x < ui.panels.size(); x++) {
				String field = ui.panels.get(x).teleoperated.fields.get(y).toString();
				b.append(field + ",");
			}
			output.add(b.toString());
		}
		return output;
	}
	
	private static void writeNumbers(UIV3 ui) {
		String nums = "";
		for (RobotTabbedPanel rp : ui.panels) {
			nums = nums + "," + String.valueOf(rp.getTeamNumber());
		}
		autoBuilders.get(0).append(nums);
		teleBuilders.get(0).append(nums);
	}
	
	private static void writeLabels(UIV3 ui) { 
		List<String> lbls = getAutonomousScoreLabels(ui);
		for (int i = 0; i < lbls.size(); i++) {
			String lbl = lbls.get(i) + ",";
			autoBuilders.get(i + 1).append(lbl); 
		}
		lbls = getTeleoperatedScoreLabels(ui);
		for (int i = 0; i < lbls.size(); i++) {
			String lbl = lbls.get(i) + ",";
			teleBuilders.get(i + 1).append(lbl); 
		}
	}
	
	private static void writeScores(UIV3 ui) {
		List<String> flds = getAutonomousScores(ui);
		for (int i = 0; i < flds.size(); i++) {
			String fld = flds.get(i) + ",";
			autoBuilders.get(i + 1).append(fld);
		}
		flds = getTeleoperatedScores(ui);
		for (int i = 0; i < flds.size(); i++) {
			String fld = flds.get(i) + ",";
			teleBuilders.get(i + 1).append(fld);
		}
	}
	
	private static void writeFiles(UIV3 ui) {
		File autoFile = ui.autonomousSaveFile;
		File teleFile = ui.teleoperatedSaveFile;
		try {
			if (!autoFile.getAbsolutePath().endsWith(".csv")) {
				String path = autoFile.getAbsolutePath();
				autoFile.delete();
				autoFile = new File(path + ".csv");
			}
			if (!teleFile.getAbsolutePath().endsWith(".csv")) {
				String path = teleFile.getAbsolutePath();
				teleFile.delete();
				teleFile = new File(path + ".csv");
			}
			FileUtils.write(autoFile, autoBuilders);
			FileUtils.write(teleFile, teleBuilders);
			System.out.println("Export complete.");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Export failed.");
		}
	}
	
	private static List<StringBuilder> createAutonomousBuilders(UIV3 ui) {
		Vector<StringBuilder> output = new Vector<StringBuilder>();
		for (int i = 0; i <= getAutonomousScoreLabels(ui).size(); i++) {
			output.add(new StringBuilder());
		}
		return output;
	}
	
	private static List<StringBuilder> createTeleoperatedBuilders(UIV3 ui) {
		Vector<StringBuilder> output = new Vector<StringBuilder>();
		for (int i = 0; i <= getTeleoperatedScoreLabels(ui).size(); i++) {
			output.add(new StringBuilder());
		}
		return output;
	}
	
}
