package project;

public class Mark 
{
	public int value=0;
	public int maxValue=0;
	public Mark(int a, int b)
	{
		value=a;
		maxValue=b;
	}
	public Mark()
	{
		
	}
	public static Mark add(Mark a, Mark b)
	{
		return new Mark(a.value+b.value, a.maxValue+b.maxValue);
	}
}
