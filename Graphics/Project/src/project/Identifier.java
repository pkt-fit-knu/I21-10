package project;

public abstract class Identifier extends Window {
	public Identifier(PixelPoint corn, Document doc, Window ancestor) {
		super(corn, doc, ancestor);
		// TODO Auto-generated constructor stub
	}

	abstract public String getId();
	abstract public void recieveBoxData(char data, int position);
	
}
