package project;

import org.opencv.core.Mat;

public class StdIdentifierChoice extends CheckBox {

	protected char idChar;
	protected int position;
	public StdIdentifierChoice(PixelPoint corn, Document doc, Window ancestor, PixelPoint a, char idChar, int position) {
		super(corn, doc, ancestor, a);
		
		this.idChar=idChar;
		this.position=position;
		// TODO Auto-generated constructor stub
	}
	public void informIdentifier(Mat img, double divider)
	{
		if(position>10) return;
		if(pixelIsDark(img, divider)) {
			((Identifier)ancestor).recieveBoxData(idChar, position);
			}
	}

}
