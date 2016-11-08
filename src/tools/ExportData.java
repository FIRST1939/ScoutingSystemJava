package tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;

import elements.FileUtils;
import elements.RobotPanel;
import elements.UIV2;

public class ExportData {
	
	private static Vector<String> teamNumbers = new Vector<String>();
	
	private static Vector<String> autonomousScoreLabels = new Vector<String>();
	private static Vector<String> teleoperatedScoreLabels = new Vector<String>();
	
	private static ArrayList<Vector<String>> autonomousScores = new ArrayList<Vector<String>>();
	private static ArrayList<Vector<String>> teleoperatedScores = new ArrayList<Vector<String>>();
	
	private static ArrayList<Vector<String>> autonomousOutput = new ArrayList<Vector<String>>();
	private static ArrayList<Vector<String>> teleoperatedOutput = new ArrayList<Vector<String>>();
	
	/**
	 * Exports stuff
	 * @param ui
	 */
	public static void toCSV(UIV2 ui) {
		
		System.out.println("Exporting data to .csv");
		
		getRobotNumbers(ui);
		getScoreLabels(ui);
		getScores(ui);
		
		for (int i = 0; i < autonomousScoreLabels.size(); i++) {
			autonomousOutput.add(new Vector<String>());
			teleoperatedOutput.add(new Vector<String>());
		}
		
		String temp = " , " + teamNumbers.toString();
		temp = temp.replace("[", "");
		temp = temp.replace("]", "");
		Vector<String> temp2 = new Vector<String>();
		temp2.add(temp);
		autonomousOutput.set(0, temp2);
		
		for (int i = 0; i < autonomousScoreLabels.size() - 1; i++) {
			temp = autonomousScoreLabels.get(i+1);
			temp = temp.replace("[", "");
			temp = temp.replace("]", "");
			temp2 = new Vector<String>();
			temp2.add(temp);
			autonomousOutput.set(i+1, temp2);
		}
		
		temp = " , " + teamNumbers.toString();
		temp = temp.replace("[", "");
		temp = temp.replace("]", "");
		temp2 = new Vector<String>();
		temp2.add(temp);
		teleoperatedOutput.set(0, temp2);
		
		for (int i = 0; i < teleoperatedScoreLabels.size() - 1; i++) {
			temp = teleoperatedScoreLabels.get(i+1);
			temp = temp.replace("[", "");
			temp = temp.replace("]", "");
			temp2 = new Vector<String>();
			temp2.add(temp);
			teleoperatedOutput.set(i+1, temp2);
		}
		
		for (int i = 0; i < autonomousScores.size(); i++) autonomousOutput.get(i+1).addAll(autonomousScores.get(i));
		for (int i = 0; i < teleoperatedScores.size(); i++) teleoperatedOutput.get(i+1).addAll(teleoperatedScores.get(i));
		
		try {
			FileUtils.write(ui.autonomousSaveFile, autonomousOutput);
			FileUtils.write(ui.teleoperatedSaveFile, teleoperatedOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Export complete.");
		
	}
	
	private static void getRobotNumbers(UIV2 ui) {
		for (RobotPanel rp : ui.panels) {
			teamNumbers.add(rp.getTeamNumber());
		}
	}
	
	private static void getScoreLabels(UIV2 ui) {
		autonomousScoreLabels.add("");
		teleoperatedScoreLabels.add("");
		
		for (JLabel l : ui.panels.get(0).autonomous.scoreLabels) {
			autonomousScoreLabels.add(l.getText());
		}
		for (JLabel l : ui.panels.get(0).teleoperated.scoreLabels) {
			teleoperatedScoreLabels.add(l.getText());
		}
	}
	
	
	private static void getScores(UIV2 ui) {
		
		// Autonomous
		for (int i = 0; i < ui.panels.get(0).autonomous.scoreFields.size(); i++) {
			String temp = ui.getAutonomousScores(i).toString();
			temp = temp.replace("[", "");
			temp = temp.replace("]", "");
			Vector<String> temp2 = new Vector<String>();
			temp2.add(temp);
			autonomousScores.add(temp2);
		}
		
		// Teleoperated
		for (int i = 0; i < ui.panels.get(0).teleoperated.scoreFields.size(); i++) {
			String temp = ui.getTeleoperatedScores(i).toString();
			temp = temp.replace("[", "");
			temp = temp.replace("]", "");
			Vector<String> temp2 = new Vector<String>();
			temp2.add(temp);
			teleoperatedScores.add(temp2);
		}
	}
	
}
