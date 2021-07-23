package FileHandling;

import java.awt.image.BufferedImage;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import External.MainDirectory;

public class CalibrationFiles {
	
	/**
	 * For a quick reference to the main directory getter method.
	 */
	private MainDirectory md = new MainDirectory();
	
	/**
	 * For a quick reference to file handling methods.
	 */
	private FileHandling fh = new FileHandling();
	
	/**
	 * How long to wait after prompt closes before continuing.
	 */
	private long inputWait = 5000L;
	
	/**
	 * How long to wait before closing prompts.
	 */
	private long infoWait = 5000L; 
	
	/**
	 * Check if calibration is needed or not. If calibration is needed, calibrate.
	 * Calibration generates the .autoclick files in the Settings/Calibration folder.
	 * @param fileName The filename of the autoclicker
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void calibrate(String fileName) throws IOException, InterruptedException
	{
		if(new File(md.getMainDirectory()+"Autoclicker-Program/Settings/Calibration/"+fileName+".autoclick").exists()) {
			
			
			
			//TODO -> For Version 1.1: Build in Recalibration Given Previous User Mouse Locations!
			
			
			
			//createAutoclicker(fileName);
			return;
			}
		else createAutoclicker(fileName);
	}
	
	/**
	 * Perform calibration of an autoclicker, generating a .autoclick file.
	 * @param fileName The filename of the autoclicker
	 * @throws IOException
	 * @throws InterruptedException
	 */
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
			endFrame.setTitle("All Done!");
			JLabel text = new JLabel("<html><p text-align:center>Calibration Complete.<br>Thank you for your time!</p></html>", JLabel.CENTER);
			endFrame.add(text);
			endFrame.setBounds(0,0,600,600);
			endFrame.setAlwaysOnTop(true);

			//endFrame.pack();
			endFrame.setVisible(true);
			Thread.sleep(infoWait);
			endFrame.dispose();
		}
	}
	
	/**
	 * Give user prompt regarding the calibration, so they know to point their mouse in the proper places.
	 * @param inText What's written in the prompt
	 * @return The location of where the user points
	 * @throws InterruptedException
	 */
	private Point calibSetup(String inText) throws InterruptedException {
		JFrame jf = new JFrame();
		jf.setTitle("Calibration in Progress...");
		//GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		//gc.getDefaultTransform().getScaleX();
		int dpi = Toolkit.getDefaultToolkit().getScreenResolution(); //the current dpi on the device.
		final double DEF_DPI = 96.0; //default dpi, at least on W10; may differ on other devices. TODO figure out how to calculate this w/ diff device info
		double scaleInv = DEF_DPI / dpi; //Rescales calibration based on default dpi.
		JLabel text = new JLabel(inText, JLabel.CENTER);
		jf.add(text);
		jf.setBounds(0,0,600,600);
		jf.setAlwaysOnTop(true);
		jf.setVisible(true);
		Thread.sleep(infoWait);
		jf.dispose();
		Thread.sleep(inputWait);
		Point p = MouseInfo.getPointerInfo().getLocation();
		p.move((int)(p.getX() * scaleInv), (int)(p.getY() * scaleInv));
		return p;
	}
	
	/**
	 * listener for mouse clicks
	 * @author Cole
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
