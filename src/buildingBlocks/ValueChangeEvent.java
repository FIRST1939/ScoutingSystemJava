package buildingBlocks;

public class ValueChangeEvent {
	
	private Object source;
	private String command;
	
	public ValueChangeEvent(Object source, String command) {
		this.source = source;
		this.command = command;
	}

	public Object getSource() {
		return source;
	}

	public String getCommand() {
		return command;
	}

}
