package elements;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import java.awt.Window.Type;
import javax.swing.BoxLayout;
import java.awt.Component;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Dimension;
import java.awt.Frame;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class UIV2 extends JFrame {

	private JPanel contentPane;
	private JTextField score1Field;
	private JTextField score2Field;
	private JTextField score3Field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIV2 frame = new UIV2();
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
	public UIV2() {
		setName("mainFrame");
		setSize(new Dimension(500, 300));
		setType(Type.NORMAL);
		setAlwaysOnTop(true);
		setTitle("Scouting Program - Autonomous");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 565);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCompetition = new JMenu("Competition");
		menuBar.add(mnCompetition);
		
		JMenuItem mntmStartMatch = new JMenuItem("Start Match");
		mnCompetition.add(mntmStartMatch);
		
		JMenuItem mntmReset = new JMenuItem("Reset");
		mnCompetition.add(mntmReset);
		
		JMenu mnExport = new JMenu("Export");
		menuBar.add(mnExport);
		
		JMenuItem mntmTocsv = new JMenuItem("to .csv");
		mnExport.add(mntmTocsv);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmTeleoperated = new JMenuItem("Teleoperated");
		mntmTeleoperated.setHorizontalAlignment(SwingConstants.TRAILING);
		mnView.add(mntmTeleoperated);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3, 1, 1));
		
		JPanel robot1 = new JPanel();
		robot1.setBorder(new LineBorder(new Color(0, 0, 0)));
		robot1.setBackground(Color.RED);
		contentPane.add(robot1);
		FormLayout fl_robot1 = new FormLayout(new ColumnSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("46px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("40px"),
				ColumnSpec.decode("49px"),
				ColumnSpec.decode("91px"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("20px"),});
		robot1.setLayout(fl_robot1);
		
		JLabel name1 = new JLabel("Team 1");
		name1.setHorizontalAlignment(SwingConstants.CENTER);
		name1.setFont(new Font("Tahoma", Font.BOLD, 15));
		robot1.add(name1, "2, 2, 5, 1, fill, fill");
		
		JLabel score1 = new JLabel("score1");
		score1.setHorizontalAlignment(SwingConstants.LEFT);
		score1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		robot1.add(score1, "2, 6, fill, center");
		
		JLabel score2 = new JLabel("score2");
		score2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		score2.setHorizontalAlignment(SwingConstants.LEFT);
		robot1.add(score2, "2, 8, fill, center");
		
		JLabel score3 = new JLabel("score3");
		score3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		robot1.add(score3, "2, 10, fill, center");
		
		score1Field = new JTextField();
		score1Field.setHorizontalAlignment(SwingConstants.TRAILING);
		score1Field.setFont(new Font("Tahoma", Font.PLAIN, 15));
		score1Field.setText("0");
		score1Field.setEditable(false);
		robot1.add(score1Field, "4, 6, fill, fill");
		score1Field.setColumns(10);
		
		score2Field = new JTextField();
		score2Field.setFont(new Font("Tahoma", Font.PLAIN, 15));
		score2Field.setText("0");
		score2Field.setHorizontalAlignment(SwingConstants.RIGHT);
		score2Field.setEditable(false);
		robot1.add(score2Field, "4, 8, fill, fill");
		score2Field.setColumns(10);
		
		score3Field = new JTextField();
		score3Field.setEditable(false);
		score3Field.setHorizontalAlignment(SwingConstants.RIGHT);
		score3Field.setFont(new Font("Tahoma", Font.PLAIN, 15));
		score3Field.setText("0");
		robot1.add(score3Field, "4, 10, fill, fill");
		score3Field.setColumns(10);
		
		JPanel robot2 = new JPanel();
		robot2.setBorder(new LineBorder(new Color(0, 0, 0)));
		robot2.setBackground(Color.RED);
		contentPane.add(robot2);
		robot2.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("97px"),
				ColumnSpec.decode("54px"),},
			new RowSpec[] {
				RowSpec.decode("58px"),
				RowSpec.decode("19px"),}));
		
		JLabel name2 = new JLabel("Team 2");
		name2.setHorizontalAlignment(SwingConstants.CENTER);
		name2.setFont(new Font("Tahoma", Font.BOLD, 15));
		robot2.add(name2, "2, 1, left, top");
		
		JPanel robot3 = new JPanel();
		robot3.setBorder(new LineBorder(new Color(0, 0, 0)));
		robot3.setBackground(Color.RED);
		contentPane.add(robot3);
		robot3.setLayout(null);
		
		JLabel name3 = new JLabel("Team 3");
		name3.setHorizontalAlignment(SwingConstants.CENTER);
		name3.setBounds(85, 6, 60, 14);
		name3.setFont(new Font("Tahoma", Font.BOLD, 15));
		robot3.add(name3);
		
		JPanel robot4 = new JPanel();
		robot4.setBorder(new LineBorder(new Color(0, 0, 0)));
		robot4.setBackground(Color.BLUE);
		contentPane.add(robot4);
		robot4.setLayout(null);
		
		JLabel name4 = new JLabel("Team 4");
		name4.setHorizontalAlignment(SwingConstants.CENTER);
		name4.setFont(new Font("Tahoma", Font.BOLD, 15));
		name4.setBounds(85, 6, 60, 14);
		robot4.add(name4);
		
		JPanel robot5 = new JPanel();
		robot5.setBorder(new LineBorder(new Color(0, 0, 0)));
		robot5.setBackground(Color.BLUE);
		contentPane.add(robot5);
		robot5.setLayout(null);
		
		JLabel lblName_4 = new JLabel("Team 5");
		lblName_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblName_4.setBounds(85, 6, 60, 14);
		robot5.add(lblName_4);
		
		JPanel robot6 = new JPanel();
		robot6.setBorder(new LineBorder(new Color(0, 0, 0)));
		robot6.setBackground(Color.BLUE);
		contentPane.add(robot6);
		robot6.setLayout(null);
		
		JLabel lblName_5 = new JLabel("Team 6");
		lblName_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblName_5.setBounds(100, 6, 32, 14);
		robot6.add(lblName_5);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{robot1, name1, score1, score2, score3, score1Field, score2Field, score3Field, robot2, name2, robot3, name3, robot4, name4, robot5, lblName_4, robot6, lblName_5}));
		
	}
}
