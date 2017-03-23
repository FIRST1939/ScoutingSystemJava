package buildingBlocks;

import java.awt.Component;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

/**
 * This class manages the values of all the {@link buildingBlocks.ScoreField ScoreFields}, {@link buildingBlocks.ScoreLabel ScoreLabels}, and the {@link buildingBlocks.RobotNumber RobotNumber}.
 * Future subclasses should not need to do anything else but extend this class. 
 * Whenever you add a {@link buildingBlocks.ScoreField ScoreFields}, {@link buildingBlocks.ScoreLabel ScoreLabels}, or a {@link buildingBlocks.RobotNumber RobotNumber} it will automatically
 * add it to the labels and fields and number objects
 * @author Grayson Spidle
 *
 */
public abstract class RobotPanel extends JPanel {
	private static final long serialVersionUID = -5432138303534530552L;
	
	public RobotNumber number = new RobotNumber();
	public List<ScoreLabel> labels;
	public List<ScoreField> fields;
	
	/**
	 * The constructor.
	 */
	public RobotPanel() {
		labels = new Vector<ScoreLabel>();
		fields = new Vector<ScoreField>();
		this.addContainerListener(new ContainerListener() {

			@Override
			public void componentAdded(ContainerEvent arg0) {
				Component c = arg0.getChild();
				if (c.getClass().isAssignableFrom(ScoreLabel.class)) {
					ScoreLabel label = (ScoreLabel) c;
					labels.add(label);
				}
				if (c.getClass().isAssignableFrom(ScoreField.class)) {
					ScoreField field = (ScoreField) c;
					fields.add(field);
				}
				if (c.getClass().isAssignableFrom(RobotNumber.class)) {
					number = (RobotNumber) c;
				}
			}

			@Override
			public void componentRemoved(ContainerEvent arg0) {
				Component c = arg0.getChild();
				if (c.getClass().isAssignableFrom(ScoreLabel.class)) {
					labels.remove((ScoreLabel) c);
				}
				else if (c.getClass().isAssignableFrom(ScoreField.class)) {
					fields.remove((ScoreField) c);
				}
				else if (c.getClass().isAssignableFrom(RobotNumber.class)) {
					number = null;
				}
			}
			
		});
	}
	
}
