package project;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class CheckBox extends Window 
{
	
	protected PixelPoint readingLocation;
	public CheckBox(PixelPoint corn, Document doc, Window ancestor,PixelPoint a)
	{
		super(corn, doc, ancestor);
		readingLocation=a;
	}
	
	public boolean pixelIsDark(Mat img)
	{

		PixelPoint checkTarget=AbsReadingLocation();
		
		boolean filled=ImageProcessing.getPixelBinaryValue(img, checkTarget);
		return filled;
	}
	public boolean pixelIsDark(Mat img, double divider)
	{

		PixelPoint checkTarget=AbsReadingLocation();
		
		boolean filled=ImageProcessing.getPixelBinaryValue(img, checkTarget, divider);
		return filled;
	}
	public PixelPoint AbsReadingLocation()
	{
		return PixelPoint.add(getAbsoluteCorner(), readingLocation);
	}
	public void visualizeReadingLocation(Mat img)
	{
		Imgproc.circle(img, new Point((double)AbsReadingLocation().getAbsX(img),
				(double)AbsReadingLocation().getAbsY(img)), 10, new Scalar(180));
		
		
	}
	
	
	
	
}
