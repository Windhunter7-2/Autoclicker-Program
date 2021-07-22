package FileHandling;

import java.util.ArrayList;

public class MappingFiles {
	
	/**
	 * Represents a single instruction.
	 */
	public class Instruction
	{
		/**
		 * The ArrayList representation of a single instruction.
		 * Example (setKeyboard): [numClicks, constantDelayOverride, heldDownForXMilliseconds, keyboardButton]
		 */
		public ArrayList<String> instrucVals;
		
		/**
		 * Constructor. Converts the given array to an ArrayList and sets instrucVals to it.
		 * @param arguments The array to convert to the ArrayList
		 */
		public Instruction(String[] arguments) {
			instrucVals = new ArrayList<String>();
			for (String s : arguments) {
				instrucVals.add(s);
			}
		}
		
		/**
		 * Returns a String representation of the ArrayList instrucVals.
		 * @return The String representation of instrucVals
		 */
		public String toString() {
			String outStr = "";
			for (String s : instrucVals) {
				outStr = outStr + s + " ";
			}
			return outStr.substring(0, outStr.length()-1);
		}
	}
	
	/**
	 * Returns an instance of the Instruction class with its variables set to String versions of the equivalent parameters.
	 * @param numClicks The number of clicks to use on the given instruction
	 * @param unicodeKey The Unicode key (In Hex), if applicable
	 * @param keyCombo The keyboard combination / functional key(s), if applicable
	 * @param isUnicode Whether to use the Unicode key or the keyboard combo
	 * @return An instance of the Instruction class with the conversion
	 */
	public Instruction setAdvKeyboard(int numClicks, int unicodeKey, String keyCombo, boolean isUnicode)
	{
		return new Instruction(new String[]{String.valueOf(numClicks), String.valueOf(unicodeKey), keyCombo, String.valueOf(isUnicode)});
	}

	/**
	 * Returns an instance of the Instruction class with its variables set to String versions of the equivalent parameters.
	 * @param numClicks The number of clicks to use on the given instruction
	 * @param constantDelayOverride Amount to override the constant delay, if applicable
	 * @param heldDownForXMilliseconds How long to hold the key down
	 * @param keyboardButton Which keyboard key to press down
	 * @return An instance of the Instruction class with the conversion
	 */
	public Instruction setKeyboard(int numClicks, int constantDelayOverride, int heldDownForXMilliseconds, String keyboardButton)
	{
		return new Instruction(new String[]{String.valueOf(numClicks), String.valueOf(constantDelayOverride), String.valueOf(heldDownForXMilliseconds), keyboardButton});
	}

	/**
	 * Returns an instance of the Instruction class with its variables set to String versions of the equivalent parameters.
	 * Slightly edits the Strings, so that calibration can occur properly.
	 * @param clickUsed Whether or not a click is used
	 * @param nameOfPosition The name for the x/y position of the mouse for use in calibration
	 * @param mouseButton Which mouse button (i.e. differentiating between left, right, and middle clicks), if applicable
	 * @param numClicks The number of clicks to use on the given instruction
	 * @param mouseWheelUsed Whether or not the mouse wheel is used
	 * @param mouseWheelAmt How much to move the mouse wheel, if applicable
	 * @param constantDelayOverride Amount to override the constant delay, if applicable
	 * @param heldDownForXMilliseconds How long to hold the key down
	 * @return An instance of the Instruction class with the conversion
	 */
	public Instruction setMouse(boolean clickUsed, String nameOfPosition, String mouseButton, int numClicks, boolean
			mouseWheelUsed, int mouseWheelAmt, int constantDelayOverride, int heldDownForXMilliseconds)
	{
		return new Instruction(new String[]{
				String.valueOf(clickUsed),
				"x:"+toPascalCase(nameOfPosition),
				mouseButton,
				String.valueOf(numClicks),
				String.valueOf(mouseWheelUsed),
				String.valueOf(mouseWheelAmt),
				String.valueOf(constantDelayOverride),
				String.valueOf(heldDownForXMilliseconds)
		});
	}

	/**
	 * Returns an instance of the Instruction class with its variables set to String versions of the equivalent parameters.
	 * @param waitTime How long to have the autoclicker wait for
	 * @return An instance of the Instruction class with the conversion
	 */
	public Instruction setWait(int waitTime)
	{
		return new Instruction(new String[]{String.valueOf(waitTime)});
	}

	/**
	 * Returns an instance of the Instruction class with its variables set to String versions of the equivalent parameters.
	 * Slightly edits the Strings, so that calibration can occur properly.
	 * @param imageLocation The image to compare against in calibration
	 * @param nameOfBoundingBox The name for the x1/y1/x2/y2 positions of the borders of the image for use in calibration
	 * @param untilSame Whether or not to wait until the images are the same
	 * @param untilDifferent Whether or not to wait until the images are different
	 * @return An instance of the Instruction class with the conversion
	 */
	public Instruction setCompareImages(String imageLocation, String nameOfBoundingBox, boolean untilSame, boolean untilDifferent)
	{
		return new Instruction(new String[]{
				imageLocation,
				"xs:"+toPascalCase(nameOfBoundingBox),
				String.valueOf(untilSame),
				String.valueOf(untilDifferent),
		});
	}
	
	/**
	 * Convert the given String to Pascal case. (AKA no spaces and the first letter of each word capitalized)
	 * @param inString The String to convert
	 * @return The Pascal case form of the String
	 */
	public String toPascalCase(String inString) {
		String[] spaceDelim = inString.split(" ");
		String outString = "";
		for (String s : spaceDelim) {
			outString = outString + Character.toUpperCase(s.charAt(0)) + s.substring(1);
		}
		return outString;
	}
	
	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		MappingFiles mf = new MappingFiles();
		System.out.println(mf.toPascalCase("my name is jeff and i like math"));
	}
}
