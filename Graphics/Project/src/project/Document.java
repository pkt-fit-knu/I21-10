package project;

import java.util.ArrayList;

import org.opencv.core.Mat;

public class Document 
{
	protected String studentId;
	{
		windowHeap=new ArrayList<Window>();
	}
	public double getColorDivider(Mat img)
	{
		Window temp;
		double empty = -1;
		double filled= -1;
		for(int i=0;i<windowHeap.size();++i)
		{
			temp=windowHeap.get(i);
			if(temp instanceof EtalonColorCheckBox)
			{
				double tempbright = ((EtalonColorCheckBox)temp).getBrightness(img);
				if(((EtalonColorCheckBox)temp).getIsFilled()) 
					filled=tempbright;
				else empty=tempbright;
			}
		}
		if(empty<0 || filled <0) return 130;
		return (empty+filled)/2;
	}
	public String receiveId()
	{
		Window temp;
		for(int i=0;i<windowHeap.size();++i)
		{
			temp=windowHeap.get(i);
			if(temp instanceof StdIdentifierWindow)
			{
				System.out.println(((StdIdentifierWindow)temp).getId());
			}
		}
		return null;
	}
	ArrayList<Window> windowHeap;
	//public Mat blurredProcessedGrayImg;
	public void addWindow(Window a)
	{
		windowHeap.add(a);
	}
	public void setPerfectAnswers(Mat img, double divider)
	{
		Window temp;
		for(int i=0;i<windowHeap.size();++i)
		{
			temp=windowHeap.get(i);
			if(temp instanceof QuestionChoice)
			{
				((QuestionChoice)temp).readCorrectOption(img, divider);
			}
		}
	}
	public void updateAllQuestions(Mat img, double divider)
	{
		Window temp;
		for(int i=0;i<windowHeap.size();++i)
		{
			temp=windowHeap.get(i);
			if(temp instanceof QuestionChoice)
			{
				((QuestionChoice)temp).reportCorrectness(img, divider);
			}
		}
	}
	public void updateIdentifier(Mat img, double divider)
	{
		Window temp;
		for(int i=0;i<windowHeap.size();++i)
		{
			temp=windowHeap.get(i);
			if(temp instanceof StdIdentifierChoice)
			{
				((StdIdentifierChoice)temp).informIdentifier(img, divider);
			}
		}
	}
	public Mark getTotalMark()
	{
		Mark total=new Mark();
		Window temp;
		for(int i=0;i<windowHeap.size();++i)
		{
			temp=windowHeap.get(i);
			if(temp instanceof Question)
			{
				total=Mark.add(((Question)temp).getMark(), total);
			}
		}
		return total;
	}
	
	
	
}
