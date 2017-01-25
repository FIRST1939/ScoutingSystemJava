package buildingBlocks;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


/**
 * Good Luck!
 * @author Paul Case
 *
 */
public class Names extends JFrame implements KeyListener {

	private JPanel contentPane;
	private JTextField txtNewEvent;

	public Names() {
		setTitle("Name?");
		setType(Type.POPUP);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 366, 129);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtNewEvent = new JTextField();
		txtNewEvent.addKeyListener(this);
		txtNewEvent.setText("New Event");
		txtNewEvent.setToolTipText("New Event");
		txtNewEvent.setBounds(10, 36, 330, 20);
		contentPane.add(txtNewEvent);
		txtNewEvent.setColumns(10);
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
			TeamImport.name = txtNewEvent.getText();
			System.out.println("New Name is: " + TeamImport.name);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
