package project;

import org.opencv.core.Core;
import java.util.ArrayList;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

import project.ImageIO;
import project.ImageProcessing;

public class Project
{
	public static void main( String[] args )
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Mat q= Imgcodecs.imread("src/resources/many-tilted-squares.png");
		Mat y= ChangeRes(q,50,50);
		System.out.println(q.get(0, 0)[0]+" "+ y.get(0,0)[0]);
		ImageIO.DisplayMat(y);
	}
	public static Mat ChangeRes(Mat a, int finl, int finh)
	{
		Mat ans=Mat.zeros(finh, finl, a.type());
		
		double zoomh=(double)finh/(double)a.height();
		double zooml=(double)finl/(double)a.cols();
		
		for(int i=0;i<finh;++i)
		{
			for(int k=0;k<finl;++k)
			{
				Boolean inhor=false, inver=false;
				double legy=((double)i/(double)zoomh);
				if(Math.abs(Math.round(legy)-legy)<0.0001)
				{
					inhor=true;
				}
				double legx=((double)k/(double)zooml);
				if(Math.abs(Math.round(legx)-legx)<0.0001)
				{
					inver=true;
				}
				try{
				if(inhor && inver)
				{
					double[] col=a.get((int)Math.round(legx), (int)Math.round(legy));
					System.out.println(i+"exact"+k);
					double[] res= new double[3];
					res[0]=col[0];
					res[1]=col[1];
					res[2]=col[2];
					ans.put(i,k, res);
					
					
				}
				else if(inhor)
				{
					Point p1=new Point((int)Math.floor(legx), (int)Math.round(legy));
					Point p2=new Point((int)Math.ceil(legx), (int)Math.round(legy));
					double[] p1col=a.get((int)p1.y,(int) p1.x);
					double[] p2col=a.get((int)p2.y,(int) p2.x);
					
					double[] res=new double[3];
					
					for(int z=0;z<3;++z)
					{
						res[z]=p1col[z]+(p2col[z]-p1col[z])*(legx-p1.x)/(p2.x-p1.x);
					}
					ans.put(i, k, res);
					
				}
				else if(inver)
				{
					Point p1=new Point((int)Math.round(legx), (int)Math.floor(legy));
					Point p2=new Point((int)Math.round(legx), (int)Math.ceil(legy));
					double[] p1col=a.get((int)p1.y,(int) p1.x);
					double[] p2col=a.get((int)p2.y,(int) p2.x);
					
					double[] res=new double[3];
					
					for(int z=0;z<3;++z)
					{
						res[z]=p1col[z]+(p2col[z]-p1col[z])*(legy-p1.y)/(p2.y-p1.y);
					}
					ans.put(i, k, res);
				}
				else
				{
					Point p1=new Point((int)Math.floor(legx), (int)Math.floor(legy));///1 3
					Point p2=new Point((int)Math.floor(legx), (int)Math.ceil(legy));//  2 4
					Point p3=new Point((int)Math.ceil(legx), (int)Math.floor(legy));
					Point p4=new Point((int)Math.ceil(legx), (int)Math.ceil(legy));
					double[] p1col=a.get((int)p1.y,(int) p1.x);
					double[] p2col=a.get((int)p2.y,(int) p2.x);
					double[] p3col=a.get((int)p3.y,(int) p1.x);
					double[] p4col=a.get((int)p4.y,(int) p2.x);
					Point p5=new Point(legx,p1.y);
					Point p6=new Point(legx,p2.y);
					double[] res=new double[3];
					
					for(int z=0;z<3;++z)
					{
						res[z]=p1col[z]+(p3col[z]-p1col[z])*(legx-p1.x)/(p3.x-p1.x);
					}
					double[] p5col=res.clone();
					for(int z=0;z<3;++z)
					{
						res[z]=p2col[z]+(p4col[z]-p2col[z])*(legx-p2.x)/(p4.x-p2.x);
					}
					double[] p6col=res.clone();
					for(int z=0;z<3;++z)
					{
						res[z]=p5col[z]+(p6col[z]-p5col[z])*(legy-p5.y)/(p6.y-p5.y);
					}
					ans.put(i, k, res);
				}
				}
				catch(Exception e)
				{
					double[] m=new double[3];
					System.out.println(i+" caught "+ k);
					m[0]=0;
					m[1]=0;
					m[2]=0;
					ans.put(i, k, m);
				}
				finally
				{
					
				}
				
			}
			
		}
		return ans;
	}
	/*
	public static void main( String[] args )
	{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		Document Test1= new Document();
		Mat Sheet1= ImageProcessing.focusOnSheet(Imgcodecs.imread("src/resources/idFinal.jpg"));
		Mat Sheet2 =Imgcodecs.imread("src/resources/canny-minichallenge.jpg");
		//ImageProcessing.drawGrid(Sheet1,20);
		
		//ImageIO.DisplayVerticalMat(Sheet1);
		Question[] quest=new Question[5];
		PixelPoint[] points=new PixelPoint[5];
		points[0]=new PixelPoint(0.1,0.35);
		points[1]=new PixelPoint(0.1,0.45);
		points[2]=new PixelPoint(0.1,0.55);
		points[3]=new PixelPoint(0.1,0.65);
		points[4]=new PixelPoint(0.1,0.75);
		for(int i=0;i<points.length;++i)
		{
			quest[i]=new StdMultichoiceQuestionWindow(points[i],Test1,null,1);
			Test1.addWindow(quest[i]);
		}
		
		points[0]=new PixelPoint(0.025,0.03);
		points[1]=new PixelPoint(0.095,0.03);
		points[2]=new PixelPoint(0.16,0.03);
		points[3]=new PixelPoint(0.217,0.03);
		points[4]=new PixelPoint(0.28,0.03);
		
		QuestionChoice[] choose=new QuestionChoice[5];
		
		for(int i=0;i<choose.length;++i)
		{
			choose[i]=new QuestionChoice(points[i],Test1,quest[0],new PixelPoint(0,0), false);
			Test1.addWindow(choose[i]);
			choose[i].visualizeReadingLocation(Sheet1);
		}
		//-----------------------------------------------------------------------------------------------------
		points[0]=new PixelPoint(0.03,0.025);
		points[1]=new PixelPoint(0.098,0.025);
		points[2]=new PixelPoint(0.16,0.025);
		points[3]=new PixelPoint(0.22,0.025);
		points[4]=new PixelPoint(0.281,0.025);
		
		for(int i=0;i<choose.length;++i)
		{
			choose[i]=new QuestionChoice(points[i],Test1,quest[1],new PixelPoint(0,0), false);
			Test1.addWindow(choose[i]);
			choose[i].visualizeReadingLocation(Sheet1);
		}
		
		//-----------------------------------------------------------------------------------------------------
		points[0]=new PixelPoint(0.03,0.0221);
		points[1]=new PixelPoint(0.098,0.0221);
		points[2]=new PixelPoint(0.165,0.0221);
		points[3]=new PixelPoint(0.22,0.0221);
		points[4]=new PixelPoint(0.285,0.0221);
		
		for(int i=0;i<choose.length;++i)
		{
			choose[i]=new QuestionChoice(points[i],Test1,quest[2],new PixelPoint(0,0), false);
			Test1.addWindow(choose[i]);
			choose[i].visualizeReadingLocation(Sheet1);
		}
		//-----------------------------------------------------------------------------------------------------
		points[0]=new PixelPoint(0.03,0.0221);
		points[1]=new PixelPoint(0.098,0.0221);
		points[2]=new PixelPoint(0.165,0.0221);
		points[3]=new PixelPoint(0.22,0.0221);
		points[4]=new PixelPoint(0.285,0.0221);
		
		for(int i=0;i<choose.length;++i)
		{
			choose[i]=new QuestionChoice(points[i],Test1,quest[3],new PixelPoint(0,0), false);
			Test1.addWindow(choose[i]);
			choose[i].visualizeReadingLocation(Sheet1);
		}
		//-----------------------------------------------------------------------------------------------------
		points[0]=new PixelPoint(0.03,0.03);
		points[1]=new PixelPoint(0.098,0.03);
		points[2]=new PixelPoint(0.165,0.03);
		points[3]=new PixelPoint(0.22,0.03);
		points[4]=new PixelPoint(0.285,0.03);
		
		for(int i=0;i<choose.length;++i)
		{
			choose[i]=new QuestionChoice(points[i],Test1,quest[4],new PixelPoint(0,0), false);
			Test1.addWindow(choose[i]);
			choose[i].visualizeReadingLocation(Sheet1);
		}
		EtalonColorCheckBox eta1=new EtalonColorCheckBox(new PixelPoint(0.13,0.09),Test1,null,new PixelPoint(0,0),true);
		Test1.addWindow(eta1);
		eta1.visualizeReadingLocation(Sheet1);

		EtalonColorCheckBox eta2=new EtalonColorCheckBox(new PixelPoint(0.13,0.175),Test1,null,new PixelPoint(0,0),false);
		Test1.addWindow(eta2);
		eta2.visualizeReadingLocation(Sheet1);
		//---------------------------------------------
		//----------------------------------------------
		StdIdentifierWindow idwin=new StdIdentifierWindow(new PixelPoint(0,0), Test1, null);
		Test1.addWindow(idwin);
		PixelPoint[] idpoint=new PixelPoint[30];
		StdIdentifierChoice[] idchoice=new StdIdentifierChoice[30];
		
		idpoint[0]=new PixelPoint(0.64,0.2);
		idpoint[1]=new PixelPoint(0.64,0.285);
		idpoint[2]=new PixelPoint(0.64,0.37);
		idpoint[3]=new PixelPoint(0.64,0.455);
		idpoint[4]=new PixelPoint(0.64,0.532);
		idpoint[5]=new PixelPoint(0.642,0.615);
		idpoint[6]=new PixelPoint(0.643,0.7);
		idpoint[7]=new PixelPoint(0.645,0.78);
		idpoint[8]=new PixelPoint(0.647,0.868);
		idpoint[9]=new PixelPoint(0.645,0.95);
		idpoint[10]=new PixelPoint(0.78,0.2);
		idpoint[11]=new PixelPoint(0.78,0.285);
		idpoint[12]=new PixelPoint(0.78,0.37);
		idpoint[13]=new PixelPoint(0.78,0.453);
		idpoint[14]=new PixelPoint(0.78,0.535);
		idpoint[15]=new PixelPoint(0.78,0.617);
		idpoint[16]=new PixelPoint(0.783,0.697);
		idpoint[17]=new PixelPoint(0.784,0.783);
		idpoint[18]=new PixelPoint(0.784,0.863);
		idpoint[19]=new PixelPoint(0.785,0.946);
		idpoint[20]=new PixelPoint(0.917,0.2);
		idpoint[21]=new PixelPoint(0.917,0.285);
		idpoint[22]=new PixelPoint(0.917,0.366);
		idpoint[23]=new PixelPoint(0.916,0.45);
		idpoint[24]=new PixelPoint(0.916,0.535);
		idpoint[25]=new PixelPoint(0.916,0.615);
		idpoint[26]=new PixelPoint(0.917,0.695);
		idpoint[27]=new PixelPoint(0.918,0.782);
		idpoint[28]=new PixelPoint(0.918,0.863);
		idpoint[29]=new PixelPoint(0.919,0.946);
		
		int tempint;
		for(int i=0;i<idchoice.length;++i)
		{
			
			tempint= (i%10)+1;
			if(tempint==10) tempint=0;
			char x= Integer.toString(tempint).charAt(0);
			idchoice[i]=new StdIdentifierChoice(idpoint[i], Test1, idwin,new PixelPoint(0,0),x,i/10);
			Test1.addWindow(idchoice[i]);
			idchoice[i].visualizeReadingLocation(Sheet1);
		}
		
		
		double ceasar = Test1.getColorDivider(Sheet1);
		
		Test1.updateAllQuestions(Sheet1, ceasar);
		Mark XXX= Test1.getTotalMark();
		System.out.println(XXX.value);
		Test1.updateIdentifier(Sheet1, ceasar);
		System.out.println(Test1.receiveId());
		ImageIO.DisplayVerticalMat(Sheet1);
		
		//quest[0]=new StdMultichoiceQuestion()
		
		//ImageIO.DisplayMat(ImageProcessing.focusOnSheet());
		/*
		System.out.println("Comer          Azathoth");
		Document myBestDoc=new Document();
		Mat bestImg=ImageProcessing.focusOnSheet();
		StdMultichoiceQuestionWindow myQuest=new StdMultichoiceQuestionWindow(new PixelPoint(0,0),myBestDoc, null,10);
		System.out.println("AZATHOTH");
		//Document myBestDoc=new Document();
		Mat bestPic=ImageProcessing.focusOnSheet();
		//StdMultichoiceQuestionWindow myQuest=new StdMultichoiceQuestionWindow(new PixelPoint(1,1),myBestDoc, null,666);
		myBestDoc.addWindow(myQuest);
		StdMultichoiceQuestionWindow myQuest2=new StdMultichoiceQuestionWindow(new PixelPoint(0,0),myBestDoc, null,20);
		myBestDoc.addWindow(myQuest2);

		CheckBox Opt1=new QuestionChoice(new PixelPoint(0.5,0.5), myBestDoc,myQuest,new PixelPoint(0,0),false);
		
		CheckBox Opt2=new QuestionChoice(new PixelPoint(0.5,0.5), myBestDoc,myQuest,new PixelPoint(0,0),false);
		CheckBox Opt3=new QuestionChoice(new PixelPoint(0.5,0.5), myBestDoc,myQuest,new PixelPoint(0,0),false);
		
		CheckBox qOpt1=new QuestionChoice(new PixelPoint(0.5,0.5), myBestDoc,myQuest2,new PixelPoint(0,0),false);
		
		CheckBox qOpt2=new QuestionChoice(new PixelPoint(0.5,0.5), myBestDoc,myQuest2,new PixelPoint(0,0),true);
		CheckBox qOpt3=new QuestionChoice(new PixelPoint(0.5,0.5), myBestDoc,myQuest2,new PixelPoint(0,0),false);
		
		myBestDoc.addWindow(Opt1);
		myBestDoc.addWindow(Opt2);
		myBestDoc.addWindow(Opt3);
		
		myBestDoc.addWindow(qOpt1);
		myBestDoc.addWindow(qOpt2);
		myBestDoc.addWindow(qOpt3);
		double divider=myBestDoc.getColorDivider(bestImg);
		myBestDoc.updateAllQuestions(bestImg, divider);
		
		StdIdentifierWindow idWindow=new StdIdentifierWindow(new PixelPoint(0,0),myBestDoc, null);
		myBestDoc.addWindow(idWindow);
		StdIdentifierChoice Opt4=new StdIdentifierChoice(new PixelPoint(0.5,0.5), myBestDoc,idWindow,new PixelPoint(0,0),'x',0);
		StdIdentifierChoice Opt5=new StdIdentifierChoice(new PixelPoint(0.5,0.5), myBestDoc,idWindow,new PixelPoint(0,0),'y',0);
		StdIdentifierChoice Opt10=new StdIdentifierChoice(new PixelPoint(0.5,0.5), myBestDoc,idWindow,new PixelPoint(0,0),'1',1);
		StdIdentifierChoice Opt6=new StdIdentifierChoice(new PixelPoint(0.5,0.5), myBestDoc,idWindow,new PixelPoint(0,0),'z',0);
		Opt4.visualizeReadingLocation(bestImg);
		Opt5.visualizeReadingLocation(bestPic);
		
		myBestDoc.addWindow(Opt10);
		myBestDoc.addWindow(Opt4);
		myBestDoc.addWindow(Opt5);
		myBestDoc.addWindow(Opt6);
		myBestDoc.updateIdentifier(bestImg, divider);
		myBestDoc.receiveId();
		System.out.println(myBestDoc.receiveId());
		ImageProcessing.drawGrid(bestImg,20);
		ImageIO.DisplayMat(bestImg);
		Mark bestMark=myBestDoc.getTotalMark();
		System.out.println(bestMark.value+" out of "+ bestMark.maxValue);
		
		/*DBProc.createDatabase();
		
		DBProc.insertStudent("i.21", "Kozorez", "Viktor");
		
		DBProc.insertTestWork("programming", 50);
		
		DBProc.insertStudentTestWork("i.21", 1, 45);
		
		ArrayList<Student> students = DBProc.selectQuery();
		
		System.out.println(students.size());
	} */
}