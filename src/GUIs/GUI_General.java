package GUIs;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;

import ClickingTranslation.Mapping;
import External.MainDirectory;
import FileHandling.CalibrationFiles;
import FileHandling.FileHandling;
import FileHandling.MappingFiles;

public class GUI_General {
	private MainDirectory md = new MainDirectory();
	private FileHandling fh = new FileHandling();
	public void createAutoclick(String fileNameWithExt, ArrayList<String> instructions) throws IOException
	{
		String outContents = "";
		for(String s : instructions) {
			outContents = outContents + s + "\n";
		}
		fh.setText("Autoclicker-Program/Autoclickers/" + fileNameWithExt, outContents.substring(0, outContents.length()-1));
		
		/*
		 * Must be O(n), where n is the number of Strings in the ArrayList
		 * Call FileHandling.setText(), where the parameter of contents is the ArrayList instructions, converted to newlines
		 * for every element (For example, if the list were hypothetically ["Bob 10", "Alice 5", "Tim 17"], then
		 * "Bob 10\nAlice 5\nTim 17" would be set as the contents for setText()
		 */
	}
	
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
		/*
		 * whichOne is which autoclicker type (i.e. "Keyboard", "AdvKeyboard", "Mouse", "Wait", or "CompareImages)
		 * The various vars are only ready from left to right, for the different items, and the unused don't matter; for example,
		 * createAutoclickInstr("Wait", "", false, false, 60000, -1, -1, -1, -1, -1) would return "Wait 60000"
		 * 
		 * First calls MappingFiles.setX, where X is a type of autoclicker, with the parameters that match ( For the previous
		 * example with Wait, this example would be calling MappingFiles.setWait(var1) )
		 * Then, after getting that returned Instruction, parse the Instruction.instrucVals into a single String, parsing each
		 * element with a space (For a hypothetical example, if instrucVals were ["Bob", "Alice", "Tim"], the resultant String
		 * would be "Bob Alice Tim"; this calculated String is what should be returned from the function
		 */
	}
	
	public void runAutoclick(String fileName) throws InterruptedException, IOException, AWTException
	{
		CalibrationFiles cf = new CalibrationFiles();
		Mapping m = new Mapping();
		cf.calibrate(fileName);
		m.startMap(fh.getText("Autoclicker-Program/Settings/Calibration/"+fileName+".autoclick"));
		
		/*
		 * Must be O(1)
		 * Call CalibrationFiles.calibrate(), *then* call FileHandling.getText(), using the *full* file name/location as its argument,
		 * and passing the results to Mapping.startMap()
		 * The "full" file name/location for getText() should be equal to "Settings/Calibration/fileName.autoclick"
		 */
	}

	/**
	 * Testing only.
	 * @throws AWTException 
	 * @throws IOException
	 * @throws InterruptedException 
	 * @params args unused.
	 */
	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		GUI_General gg = new GUI_General();
		ArrayList<String> as = new ArrayList<>();
		as.add(gg.createAutoclickInstr("Keyboard", "Blehh", null, false, false, 1, 2, 1000, 0, 0, 0));
		as.add(gg.createAutoclickInstr("AdvKeyboard", "Ctrl", null, false, false, 1, 2, 0, 0, 0, 0));
		as.add(gg.createAutoclickInstr("Mouse", "Mouse1", "Left", true, false, 3, 0, 30, 100, 0, 0));
		as.add(gg.createAutoclickInstr("Wait", null, null, false, false, 3, 0, 0, 0, 0, 0));
		as.add(gg.createAutoclickInstr("CompareImages", "Region1", "Box1", true, false, 0, 0, 0, 0, 0, 0));
		gg.createAutoclick("GUIGeneralTest.txt", as);
		gg.runAutoclick("GUIGeneralTest");
		
	}
}
