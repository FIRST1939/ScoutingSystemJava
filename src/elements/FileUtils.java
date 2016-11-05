package elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
public class FileUtils {
	
	
	 private static final char DEFAULT_SEPARATOR = ',';
	 static FileWriter writer;

	    public static void write(File file, ArrayList<Vector<String>> lines) throws IOException {
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


}
