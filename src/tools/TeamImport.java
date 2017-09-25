package tools;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.stream.JsonReader;

public class TeamImport extends JFrame {

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	static String matchKey = "";
	static Object result;
	static final String EVENT_URL_BASE = "https://www.thebluealliance.com/api/v2/event/";
	static final String MATCH_URL_BASE = "https://www.thebluealliance.com/api/v2/match/";
	static final String URL_END = "?X-TBA-App-Id=frc1939:ScoutingSystem:v2";
	public JPanel contentPane;
	public JTextField textField;
	protected File defaultSaveFile = new File((System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));
	ArrayList<String> output = new ArrayList<String>();
	public static String name = "";
	public Names N = new Names();
	public JTextField textField_1;
	public int matchTotal;

	/**
	 * Launch the application.
	 */
	public final File getEvent() {
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamImport frame = new TeamImport();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TeamImport() {
		setForeground(Color.WHITE);
		setTitle("Import");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				addFullEvent();
				}
			}
		});
		textField.setBounds(89, 11, 256, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Edit Name");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				N = new Names();
				N.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 8, 69, 23);
		contentPane.add(btnNewButton);
		
		JButton btnSave = new JButton("Save");
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				write(btnSave);
			}
		});
		btnSave.setBounds(355, 8, 69, 23);
		contentPane.add(btnSave);
		
		JLabel lblMatchCount = new JLabel("Match Count");
		lblMatchCount.setBounds(20, 45, 69, 14);
		contentPane.add(lblMatchCount);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					matchTotal = Integer.parseInt(textField_1.getText());
					
					System.out.println("Match count is: " + matchTotal);
				}
			}
		});
		textField_1.setBounds(89, 42, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
	}
	public void addFullEvent(){
		for (int i = 1; i<= matchTotal; i++){
			String path = textField.getText() + i;
			addMatch(path);
			
		}
	}
	public void addMatch(String path){
		List<String> the = new ArrayList<String>();
		try {
			the =  getTeamNumbers(path);
			String adding = "";
			for (int i = 0; i<the.size(); i++){
				if(i == the.size()-1){
					adding = adding +the.get(i);
				}
				else{
				adding = adding +the.get(i) + ".";
				}
				
			}
			
			System.out.println("Successfully got: " + adding);
			 
			output.add(adding);
			System.out.println(output.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public final void write(JButton ep){
		
		File file = new File(this.defaultSaveFile+"\\"+name + ".txt");
		
		System.out.println("File at: " + file.getAbsolutePath());
		
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			String outpp = output.toString();
			StringBuilder b = new StringBuilder();
			for (String s : output) {
				b.append(s + "\n");
			}
			outpp = b.toString();
			Files.write(file.toPath(), outpp.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}	
	
	public static JsonReader createMatchJsonReader(String matchId) throws IOException {
		try {
			URL url = new URL(MATCH_URL_BASE + matchId + URL_END);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows 10; WOW64; rv:25.0) Gecko/20100101 Chrome/51.0.2704.103");
			connection.connect();
			JsonReader reader = new JsonReader(new BufferedReader(new InputStreamReader(connection.getInputStream())));
			
			return reader;			
		} catch (MalformedURLException e) {
			System.err.println("Cannot create the url");
		} catch (UnknownHostException e) {
			System.err.println("Cannot connect to www.thebluealliance.com");
		} catch (IOException e) {
			throw e;
		}
		return null;
	}
	public static List<String> readTeamNumbers(JsonReader reader) throws IOException {
		List<String> output = new ArrayList<String>();
		
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("alliances")) {
				output.addAll(readAlliances(reader));
			}
			else {
				reader.skipValue();
			}
		}
		reader.endObject();
		
		return output;
	}
	
	public static List<String> readAlliances(JsonReader reader) throws IOException {
		List<String> output = new Vector<String>();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("blue")) {
				output.addAll(readAlliance(reader));
			}
			else if (name.equals("red")) {
				output.addAll(readAlliance(reader));
			}
			else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}	
	
	
	public static List<String> readAlliance(JsonReader reader) throws IOException {
		List<String> output = new Vector<String>();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("teams")) {
				output = readTeams(reader);
			}
			else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return output;
	}

	public static List<String> readTeams(JsonReader reader) throws IOException {
		Vector<String> output = new Vector<String>();
		reader.beginArray();
		while (reader.hasNext()) {
			output.add(reader.nextString());
		}
		reader.endArray();
		return output;
	}
	public static List<String> getTeamNumbers(String matchId) throws IOException {
		List<String> nums = null;
		try {
			JsonReader reader = createMatchJsonReader(matchId);
			nums = readTeamNumbers(reader);
			
		} catch (MalformedURLException e) {
			System.err.println("Cannot create the url");
		} catch (UnknownHostException e) {
			System.err.println("Cannot connect to www.thebluealliance.com");
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		
		for (int i = 0; i < nums.size(); i++) {
			nums.set(i, nums.get(i).replace("frc", ""));
		}
		
		Vector<String> output = new Vector<String>();
		output.add(nums.get(3));
		output.add(nums.get(4));
		output.add(nums.get(5));
		output.add(nums.get(0));
		output.add(nums.get(1));
		output.add(nums.get(2));
		return output;
	}
}
