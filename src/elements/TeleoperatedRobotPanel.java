package elements;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class TeleoperatedRobotPanel extends JPanel {
	
	public boolean areEditable = false;
	
	public Font nameFont = new Font("Tahoma", Font.BOLD, 15);
	public Font scoreLabelFont = new Font("Tahoma", Font.PLAIN, 15);
	public Font scoreFieldFont = new Font("Tahoma", Font.PLAIN, 15);
	
	public JTextField score1Field;
	public JTextField score2Field;
	public JTextField score3Field;
	public JTextField score4Field;
	
	public JLabel name = new JLabel();
	public JLabel score1 = new JLabel();
	public JLabel score2 = new JLabel();
	public JLabel score3 = new JLabel();
	public JLabel score4 = new JLabel();
	
	public Vector<JLabel> scoreLabels = new Vector<JLabel>();
	public Vector<JTextField> scoreFields = new Vector<JTextField>();
	
	public TeleoperatedRobotPanel(String teamName, Color teamColor) {
		
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setBackground(teamColor);
		this.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px:grow"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("84px"),
				FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,},
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
				RowSpec.decode("20px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		
		name = new JLabel(teamName);
		name.setSize(this.getWidth(), 14);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(nameFont);
		this.add(name, "2, 2, 9, 1, center, fill");
		
		score1 = new JLabel("score1");
		score1.setHorizontalAlignment(SwingConstants.LEFT);
		score1.setFont(scoreLabelFont);
		this.add(score1, "2, 6, fill, center");
		
		score2 = new JLabel("score2");
		score2.setHorizontalAlignment(SwingConstants.LEFT);
		score2.setFont(scoreLabelFont);
		this.add(score2, "2, 8, fill, center");
		
		score3 = new JLabel("score3");
		score3.setHorizontalAlignment(SwingConstants.LEFT);
		score3.setFont(scoreLabelFont);
		this.add(score3, "2, 10, fill, center");
		
		score4 = new JLabel("score4");
		score4.setFont(scoreLabelFont);
		this.add(score4, "2, 12, fill, center");
		
		score1Field = new JTextField();
		score1Field.setHorizontalAlignment(SwingConstants.TRAILING);
		score1Field.setFont(scoreFieldFont);
		score1Field.setText("0");
		score1Field.setEditable(false);
		this.add(score1Field, "4, 6, fill, fill");
		score1Field.setColumns(10);
		
		score2Field = new JTextField();
		score2Field.setFont(scoreFieldFont);
		score2Field.setText("0");
		score2Field.setHorizontalAlignment(SwingConstants.RIGHT);
		score2Field.setEditable(false);
		this.add(score2Field, "4, 8, fill, fill");
		score2Field.setColumns(10);
		
		score3Field = new JTextField();
		score3Field.setEditable(false);
		score3Field.setHorizontalAlignment(SwingConstants.RIGHT);
		score3Field.setFont(scoreFieldFont);
		score3Field.setText("0");
		this.add(score3Field, "4, 10, fill, fill");
		score3Field.setColumns(10);
		
		score4Field = new JTextField();
		score4Field.setEditable(false);
		score4Field.setText("0");
		score4Field.setHorizontalAlignment(SwingConstants.RIGHT);
		score4Field.setFont(scoreFieldFont);
		this.add(score4Field, "4, 12, fill, fill");
		score4Field.setColumns(10);
		
		scoreLabels.add(score1);
		scoreLabels.add(score2);
		scoreLabels.add(score3);
		scoreLabels.add(score4);
		
		scoreFields.add(score1Field);
		scoreFields.add(score2Field);
		scoreFields.add(score3Field);
		scoreFields.add(score4Field);
	}
}
