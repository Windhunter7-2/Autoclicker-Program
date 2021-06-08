package FileHandling;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;

import External.MainDirectory;

public class CalibrationFiles {
	private MainDirectory md = new MainDirectory();
	private FileHandling fh = new FileHandling();
	public void calibrate(String fileName) throws IOException
	{
		if(new File(md.getMainDirectory()+"Autoclicker-Program/Settings/Calibration/"+fileName+".autoclick").exists()) {return;}
		else createAutoclicker(fileName);
		
		/*
		 * Must be O(1)
		 * Checks if "Settings/Calibration/fileName.autoclick" exists; if it does exist, simply return; otherwise,
		 * call createAutoclicker()
		 */
	}
	
	private void createAutoclicker(String fileName) throws IOException
	{
		String[] oldContents = fh.getText("Autoclicker-Program/Autoclickers/"+fileName+".txt").split("\n");
		for (int i = 0; i < oldContents.length; ++i) {
			Scanner sc = new Scanner(oldContents[i]);
			if(sc.hasNext()) {
				String line = sc.next();
				if (line.equals("Mouse")){
					JFrame jf = new JFrame();
					GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(jf);
					jf.setAlwaysOnTop(true);
					MouseCalibrator mc = new MouseCalibrator();
					jf.addMouseListener(mc);
					JLabel text = new JLabel("Click Anywhere Within Window To Calibrate", JLabel.CENTER);
					jf.add(text);
					while(!mc.clicked) {} //Wait for user input
					oldContents[i] = oldContents[i].replaceAll("x:[^\s]*",mc.pos);
					jf.dispose();
				}
				else if (line.equals("CompareImages")) {				
					JFrame jf = new JFrame();
					GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(jf);
					jf.setAlwaysOnTop(true);
					CompareCalibrator cc = new CompareCalibrator();
					jf.addMouseListener(cc);
					JLabel text = new JLabel("Click And Drag Within Window To Define Comparison Region", JLabel.CENTER);
					jf.add(text);
					while(!cc.clicked) {} //Wait for user input
					oldContents[i] = oldContents[i].replaceAll("xs:[^\s]*", cc.startPos + " " + cc.endPos);
					jf.dispose();
				}
			}
			sc.close();
		}
		String newContents = "";
		for (String s : oldContents) {
			newContents = newContents + s;
		}
		fh.setText("Autoclicker-Program/Settings/Calibration/"+fileName+".autoclick", newContents);
		//throw new RuntimeException("TODO createAutoclicker()");
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
	 * listener for mouse clicks
	 * @author User
	 *
	 */
	private class MouseCalibrator extends MouseAdapter{
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
	}

	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) throws IOException {
		CalibrationFiles cf = new CalibrationFiles();
		cf.calibrate("LoremIpsum");
		System.out.println("File found case handled");
		cf.calibrate("WhyYouNotTalking");
	}

}
