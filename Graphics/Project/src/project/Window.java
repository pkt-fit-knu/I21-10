package project;

public abstract class Window 
{
	protected Window ancestor;
	protected Document parentDocument;
	protected PixelPoint corner;
	public Window(PixelPoint corn, Document doc, Window ancestor)
	{
		this.ancestor=ancestor;
		corner=corn;
		parentDocument=doc;
	}
	public PixelPoint getCorner()
	{
		return corner;
	}
	public PixelPoint getAbsoluteCorner()
	{
		if(ancestor == null) return corner;
		else return PixelPoint.add(ancestor.getAbsoluteCorner(), corner);
	}
}
