package project;

public abstract class Question extends Window {
	abstract void receiveChoiceResponse(boolean correct);
	abstract Mark getMark();
	
	public Question(PixelPoint corn, Document doc, Window ancestor)
	{
		super(corn, doc, ancestor);
	}
	
}
