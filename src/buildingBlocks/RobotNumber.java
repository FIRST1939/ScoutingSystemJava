package buildingBlocks;

import javax.swing.JLabel;

/**
 * This class is used to help other processes get the correct inputs. It is a subclass of {@link javax.swing.JLabel JLabel}  
 * Implement this class for robot numbers in future subclasses of {@link buildingBlocks.RobotPanel RobotPanel}.
 * @author Grayson Spidle
 *
 */
public final class RobotNumber extends JLabel {
	
	private static final long serialVersionUID = 1L;

	public RobotNumber() {
		super("" + null);
		this.putClientProperty("JComponent.sizeVariant", "large");
	}
	
	public RobotNumber(String arg0) {
		super(arg0);
		this.putClientProperty("JComponent.sizeVariant", "large");
	}

}
