package GUIs;

import java.util.ArrayList;

public class GUI_General {
	
	public void createAutoclick(String fileNameWithExt, ArrayList<String> instructions)
	{
		/*
		 * Must be O(n), where n is the number of Strings in the ArrayList
		 * Call FileHandling.setText(), where the parameter of contents is the ArrayList instructions, converted to newlines
		 * for every element (For example, if the list were hypothetically ["Bob 10", "Alice 5", "Tim 17"], then
		 * "Bob 10\nAlice 5\nTim 17" would be set as the contents for setText()
		 */
	}
	
	public String createAutoclickInstr(String whichOne, String varS, boolean varB1, boolean varB2, int var1, int var2,
			int var3, int var4, int var5, int var6)
	{
		/*
		 * whichOne is which autoclicker type (i.e. "Keyboard", "AdvKeyboard", etc.)
		 * The various vars are only ready from left to right, for the different items, and the unused don't matter; for example,
		 * createAutoclickInstr("Wait", "", false, false, 60000, -1, -1, -1, -1, -1) would return "Wait 60000"
		 * 
		 * First calls MappingFiles.setX, where X is a type of autoclicker, with the parameters that match ( For the previous
		 * example with Wait, this example would be calling MappingFiles.setWait(var1) )
		 * Then, after getting that returned Instruction, parse the Instruction.instrucVals into a single String, parsing each
		 * element with a space (For a hypothetical example, if instrucVals were ["Bob", "Alice", "Tim"], the resultant String
		 * would be "Bob Alice Tim"; this calculated String is what should be returned from the function
		 */
		return "";
	}
	
	public void runAutoclick(String fileName)
	{
		/*
		 * Must be O(1)
		 * Call CalibrationFiles.calibrate(), *then* call FileHandling.getText(), using the *full* file name/location as its argument,
		 * and passing the results to Mapping.startMap()
		 * The "full" file name/location for getText() should be equal to "Settings/Calibration/fileName.autoclick"
		 */
	}

}
