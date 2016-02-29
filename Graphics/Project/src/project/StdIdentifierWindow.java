package project;

import java.util.ArrayList;

public class StdIdentifierWindow extends Identifier{
	
	protected ArrayList<Character> fullId;
	{
		fullId=new ArrayList<Character>(11);
		for(int i=0;i<11;++i)
		{
			fullId.add('#');
		}
	}
	protected int length=0;
	public StdIdentifierWindow(PixelPoint corn, Document doc, Window ancestor) {
		super(corn, doc, ancestor);
		// TODO Auto-generated constructor stub
	}
	public String getId()
	{
		Character temp;
		StringBuilder str=new StringBuilder();
		for(int i=0;i<length;++i)
		{
			temp=fullId.get(i);
			if((char)temp!='#')
			{
				str.append(temp);
			}
		}
		String u=str.toString();
		return u;
	}
	public void recieveBoxData(char data, int position)
	{
		if(position>=length) length=position+1;
		fullId.set(position,data);
		
	}

}
