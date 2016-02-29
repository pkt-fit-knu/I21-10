package project;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Mat;

public final class ImageIO 
{
	private ImageIO()
	{ }
	
	private static BufferedImage bufferedImage(Mat m) 
	{
	    int type = BufferedImage.TYPE_BYTE_GRAY;
	    
	    if (m.channels() > 1) 
	    {
	        type = BufferedImage.TYPE_3BYTE_BGR;
	    }
	    
	    BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
	    
	    m.get(0, 0, ((DataBufferByte)image.getRaster().getDataBuffer()).getData());
	    
	    return image;
	}
	
    private static void displayImage(Image img)
	{   
	    ImageIcon icon = new ImageIcon(img);
	    
	    JFrame frame = new JFrame();
	    
	    frame.setLayout(new FlowLayout());
	    
	    frame.setSize(img.getWidth(null) + 50, img.getHeight(null) + 50);
	    
	    JLabel label = new JLabel();
	    
	    label.setIcon(icon);
	    
	    frame.add(label);
	    
	    frame.setVisible(true);
	    
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} 
    
    public static void DisplayMat(Mat img)
   	{   
    	displayImage(bufferedImage(img));
   	}
    public static void DisplayVerticalMat(Mat img)
   	{   
    	displayImage(bufferedImage(img.t()));
   	}
}
