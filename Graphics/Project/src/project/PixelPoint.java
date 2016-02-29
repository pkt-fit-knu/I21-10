package project;

import org.opencv.core.Mat;

public class PixelPoint {
	public PixelPoint(double a, double b)
	{
		x=a;
		y=b;
		while(x>1) x-=1;
		while(x<0) x+=1;
		while(y>1) y-=1;
		while(y<0) y+=1;
	}
	public double x=0;
	public double y=0;
	public static PixelPoint add(PixelPoint a, PixelPoint b)
	{
		PixelPoint temp= new PixelPoint(a.x+b.x,a.y+b.y);
		while(temp.x>1) temp.x-=1;
		while(temp.x<0) temp.x+=1;
		while(temp.y>1) temp.y-=1;
		while(temp.y<0) temp.y+=1;
		return temp;
		
	}
	public int getAbsX(Mat img)
	{
		int temp=(int)(x*img.cols());
		if(temp==img.cols()) --temp;
		return temp;
		
		
	}
	public int getAbsY(Mat img)
	{
		int temp=(int)(y*img.rows());
		if(temp==img.rows()) --temp;
		return temp;
	}
	
}
