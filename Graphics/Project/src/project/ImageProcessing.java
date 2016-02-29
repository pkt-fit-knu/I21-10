package project;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public final class ImageProcessing 
{
	public static boolean getPixelBinaryValue(Mat img,PixelPoint a)
	{
		double q[];
		q=img.get(a.getAbsY(img), a.getAbsX(img));
		return q[0]<=110 ? true : false;
		
	}
	public static boolean getPixelBinaryValue(Mat img,PixelPoint a, double divider)
	{
		double q[];
		q=img.get(a.getAbsY(img), a.getAbsX(img));
		return q[0]<=divider ? true : false;
		
	}
	public static void drawGrid(Mat img,int num)
	{
		for(int i=1;i<num;++i)
		{
			Imgproc.line(img, new Point(0,((double)i/num)*(img.rows())),
					new Point(img.cols()-1,((double)i/num)*(img.rows())), new Scalar(200));
			
			Imgproc.line(img, new Point(((double)i/num)*(img.cols()),0),
					new Point(((double)i/num)*(img.cols()),img.rows()), new Scalar(200));
			Imgproc.putText(img,Double.toString(((double)i/num)),new Point(0,((double)i/num)*(img.rows()-1)),1,0.8,new Scalar(100));
			Imgproc.putText(img,Double.toString(((double)i/num)),
					new Point(((double)i/num)*(img.cols()),((double)0.5/num)*(img.cols())),1,0.8,new Scalar(100));
		}
	}
	public static Mat focusOnSheet(Mat img)
	{
		int cannyParam =25;
		
		while(cannyParam <256)
		{	
			Mat sheetPic = applyAffine(img,cannyParam);
			
			if(sheetPic != null) 
				return sheetPic;	
			
			cannyParam += 40;
		}
		
		return null;
	}
	
	private ImageProcessing()
	{ }
	
	private static Point getCorner(Mat img, boolean left, boolean top)
	{
		Point cornerPoint = null;
		boolean isFound = false;
		int col;
		int row;
		double[] q;
		
		for(int i = 0; i < img.rows() + img.cols(); ++i)
		{
			for(int k = 0; k <=i; ++k)
			{
				col = left ? k : img.cols() - k - 1;
				row = top ? i - k : img.rows() - i + k - 1;
				
				if(col >= 0 && col < img.cols() && row >= 0 && row < img.rows())
				{
					q = img.get(row, col);
					
					if(q[0] < 1) 
						continue;
					
					cornerPoint = new Point(col, row);
					isFound = true;
					
					break;
				}
			}
			
			if(isFound) 
				break;
		}
		
		return cornerPoint;
	}
	
	private static Mat applyAffine(Mat img, int cannyParam ) 
	{
		
		
		Mat blurredImage = new Mat();
		Mat initialImageRGB =img;
		Mat initialImageGray = new Mat();
		
		Imgproc.cvtColor(initialImageRGB, initialImageGray, Imgproc.COLOR_RGB2GRAY);
		
		Mat edgesImage = new Mat();
		
		Imgproc.GaussianBlur(initialImageGray, blurredImage, new Size(5,5), 0);
		
		Imgproc.Canny(blurredImage, edgesImage, cannyParam, cannyParam * 3, 3, true);
		
		Point p1 = getCorner(edgesImage, true, true);
		Point p2 = getCorner(edgesImage, false, true);
		Point p3 = getCorner(edgesImage, true, false);
		
		
		if(p1 == null || p2 == null || p3 == null)
		{
			return null;
		}
		
		if((p2.x - p1.x) * 10 < initialImageRGB.cols()) 
			return null;
		if((p3.y - p1.y) * 10 < initialImageRGB.rows()) 
			return null;
		
		MatOfPoint2f a = new MatOfPoint2f(new Point(0, 0), new Point(blurredImage.cols() - 1, 0), new Point(0, blurredImage.rows() - 1));
		MatOfPoint2f b = new MatOfPoint2f(p1, p2, p3);
		
		Mat finalImageRGB = new Mat();
		Mat finalImageGray=new Mat();
		
		Mat affineTransform= Imgproc.getAffineTransform(b,a);
		
		Imgproc.warpAffine(blurredImage, finalImageGray, affineTransform, blurredImage.size());
		
		//ImageIO.DisplayMat(finalImageGray);
		
		return finalImageGray;
	}
}
