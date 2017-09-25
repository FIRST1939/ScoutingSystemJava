package buildingBlocks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class modifies the {@link buildingBlocks.UIV3 UI} according to inputs from a controller.
 * Future subclasses must define an {@link java.awt.event.ActionListener ActionListener} for autonomous and teleoperated modes. 
 * @author Grayson Spidle
 *
 */
public abstract class ControlScheme implements ActionListener {
	
	public static UIV3 ui;
	public ActionListener autonomous;
	public ActionListener teleoperated;
	
	public static ActionEvent POLL_AUTONOMOUS_CONTROLS;
	public static ActionEvent POLL_TELEOPERATED_CONTROLS;
	
	public ControlScheme(UIV3 arg0) {
		ui = arg0;
		POLL_AUTONOMOUS_CONTROLS = new ActionEvent(ui, ActionEvent.ACTION_PERFORMED, "poll autonomous controls");
		POLL_TELEOPERATED_CONTROLS = new ActionEvent(ui, ActionEvent.ACTION_PERFORMED, "poll teleoperated controls");
		autonomous = null;
		teleoperated = null;
	}
	
	/**
	 * The constructor.
	 * @param arg0 The UI the ControlScheme should modify.
	 */
	public ControlScheme(UIV3 arg0, ActionListener autonomous, ActionListener teleoperated) {
		ui = arg0;
		POLL_AUTONOMOUS_CONTROLS = new ActionEvent(ui, ActionEvent.ACTION_PERFORMED, "poll autonomous controls");
		POLL_TELEOPERATED_CONTROLS = new ActionEvent(ui, ActionEvent.ACTION_PERFORMED, "poll teleoperated controls");
		this.autonomous = autonomous;
		this.teleoperated = teleoperated;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.equals(POLL_AUTONOMOUS_CONTROLS)) {
			autonomous.actionPerformed(e);
		} else if (e.equals(POLL_TELEOPERATED_CONTROLS)) {
			teleoperated.actionPerformed(e);
		} else {
			System.err.println("Unable to obtain the controls. Use the ActionEvents provided by the class.");
		}
	}
	
	public void setAutonomousActionListener(ActionListener arg0) {
		autonomous = arg0;
	}
	
	public void setTeleoperatedActionListener(ActionListener arg0) {
		teleoperated = arg0;
	}
	
}
