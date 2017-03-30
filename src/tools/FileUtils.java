package tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
	 * @throws FileNotFoundException Pasted from PrinterWriter - If the given file object does not denote an existing, writable regular file and a new regular file of that name cannot be created, or if some other error occurs while opening or creating the file
	 */
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
	
	public static void writeNewMatch(File file, ArrayList<ArrayList<String>> match) throws IOException{
		try {
			
			for (int i = 0; i<match.size(); i++){
				ArrayList<String> in = match.get(i);
				String str = in.toString();				
				Files.write(file.toPath(), (str+ "\n").getBytes(),StandardOpenOption.APPEND);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void write(File file, List<? extends Object> lines) throws FileNotFoundException {
		PrintWriter output = new PrintWriter(file);
		for (Object o : lines) {
			output.append(o.toString() + "\n");
		}
		output.close();
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
