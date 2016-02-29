package project;

import org.opencv.core.Mat;

public class EtalonColorCheckBox extends CheckBox {
	protected boolean isFilled;
	public boolean getIsFilled()
	{
		return isFilled;
	}
	public EtalonColorCheckBox(PixelPoint corn, Document doc, Window ancestor, PixelPoint a, boolean isFilled) {
		super(corn, doc, ancestor, a);
		this.isFilled=isFilled;
		// TODO Auto-generated constructor stub
	}
	public double getBrightness(Mat img)
	{
		PixelPoint temp=AbsReadingLocation();
		double q[]=img.get(temp.getAbsY(img), temp.getAbsX(img));
		return q[0];
	}
	

}
