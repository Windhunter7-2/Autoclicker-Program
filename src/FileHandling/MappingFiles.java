package FileHandling;

import java.util.ArrayList;

public class MappingFiles {
		
	public class Instruction
	{
		//Example (setKeyboard): [numClicks, constantDelayOverride, heldDownForXMilliseconds, keyboardButton]
		public ArrayList<String> instrucVals;
		
		public Instruction(String[] arguments) {
			instrucVals = new ArrayList<String>();
			for (String s : arguments) {
				instrucVals.add(s);
			}
		}
	}
	
	
	public Instruction setAdvKeyboard(int numClicks, int unicodeKey, String keyCombo, boolean isUnicode)
	{
		return new Instruction(new String[]{String.valueOf(numClicks), String.valueOf(unicodeKey), keyCombo, String.valueOf(isUnicode)});
		/*
		 * Must be O(1)
		 * Return an instance of the Instruction class with its variables set to String versions of the equivalent parameters
		 */
	}

	public Instruction setKeyboard(int numClicks, int constantDelayOverride, int heldDownForXMilliseconds, String keyboardButton)
	{
		/*
		 * Must be O(1)
		 * Return an instance of the Instruction class with its variables set to String versions of the equivalent parameters
		 */
		return new Instruction(new String[]{String.valueOf(numClicks), String.valueOf(constantDelayOverride), String.valueOf(heldDownForXMilliseconds), keyboardButton});
	}

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
		//return
		/*
		 * Must be O(n), where n is the number of characters in nameOfPosition
		 * Return an instance of the Instruction class with its variables set to String versions of the equivalent parameters
		 * xPos and yPos for Mouse clicks won't actually be known at the time of this call, so instead, nameOfPosition is what is
		 * indicated by the creator of the autoclicker, and the actual String itself to store into the resulting Instruction for
		 * nameOfPosition should not be "nameOfPosition" by itself, but actually "x:NameOfPosition", where the words are "scrunched"
		 * together and the first letter of each word from the user becomes capitalized; for example, if nameOfPosition equals
		 * "This is an Example x/y", then what would be stored into the String would, instead of "This is an Example x/y", be
		 * "ThisIsAnExampleX/y"
		 */
		
		//return null;
	}

	public Instruction setWait(int waitTime)
	{
		return new Instruction(new String[]{String.valueOf(waitTime)});
		/*
		 * Must be O(1)
		 * Return an instance of the Instruction class with its variable set to a String version of waitTime
		 */
	}

	public Instruction setCompareImages(String imageLocation, String nameOfBoundingBox, boolean untilSame, boolean untilDifferent)
	{
		return new Instruction(new String[]{
				imageLocation,
				"xs:"+toPascalCase(nameOfBoundingBox),
				String.valueOf(untilSame),
				String.valueOf(untilDifferent),
		});
		/*
		 * Must be O(1)
		 * Return an instance of the Instruction class with its variables set to String versions of the equivalent parameters
		 * The 4 corner pixel locations of an image click won't actually be known at the time of this call, so instead,
		 * nameOfBoundingBox is what is indicated by the creator of the autoclicker, and the actual String itself to store into
		 * the resulting Instruction for nameOfBoundingBox should not be "nameOfBoundingBox" by itself, but actually
		 * "xs:NameOfBoundingBox", where the words are "scrunched" together and the first letter of each word from the user becomes
		 * capitalized; for example, if nameOfBoundingBox equals "This is an Example x1/y1/x2/y2", then what would be stored into
		 * the String would, instead of "This is an Example x1/y1/x2/y2", be "ThisIsAnExampleX1/y1/x2/y2"
		 */
	}
	
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
		System.out.println(mf.toPascalCase("my name is jeff and i like meth"));
	}
}
