package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 * The utilities for writing and reading files. 
 * @author Grayson Spidle
 *
 */
public class FileUtils {
	
	/**
	 * Writes the specified list to the specified file.
	 * @param file The specified file.
	 * @param lines The specified list of Strings to write.
	 * @param option The desired method of writing the given data
	 * @throws IOException 
	 */
	public static void write(File file, List<List<String>> lines, StandardOpenOption option) throws IOException {
		// TODO Test this 
		StringBuilder sb = new StringBuilder();
		for (List<String> ls : lines) {
			for (String s : ls) {
				sb.append(s + ",");
			}
			sb = new StringBuilder(sb.substring(0, sb.length() - 2));
			sb.append(System.lineSeparator());
		}
		if (file.exists()) Files.write(file.toPath(), sb.toString().getBytes(), option);
		else Files.write(file.toPath(), sb.toString().getBytes(), StandardOpenOption.CREATE_NEW);
//		PrintWriter output = new PrintWriter(file);
//		for (int i = 0; i < lines.size(); i++) {
//			String temp = lines.get(i).toString();
//			temp = temp.replace("[", "");
//			temp = temp.replace("]", "");
//			output.println(temp);
//		}
//		output.close();
	}
	
	/**
	 * Use the write method instead
	 * @param file
	 * @param match
	 * @throws IOException
	 */
	@Deprecated
	public static void writeNewMatch(File file, ArrayList<ArrayList<String>> match) throws IOException{
		try {
			for (int i = 0; i<match.size(); i++){
				ArrayList<String> in = match.get(i);
				String str = in.toString();				
				Files.write(file.toPath(), (str+ "\n").getBytes(),StandardOpenOption.APPEND);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads and returns the contents of the specified file.
	 * @param file The specified file.
	 * @return Returns a List.
	 * @throws IOException Pasted from Files.readAllLines() -  if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read.
	 */
	public static List<String> read(File file) throws IOException {
		List<String> lines = Files.readAllLines(file.toPath());
		return lines;
	}
	

}
