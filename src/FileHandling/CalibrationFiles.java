package FileHandling;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;

import External.MainDirectory;

public class CalibrationFiles {
	private MainDirectory md = new MainDirectory();
	private FileHandling fh = new FileHandling();
	private long inputWait = 5000L;
	private long infoWait = 5000L; 
	public void calibrate(String fileName) throws IOException, InterruptedException
	{
		if(new File(md.getMainDirectory()+"Autoclicker-Program/Settings/Calibration/"+fileName+".autoclick").exists()) {return;}
		else createAutoclicker(fileName);
		
		/*
		 * Must be O(1)
		 * Checks if "Settings/Calibration/fileName.autoclick" exists; if it does exist, simply return; otherwise,
		 * call createAutoclicker()
		 */
	}
	
	private void createAutoclicker(String fileName) throws IOException, InterruptedException
	{
		boolean isChanged = false; //Test if we do any mouse-based calibrations
		String[] oldContents = fh.getText("Autoclicker-Program/Autoclickers/"+fileName+".txt").split("\n");
		for (int i = 0; i < oldContents.length; ++i) {
			Scanner sc = new Scanner(oldContents[i]);
			if(sc.hasNext()) {
				String line = sc.next();
				if (line.equals("Mouse")){
					isChanged = true;
					Pattern p = Pattern.compile("x:[^ \t\n\f\r]*");
					Matcher m = p.matcher(oldContents[i]);
					String x = "Unnamed";
					if(m.find()) {
						x = oldContents[i].substring(m.start()+2, m.end());
					}
					Point pt = calibSetup("<html><p text-align:center>Calibrating " + x + ".<br>When this window closes, hover over where you want to click and wait.</p></html>");
					oldContents[i] = oldContents[i].replaceAll("x:[^ \t\n\f\r]*", pt.x + " " + pt.y);
					
				}
				else if (line.equals("CompareImages")) {
					isChanged = true;
					//Finding command name
					Pattern p = Pattern.compile("xs:[^ \t\n\f\r]*");
					Matcher m = p.matcher(oldContents[i]);
					String xs = "Unnamed";
					if(m.find()) { 
						xs = oldContents[i].substring(m.start()+3, m.end());
					}
					Point sp = calibSetup("<html><p text-align:center>Calibrating "
					+ xs
					+ ".<br>When this window closes, hover over top-left corner and wait.</p></html>");
					Point ep = calibSetup("<html><p text-align:center>Calibrating "
					+ xs
					+ ".<br>When this window closes, hover over bottom-right corner and wait.</p></html>");
					if (sp.x > ep.x) {
						int tmp = sp.x;
						sp.x = ep.x;
						ep.x = tmp;
					}
					if (sp.y > ep.y) {
						int tmp = sp.y;
						sp.y = ep.y;
						ep.y = tmp;
					}
					oldContents[i] = oldContents[i].replaceAll("xs:[^ \t\n\f\r]*", sp.x + " " + sp.y + " " + ep.x + " " + ep.y);
					
                    //Take Screenshot to Compare (If CompareImages Calibration)
                    ImageCompareFiles cmp = new ImageCompareFiles();
                    Robot robot = null;
                    try {
                        robot = new Robot();
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                    Rectangle screenRectangle = new Rectangle( sp.x, sp.y, (ep.x-sp.x), (ep.y-sp.y) );
                    BufferedImage screenshot = robot.createScreenCapture(screenRectangle);    //Create Screenshot of That Part of the Screen
                    MainDirectory directory = new MainDirectory();
                    String name = sc.next();	//The Filename (e.g. ExampleScreenshot_0.png)
                    cmp.saveImage(screenshot, (directory.getMainDirectory() + "/Autoclicker-Program/Settings/Images/" + name) );
				}
			}
			sc.close();
		}
		String newContents = "";
		for (String s : oldContents) {
			newContents = newContents + s + "\n";
		}
		
		fh.setText("Autoclicker-Program/Settings/Calibration/"+fileName+".autoclick", newContents.substring(0, Math.max(newContents.length()-1,0)));
		if (isChanged) {
			JFrame endFrame = new JFrame();
			JLabel text = new JLabel("<html><p text-align:center>Calibration Complete.<br>Thank you for your time!</p></html>", JLabel.CENTER);
			endFrame.add(text);
			endFrame.setBounds(0,0,600,600);
			endFrame.setAlwaysOnTop(true);

			//endFrame.pack();
			endFrame.setVisible(true);
			Thread.sleep(infoWait);
			endFrame.dispose();
		}
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
	
	private Point calibSetup(String inText) throws InterruptedException {
		JFrame jf = new JFrame();
		JLabel text = new JLabel(inText, JLabel.CENTER);
		jf.add(text);
		jf.setBounds(0,0,600,600);
		jf.setAlwaysOnTop(true);
		jf.setVisible(true);
		Thread.sleep(infoWait);
		jf.dispose();
		Thread.sleep(inputWait);
		return MouseInfo.getPointerInfo().getLocation();
	}
	
	/**
	 * listener for mouse clicks
	 * @author User
	 *
	 */
	/*private class MouseCalibrator extends MouseAdapter{
		public String pos;
		public boolean clicked;
		public MouseCalibrator() {
			pos = "0 0";
			clicked = false;

		}
		@Override
		public void mouseClicked(MouseEvent e) {
			Point p = MouseInfo.getPointerInfo().getLocation();
			pos = p.x + " " + p.y;
			clicked = true;
		}		
	}
	
	private class CompareCalibrator extends MouseAdapter{
		public String startPos;
		public String endPos;
		public boolean clicked;
		public CompareCalibrator() {
			startPos = null;
			endPos = null;
			clicked = false;
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			Point p = MouseInfo.getPointerInfo().getLocation();
			startPos = p.x + " " + p.y;
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			Point p = MouseInfo.getPointerInfo().getLocation();
			endPos = p.x + " " + p.y;
			if (startPos != null && endPos != null) {
				clicked = true;
			}
		}
	}*/

	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		CalibrationFiles cf = new CalibrationFiles();
		cf.calibrate("LoremIpsum");
		System.out.println("File found case handled");
		cf.calibrate("WhyYouNotTalking");
	}

}
