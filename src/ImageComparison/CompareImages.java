package ImageComparison;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class CompareImages {

	//TO DO -> Finishing Planning...

	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot;
		try {
			robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);	//Create Screenshot of *Whole* Screen
			
			//Compare Two Images
			BufferedImage imageToCompareAgainst = null;
			
			int width = image.getWidth();
			int height = image.getHeight();
			boolean breakBool = false;
			for (int x = 0; x < width; x++)
			{
				if (breakBool)
					break;
				for (int y = 0; y < height; y++)
				{
					if ( image.getRGB(x, y) != imageToCompareAgainst.getRGB(x, y) )
					{
						breakBool = true;
						break;
					}
				}
			}
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
