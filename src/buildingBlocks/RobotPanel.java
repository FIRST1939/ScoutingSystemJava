package buildingBlocks;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.Vector;

import javax.swing.JPanel;

public abstract class RobotPanel extends JPanel {
	private static final long serialVersionUID = -5432138303534530552L;
	
	private int panelNumber = -1;
	
	public RobotNumber number = new RobotNumber();
	public Vector<ScoreLabel> labels = new Vector<ScoreLabel>();
	public Vector<ScoreField> fields = new Vector<ScoreField>();
	
	/**
	 * The constructor.
	 */
	public RobotPanel() {
		this.addContainerListener(new ContainerListener() {

			@Override
			public void componentAdded(ContainerEvent arg0) {
				if (arg0.getChild().getClass().equals(ScoreLabel.class)) {
					labels.add((ScoreLabel) arg0.getChild());
				}
				else if (arg0.getChild().getClass().equals(ScoreField.class)) {
					fields.add((ScoreField) arg0.getChild());
				}
				else if (arg0.getChild().getClass().equals(RobotNumber.class)) {
					number = (RobotNumber) arg0.getChild();
				}
			}

			@Override
			public void componentRemoved(ContainerEvent arg0) {
				if (arg0.getChild().getClass().equals(ScoreLabel.class)) {
					labels.remove((ScoreLabel) arg0.getChild());
				}
				else if (arg0.getChild().getClass().equals(ScoreField.class)) {
					fields.remove((ScoreField) arg0.getChild());
				}
				else if (arg0.getChild().getClass().equals(RobotNumber.class)) {
					number = null;
				}
			}
			
		});
	}
	
}
