package elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FileUtils {
	
	public static final File logFile = new File("systemLog.txt");
	
	public static void write(File file, ArrayList<Vector<String>> lines) throws FileNotFoundException {
		
		PrintWriter output = new PrintWriter(file);
		for (int i = 0; i < lines.size(); i++) {
			String temp = lines.get(i).toString();
			temp = temp.replace("[", "");
			temp = temp.replace("]", "");
			output.println(temp);
		}
		output.close();
	}
	
	public static List<String> read(File file) throws IOException {
		
		List<String> lines = Files.readAllLines(file.toPath());
		return lines;
	}
	
	public static void updateSystemLog() throws IOException {
		Vector<String> log = new Vector<String>();
		while (true) {
			try {
				
				String line = System.console().readLine();
				log.add(line);
			} catch (NullPointerException e1) {
				break;
			}
		}
		
		try {
			Files.delete(logFile.toPath());
			Files.createFile(logFile.toPath());
		} catch (NoSuchFileException e) {
			Files.createFile(logFile.toPath()); // FIXME fix this
			PrintWriter output = new PrintWriter(logFile);
			for (int i = 0; i < log.size(); i++) {
				output.println(log.get(i));
			}
			output.close();
		}
	}
	
}
