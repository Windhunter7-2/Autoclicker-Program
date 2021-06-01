package FileHandling;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import External.MainDirectory;

public class CalibrationFiles {
	private MainDirectory md = new MainDirectory();
	public void calibrate(String fileName)
	{
		if(new File(md.getMainDirectory()+"Autoclicker-Program/Autoclickers/"+fileName+".autoclick").exists()) {return;}
		else createAutoclicker(fileName);
		
		/*
		 * Must be O(1)
		 * Checks if "Settings/Calibration/fileName.autoclick" exists; if it does exist, simply return; otherwise,
		 * call createAutoclicker()
		 */
	}
	
	private void createAutoclicker(String fileName)
	{
		throw new RuntimeException("TODO createAutoclicker()");
		/*
		 * Must be O(n)*, where n is the number of characters in the autoclicker template file
		 * (O() might be different for CompareImages, because of the pixels)
		 * Creates the file "Settings/Calibration/fileName.autoclick", and puts into it the edited contents of the file
		 * "Autoclickers/fileName.txt"
		 * These edits should look for any line that starts with "Mouse", or "CompareImages", and do some replacement to the String
		 * For lines with "Mouse", any "x:NameOfPosition" should prompt the user to point to the location "Name Of Position"
		 * (Yes, capitalization is what defines the spacing for printing to the user), in which case the edit to the string will
		 * replace that bit of "x:NameOfPosition" with "x y", where x and y are the x position and y position of the mouse,
		 * respectively
		 * For lines with "CompareImages", instead what will be replaced will be "xs:NameOfPosition", and will prompt the user to
		 * draw a box around the part of the screen they want to compare images against; after they draw this box, the 4 corners of
		 * the box, *in the order* of [TopLeft x Position, TopLeft y Position, BottomRight x Position, BottomRight y Position] will
		 * be the replacement for "xs:NameOfPosition", and it will also create a BufferedImage screen capture of this screen at those
		 * exact pixels, and call ImageCompareFiles.saveImage(), with fileName in the parameter being the same as createAutoclicker()'s
		 * fileName, to save the image as a .png
		 */
	}

	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		CalibrationFiles cf = new CalibrationFiles();
		cf.calibrate("MyNameJeff");
		System.out.println("File found case handled");
		cf.calibrate("WhyYouNotTalking");
		
		
		
	}

}
