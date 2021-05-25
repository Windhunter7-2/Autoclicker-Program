package ImageComparison;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CompareImages {

	
	/**
	 * A *constant* number to represent the best time to wait before asking the user if they still want to wait (To avoid
	 * memory overuse); this number equals 5 minutes.
	 */
	private long timeouter = 300000;
	
	/**
	 * The location of the image to compare against the user's screenshot.
	 */
	private String imageLocation = "";
	
	/**
	 * The 4 pixel locations for the current rectangle screenshot.
	 */
	private int [] pixelLocations = new int[4];
	
	/**
	 * Whether or not to wait until the images are the same, i.e. while they are different, to wait.
	 */
	private boolean untilSame = false;
	
	/**
	 * Whether or not to wait until the images are different, i.e. while they are the same, to wait.
	 */
	private boolean untilDifferent = false;
	
	/**
	 * The wait time (In milliseconds) to wait for a waiting autoclicker.
	 */
	private int waitTime = 0;
	
	/**
	 * This starts the auto-click of the given instructions, for comparing image instructions.
	 * @param instructions The instructions given (In order) for the auto-click (Comparing images ones)
	 */
	public void startAutoclick_cmpImg(ArrayList<String> instructions)
	{
		//TODO -> Finishing Planning...
		//TODO -> Make <Sure> to Use compareScreens() <&> Timer to Prevent Overusing Memory!!!
		//TODO -> Make <Sure> That It Starts Out with <Checking> Boundary Sizes to Match Width/Height Beforehand!!!
		convertInstructions_cmpImg(instructions);
	}
	
	/**
	 * This starts the auto-click of the given instructions, for waiting instructions.
	 * @param instructions The instruction given (Wait time) for the auto-click (Waiting instructions)
	 */
	public void startAutoclick_wait(ArrayList<String> instructions)
	{
		convertInstructions_wait(instructions);
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Converts the instructions from String form to the proper forms (As globals, i.e. ints, etc.), for the
	 * comparing images instructions.
	 * @param instructions The instructions to be converted
	 */
	private void convertInstructions_cmpImg(ArrayList<String> instructions)
	{
		//Instructions Format = {imageLocation, pixel_x_i, pixel_y_i, pixel_x_f, pixel_y_f, untilSame, untilDifferent}
		String S1 = instructions.get(0);
		String S2 = instructions.get(1);
		String S3 = instructions.get(2);
		String S4 = instructions.get(3);
		String S5 = instructions.get(4);
		String S6 = instructions.get(5);
		String S7 = instructions.get(6);
		imageLocation = S1;
		pixelLocations[0] = Integer.parseInt(S2);
		pixelLocations[1] = Integer.parseInt(S3);
		pixelLocations[2] = Integer.parseInt(S4);
		pixelLocations[3] = Integer.parseInt(S5);
		untilSame = Boolean.parseBoolean(S6);
		untilDifferent = Boolean.parseBoolean(S7);
	}
	
	/**
	 * Converts the instructions from String form to the proper forms (As globals), for the waiting instructions.
	 * @param instructions The instructions to be converted
	 */
	private void convertInstructions_wait(ArrayList<String> instructions)
	{
		//Instructions Format = {waitTime}
		String S1 = instructions.get(0);
		waitTime = Integer.parseInt(S1);
	}
	
	/**
	 * Compare two images; if they are the same, return true; otherwise, return false.
	 * @param imageA The first image to compare
	 * @param imageB The second image to compare
	 * @param width The width of both images
	 * @param height The height of both images
	 * @return
	 */
	private boolean compareScreens(BufferedImage imageA, BufferedImage imageB, int width, int height)
	{
		//Traverse a Row of Pixels
		for (int x = 0; x < width; x++)
		{
			//Traverse a Column of Pixels
			for (int y = 0; y < height; y++)
			{
				if ( imageA.getRGB(x, y) != imageB.getRGB(x, y) )
					return false;	//There Is at Least One Different Pixel
			}
		}
		return true;	//All the Pixels Are the Same
	}

	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		
		//TODO THE STUFF HERE IS REMINDER IMPORTANT CODE FOR SCREENSHOT BUILDING
		CompareImages zzz = new CompareImages();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle screenRectangle = new Rectangle(screenSize);
		Robot robot;
		try {
			robot = new Robot();
			BufferedImage image = robot.createScreenCapture(screenRectangle);	//Create Screenshot of *Whole* Screen
			
			//Compare Two Images
			BufferedImage imageToCompareAgainst = robot.createScreenCapture(screenRectangle);
			
			int width = image.getWidth();
			int height = image.getHeight();
			
			System.out.println( zzz.compareScreens(image, imageToCompareAgainst, width, height) );
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
		//TODO THE STUFF HERE IS REMINDER IMPORTANT CODE FOR SCREENSHOT BUILDING

		
		//Example Test A (Waiting)
		ArrayList<String> instr_wait = new ArrayList<String>();
		instr_wait.add("5000");	//Wait Time of 5 Seconds
		
		CompareImages forTesting = new CompareImages();
		System.out.println("Starting waiting...");
		forTesting.startAutoclick_wait(instr_wait);
		System.out.println("Finished waiting...");


	}

}
