package buildingBlocks;

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
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.google.gson.stream.JsonReader;

public class Teamimports extends JFrame {

	static String matchKey = "";
	static Object result;
	static final String EVENT_URL_BASE = "https://www.thebluealliance.com/api/v2/event/";
	static final String MATCH_URL_BASE = "https://www.thebluealliance.com/api/v2/match/";
	static final String URL_END = "?X-TBA-App-Id=frc1939:ScoutingSystem:v2";
	private JPanel contentPane;
	private JTextField textField;
	protected File defaultSaveFile = new File((System.getProperty("user.home") + System.getProperty("file.separator") + "Desktop"));
	List<String> output;
	

	/**
	 * Launch the application.
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Teamimports frame = new Teamimports();
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
	public Teamimports() {
		setForeground(Color.WHITE);
		setTitle("MEMER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 42, 414, 209);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
		});
		textField.setBounds(89, 11, 256, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addMatch();
				textField.setText("");
						}
		});
		
		btnNewButton.setBounds(10, 8, 69, 23);
		contentPane.add(btnNewButton);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(355, 8, 69, 23);
		contentPane.add(btnSave);
	}
	private String listToString(){
		String out = "";
		for (int i = 0; i<output.size(); i++){
			out = out + output.get(i);
		}
		return out;
		
	}
	private final void addMatch(){
		ArrayList<String> the = new ArrayList<String>();
		try {
			the = (ArrayList<String>) getTeamNumbers(textField.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String adding = the.get(0) + ","+  the.get(1) + ","+the.get(2) + ","+the.get(3) + ","+the.get(4) + ","+the.get(5) + ",";
		output.add(adding);
	}
	private file
	private final void write(){
		try {
			PrintWriter PW = new PrintWriter(this);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	private static JsonReader createMatchJsonReader(String matchId) throws IOException {
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
	private static List<String> readTeamNumbers(JsonReader reader) throws IOException {
		List<String> output = new Vector<String>();
		
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
	private static List<String> readAlliances(JsonReader reader) throws IOException {
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
	
	
	private static List<String> readAlliance(JsonReader reader) throws IOException {
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

	private static List<String> readTeams(JsonReader reader) throws IOException {
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
		
		Vector<String> temp = new Vector<String>();
		temp.add(nums.get(3));
		temp.add(nums.get(4));
		temp.add(nums.get(5));
		temp.add(nums.get(0));
		temp.add(nums.get(1));
		temp.add(nums.get(2));
		return temp;
	}
}
