package ClickingTranslation;

import java.util.ArrayList;

public class Advanced_Keyboard {
	
	/**
	 * Since Unicode characters can’t be *directly* gotten in Java, this is just an integer representing the current
	 * Unicode character to be called. This integer corresponds to the hexadecimal Unicode Number; for example, 225 (E1 in hex)
	 * refers to the symbol “á”. The full chart of Unicode characters and their numbers can be found here:
	 * 			https://unicode-table.com/en/
	 */
	private int unicodeKey;
	
	/**
	 * This is the representation of a keyboard combination, for keyboard actions, such as Windows+Shift+S or Windows+ . , for example.
	 */
	private String keyCombo;
	
	/**
	 * This is how many times the key will be pressed.
	 */
	private int numClicks;
	
	public void startAutoclick(ArrayList<String> instructions)
	{
		/*
		 * Must be O(n), where n is numClicks
		 * This starts the auto-click of the given instructions. First call convertInstructions(instructions) to set the
		 * globals to be used, and then implement the keypresses, via either calling clickUnicode(), if unicodeKey is not
		 * equal to 0, or if it is 0, instead do a series of keyPresses with a Robot class, matching the keyboard combination
		 * in the String keyCombo. The currently supported ones in Version 1 of the program will be:
		 * “Windows + .”, “Windows + Shift + S”, “Alt + Tab”, “Printscreen”, “Caps Lock”, “Tab”, “Enter”, “Escape”, “Delete”,
		 * “Backspace”, “Shift” (Hold), “Alt” (Hold), “Ctrl” (Hold), and “FX” where “X” is which number (For example, “F1”, “F2”, etc.)
		 * For example, if after calling convertInstructions(), numClicks is 2, unicodeKey is 0, and keyCombo is “Alt + Tab”, it should
		 * use the Robot to hold down the Alt key while pressing the Tab key, and then do that a second time, as well. See the Keyboard
		 * class for a similar example.
		 */
	}
	
	private void clickUnicode()
	{
		/*
		 * Must be less than or equal to O(n), where n is the number of Unicode characters
		 * Creating the additionally required file (The Unicode document) is part of this method, and the reason is that since this
		 * is open-ended implementation, you can design the document however you wish; just make sure that the integers in the document
		 * represent the Unicode counterparts in the same way as mentioned in the description of the unicodeKey variable.
		 * (Note that the variable “unicodeKey” is the integer you’re converting to the Unicode character) This function should use a
		 * Robot class and a series of key presses to simulate “pressing a key” that represents a single Unicode character;
		 * specifically, it should get the corresponding character from the document, use auto clicking to physically select the
		 * associated character and copy that to the user’s clipboard, followed by pasting that character from the clipboard (Via Ctrl+V).
		 * Note that much of this will be a lot easier using the already-defined auto-clicks, which is fine for this particular method.
		 */
	}
	
	private void convertInstructions(ArrayList<String> instructions)
	{
		/*
		 * Must be O(1)
		 * Given that the format of the given instructions ArrayList is the following:
		 * [numClicks, unicodeKey, keyCombo, isUnicode]
		 * Where each corresponds to its respective index ( For example, numClicks is instructions.get(0) ), and isUnicode is 0
		 * for using a keyCombo and 1 for using a unicodeKey, set all 4 of these to their respective global variables. Note that
		 * there is no checking needed, and all 4 are always set, 1 to 1.
		 */
	}

	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		


	}

}
