package FileHandling;

import java.util.ArrayList;

public class MappingFiles {
	
	private String needToStillImplementThis;
	
	public class Instruction
	{
		//Example (setKeyboard): [numClicks, constantDelayOverride, heldDownForXMilliseconds, keyboardButton]
		public ArrayList<String> instrucVals;
	}
	
	public Instruction setAdvKeyboard(int numClicks, int unicodeKey, String keyCombo, boolean isUnicode)
	{
		/*
		 * Must be O(1)
		 * Return an instance of the Instruction class with its variables set to String versions of the equivalent parameters
		 */
		return null;
	}

	public Instruction setKeyboard(int numClicks, int constantDelayOverride, int heldDownForXMilliseconds, String keyboardButton)
	{
		/*
		 * Must be O(1)
		 * Return an instance of the Instruction class with its variables set to String versions of the equivalent parameters
		 */
		return null;
	}

	public Instruction setMouse(boolean clickUsed, String nameOfPosition, String mouseButton, int numClicks, boolean
			mouseWheelUsed, int mouseWheelAmt, int constantDelayOverride, int heldDownForXMilliseconds)
	{
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
		return null;
	}

	public Instruction setWait(int waitTime)
	{
		/*
		 * Must be O(1)
		 * Return an instance of the Instruction class with its variable set to a String version of waitTime
		 */
		return null;
	}

	public Instruction setCompareImages(String imageLocation, String nameOfBoundingBox, boolean untilSame, boolean untilDifferent)
	{
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
		return null;
	}

	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
