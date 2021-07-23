package GUIs;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import ClickingTranslation.Mapping;
import External.MainDirectory;
import FileHandling.CalibrationFiles;
import FileHandling.FileHandling;
import FileHandling.MappingFiles;

public class GUI_General {
	
	/**
	 * For a quick reference to file handling methods.
	 */
	private FileHandling fh = new FileHandling();
	
	/**
	 * Takes an ArrayList of autoclicker instructions, converts it to a String of these instructions with newlines separating
	 * them, and then outputs it to the proper file.
	 * @param fileNameWithExt The full filename for the autoclicker
	 * @param instructions The instructions (In ArrayList form) to output
	 * @throws IOException
	 */
	public void createAutoclick(String fileNameWithExt, ArrayList<String> instructions) throws IOException
	{
		String outContents = "";
		for(String s : instructions) {
			outContents = outContents + s + "\n";
		}
		fh.setText("Autoclicker-Program/Autoclickers/" + fileNameWithExt, outContents.substring(0, outContents.length()-1));
		String fileName = new MainDirectory().getMainDirectory() 
			+ "Autoclicker-Program/Settings/Calibration/" 
			+ fileNameWithExt.substring(0, fileNameWithExt.length()-4) 
			+ ".autoclick"; //Undoing the addition of the extension is bad form. I must fix this later.
		new File(fileName).delete();
		//new File("Autoclicker-Program/Settings/Calibration/" + fileNameWithExt.substring(0, fileNameWithExt.length()-4) + ".autoclick").delete();
	}
	
	/**
	 * A generic method that uses MappingFiles as a middleman class to convert generically given inputs to the proper instruction
	 * type, depending on what whichOne's value is. For example, if whichOne is "Wait", the instruction generated will be in the
	 * proper "Wait" instruction format, with the proper variables in the parameters as the input for the instruction.
	 * @param whichOne Which instruction to convert the format to
	 * @param varS1 Generic String variable #1
	 * @param varS2 Generic String variable #2
	 * @param varB1 Generic boolean variable #1
	 * @param varB2 Generic boolean variable #2
	 * @param var1 Generic integer variable #1
	 * @param var2 Generic integer variable #2
	 * @param var3 Generic integer variable #3
	 * @param var4 Generic integer variable #4
	 * @param var5 Generic integer variable #5
	 * @param var6 Generic integer variable #6
	 * @return A String representation of the converted format
	 */
	public String createAutoclickInstr(String whichOne, String varS1, String varS2, boolean varB1, boolean varB2, int var1, int var2,
			int var3, int var4, int var5, int var6)
	{
		MappingFiles mf = new MappingFiles();
		MappingFiles.Instruction mfi;
		String outInstruction;
		switch(whichOne) {
		case "Keyboard":
			mfi = mf.setKeyboard(var1, var2, var3, varS1);
			break;
		case "AdvKeyboard":
			mfi = mf.setAdvKeyboard(var1, var2, varS1, varB1);
			break;
		case "Mouse":
			mfi = mf.setMouse(varB1, varS1, varS2, var1, varB2, var2, var3, var4);
			break;
		case "Wait":
			mfi = mf.setWait(var1);
			break;
		case "CompareImages":
			mfi = mf.setCompareImages(varS1, varS2, varB1, varB2);
			break;
		default:
			mfi = mf.setWait(0);
			break;
		}
		outInstruction = whichOne + " " + mfi.toString();
		return outInstruction;
	}
	
	/**
	 * Runs the given autoclicker, calibrating if needed.
	 * @param fileName Which autoclicker to run
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws AWTException
	 */
	public void runAutoclick(String fileName) throws InterruptedException, IOException, AWTException
	{
		CalibrationFiles cf = new CalibrationFiles();
		Mapping m = new Mapping();
		cf.calibrate(fileName);
		m.startMap(fh.getText("Autoclicker-Program/Settings/Calibration/"+fileName+".autoclick"));
	}

	/**
	 * Testing only.
	 * @throws AWTException
	 * @throws IOException
	 * @throws InterruptedException 
	 * @param args unused.
	 */
	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		GUI_General gg = new GUI_General();
		ArrayList<String> as = new ArrayList<>();
		as.add(gg.createAutoclickInstr("Keyboard", "Blehh", null, false, false, 1, 2, 1000, 0, 0, 0));
		as.add(gg.createAutoclickInstr("AdvKeyboard", "Ctrl", null, false, false, 1, 2, 0, 0, 0, 0));
		as.add(gg.createAutoclickInstr("Mouse", "Mouse1", "LeftClick", true, false, 1, 0, 30, 100, 0, 0));
		as.add(gg.createAutoclickInstr("Wait", null, null, false, false, 3000, 0, 0, 0, 0, 0));
		as.add(gg.createAutoclickInstr("CompareImages", "Region1", "Box1", true, false, 0, 0, 0, 0, 0, 0));
		gg.createAutoclick("GUIGeneralTest.txt", as);
		gg.runAutoclick("GUIGeneralTest");
		
	}
}
