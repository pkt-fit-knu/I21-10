package project;

import org.opencv.core.Mat;

public class QuestionChoice extends CheckBox {

	public QuestionChoice(PixelPoint corn, Document doc, Window ancestor, PixelPoint a, boolean b) {
		super(corn, doc, ancestor, a);
		filledIsCorrect=b;
		// TODO Auto-generated constructor stub
	}
	private boolean filledIsCorrect=false;
	public boolean isCorrect(boolean filled)
	{
		if(filled==filledIsCorrect) return true;
		else return false;
	}
	public void setCorrectOption(boolean filled)
	{
		filledIsCorrect=filled;
	}
	public void readCorrectOption(Mat img, double divider)
	{
		filledIsCorrect=pixelIsDark(img, divider);
	}
	public void reportCorrectness(Mat img, double divider)
	{
		//System.out.println(filled);
		((Question)ancestor).receiveChoiceResponse(isCorrect(pixelIsDark(img, divider)));
	}

}
