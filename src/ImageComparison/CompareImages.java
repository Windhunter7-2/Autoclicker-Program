package ImageComparison;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import External.MainDirectory;
import FileHandling.ImageCompareFiles;

public class CompareImages {

	
	/**
	 * A *constant* number to represent the best time to wait before asking the user if they still want to wait (To avoid
	 * memory overuse); this number equals 5 minutes.
	 */
	private static long timeouter = 300000;
	
	/**
	 * The current time taken so far for the screenshot comparison.
	 */
	private static volatile long curTime = 0;
	
	/**
	 * Whether or not the program should issue a warning regarding a timeout from the program taking too long.
	 */
	private static volatile boolean timeout = false;
	
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
	 * It stops the algorithmic comparison if the timeout occurs.
	 * @param instructions The instructions given (In order) for the auto-click (Comparing images ones)
	 * @throws IOException 
	 * @throws AWTException 
	 */
	public void startAutoclick_cmpImg(ArrayList<String> instructions) throws IOException, AWTException
	{
		//Get the Instructions
		convertInstructions_cmpImg(instructions);
		
		//Get Dimensions / Pixel Locations
		int x = pixelLocations[0];
		int y = pixelLocations[1];
		ImageCompareFiles imageLoader = new ImageCompareFiles();
		BufferedImage imageToCompareTo = imageLoader.loadImage(imageLocation);	//The Screenshot to Compare to
		int height = imageToCompareTo.getHeight();
		int width = imageToCompareTo.getWidth();
		Rectangle screenRectangle = new Rectangle(x, y, width, height);
		Robot robot;
		
		//While Timer Active, Compare Images
		boolean imageCmpDone = false;
		long originalTime = System.currentTimeMillis();
		
		while (timeout == false)
		{
			//Check Current Time Here; Until the Timer Is Reached, Update the Timer
			curTime = (System.currentTimeMillis() - originalTime);
			if (curTime > timeouter)
				timeout = true;
			
			//Create Current Screenshot
			robot = new Robot();
			BufferedImage screenshot = robot.createScreenCapture(screenRectangle);	//Create Screenshot of That Part of the Screen
			boolean toCheck = compareScreens(imageToCompareTo, screenshot, width, height);	//Compare the Screenshots
			
			//Mark As Successfully Compared, and Exit Loop
			if (untilSame == true)
			{
				if (toCheck == true)
					imageCmpDone = true;
			}
			else if (untilDifferent == true)
			{
				if (toCheck == false)
					imageCmpDone = true;
			}
			if (imageCmpDone == true)
				break;
		}
		
		//If Not Yet Successfully Compared, a Timeout Occurred Here; Prompt the User to Restart Or to Stop
		if (imageCmpDone == false)
		{
			boolean repeat = cmpImage_cont();	//Decide Whether to Retry the Image Comparison Or Not
			try {
				Thread.sleep(3000);	//Close Window *Before* Restarting Screenshot Comparison; 3 Seconds for It
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (repeat == true)	//If Retrying Image Comparison, Just Call the Method Recursively (Again)
			{
				curTime = 0;
				timeout = false;
				startAutoclick_cmpImg(instructions);
			}
		}
		
		//If Successfully Compared, Return (Also Return if User Decides to Not Continue Image Check)
		return;
	}
	
	/**
	 * There has not yet been a successful comparison of images, and a timeout has occurred. This prompts the user to select whether
	 * to restart and retry the image comparison, or to exit the current instruction and continue the autoclicker as normal.
	 * @return true if the desire to retry the image comparison, and false otherwise
	 */
	private boolean cmpImage_cont()
	{
		//Convert timeouter to Minutes
		double minutes = (timeouter / 60000);
		String minutes_Str = String.format("%.2f", minutes);
		if ((minutes_Str.charAt(2) == '0') || (minutes_Str.charAt(3) == '0'))
			minutes_Str = String.format("%.0f", minutes);
		
		//The GUI JOptionPane
		Object [] buttons = {"Continue Waiting...", "Stop Waiting", "Exit Program"};
		String warningText = ("It has been " + minutes_Str + " minutes without the screenshot being compared changing.\nWould you "
				+ "like to:\n      (1) Wait further,\n      (2) Stop waiting and continue the autoclicker as normal, or\n      (3) "
				+ "Consider this an error and exit the program?");
		int option = JOptionPane.showOptionDialog(null, warningText, "Screenshot Comparison TimeOut",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, buttons, buttons[0]);
		
		//Make Selection
		if (option == 0)
			return true;
		else if (option == 1)
			return false;
		else if (option == 2)
			System.exit(-1);
		return true;
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
	 * @throws IOException 
	 * @throws AWTException 
	 */
	public static void main(String[] args) throws IOException, AWTException {
		
		//Example Test A (Waiting)
		ArrayList<String> instr_wait = new ArrayList<String>();
		instr_wait.add("500");	//Wait Time of 1/2 Second
		
		CompareImages forTesting = new CompareImages();
		System.out.println("Starting waiting...");
		forTesting.startAutoclick_wait(instr_wait);
		System.out.println("Finished waiting...");
		
		//Test Screenshot Comparison - Take Screenshot to Compare
		ImageCompareFiles test = new ImageCompareFiles();
		Robot robot = new Robot();
		Rectangle screenRectangle = new Rectangle(550, 362, 938, 387);
		BufferedImage screenshot = robot.createScreenCapture(screenRectangle);	//Create Screenshot of That Part of the Screen
		MainDirectory directory = new MainDirectory();
		test.saveImage(screenshot, (directory.getMainDirectory() + "/Autoclicker-Program/TestScreenshot.png") );
		
		//Test Screenshot Comparison - Instructions
		ArrayList<String> instr = new ArrayList<String>();
		String screenshotExample = (directory.getMainDirectory() + "/Autoclicker-Program/TestScreenshot.png");
		instr.add(screenshotExample);
		instr.add("550");
		instr.add("362");
		instr.add("-1");
		instr.add("-1");
		instr.add("false");
		instr.add("true");	//Wait Until <Different>
		forTesting.startAutoclick_cmpImg(instr);
		System.out.println("Screenshots successfully compared! Waiting for the screenshots to be *different* is complete!");


	}

}
