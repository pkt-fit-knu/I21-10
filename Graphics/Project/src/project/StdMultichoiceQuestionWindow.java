package project;

public class StdMultichoiceQuestionWindow extends Question {
	private Mark mark;
	private int maxScore;
	private int correctCount=0;
	private int wrongCount=0;
	public StdMultichoiceQuestionWindow(PixelPoint corn, Document doc, Window ancestor, int maxScore)
	{
		super(corn, doc, ancestor);
		this.maxScore=maxScore;
	}
	public Mark getMark()
	{
		if(correctCount>0 && wrongCount==0) return new Mark(maxScore, maxScore);
		else return new Mark(0, maxScore);
	}
	
	public void receiveChoiceResponse(boolean correct)
	{
		
		if(correct) correctCount++;
		else wrongCount++;
		
	}
}
