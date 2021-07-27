package ClickingTranslation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

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
	
	/**
	 * This is whether this class is processing 
	 */
	private boolean isUnicode;

	/**
	 * This starts the auto-click of the given instructions.
	 * @param instructions The instructions given (In order) for the auto-click
	 * @throws InterruptedException
	 */
	public void startAutoclick(ArrayList<String> instructions) throws InterruptedException
	{
		convertInstructions(instructions);
		Robot click = null;
		try {
			click = new Robot();
			for (int i = 0; i < numClicks; ++i) {
				if (isUnicode) {
					clickUnicode(click);
				} else {
					switch (keyCombo) {	
					case "Windows + .":
						pressOrder(click, new int[]{KeyEvent.VK_WINDOWS, KeyEvent.VK_PERIOD});
						break;
					case "Windows + Shift + S":
						pressOrder(click, new int[] {KeyEvent.VK_WINDOWS, KeyEvent.VK_SHIFT, KeyEvent.VK_S});
						break;
					case "Alt + Tab":
						pressOrder(click, new int[] {KeyEvent.VK_ALT, KeyEvent.VK_TAB});
						break;
					case "Printscreen":
						pressOrder(click, new int[] {KeyEvent.VK_PRINTSCREEN});
						break;
					case "Caps Lock":
						pressOrder(click, new int[] {KeyEvent.VK_CAPS_LOCK});
						break;
					case "Tab":
						pressOrder(click, new int[] {KeyEvent.VK_TAB});
						break;
					case "Enter":
						pressOrder(click, new int[] {KeyEvent.VK_ENTER});
						break;
					case "Escape":
						pressOrder(click, new int[] {KeyEvent.VK_ESCAPE});
						break;
						/*NOTE: If the user is already holding a key when click.keyRelease is called,
						 *then the user's held key will also be canceled.
						 *For instance, if the user is holding SHIFT when that key is released,
						 *subsequent letters will be lowercase even if the user keeps holding shift.
						 *This doesn't break anything - releasing the key and pressing it again still works-
						 *but it is a behavior to keep in mind.						
						 */
					case "Shift": //hold
						click.keyPress(KeyEvent.VK_SHIFT);
						click.delay(1000); //TODO figure out how long to hold for
						click.keyRelease(KeyEvent.VK_SHIFT);
						break;
					case "Alt": //hold
						click.keyPress(KeyEvent.VK_ALT);
						click.delay(1000); //TODO figure out how long to hold for
						click.keyRelease(KeyEvent.VK_ALT);
						break;
					case "Ctrl": //hold
						click.keyPress(KeyEvent.VK_CONTROL);
						click.delay(1000); //TODO figure out how long to hold for
						click.keyRelease(KeyEvent.VK_CONTROL);
						break;
					case "F1":
						pressOrder(click, new int[] {KeyEvent.VK_F1});
						break;
					case "F2":
						pressOrder(click, new int[] {KeyEvent.VK_F2});
						break;
					case "F3":
						pressOrder(click, new int[] {KeyEvent.VK_F3});
						break;
					case "F4":
						pressOrder(click, new int[] {KeyEvent.VK_F4});
						break;
					case "F5":
						pressOrder(click, new int[] {KeyEvent.VK_F5});
						break;
					case "F6":
						pressOrder(click, new int[] {KeyEvent.VK_F6});
						break;
					case "F7":
						pressOrder(click, new int[] {KeyEvent.VK_F7});
						break;
					case "F8":
						pressOrder(click, new int[] {KeyEvent.VK_F8});
						break;
					case "F9":
						pressOrder(click, new int[] {KeyEvent.VK_F9});
						break;
					case "F10":
						pressOrder(click, new int[] {KeyEvent.VK_F10});
						break;
					case "F11": //Warning: this runs the program in Eclipse. If you test it in Eclipse, it may cause the program to execute copies of itself.
						pressOrder(click, new int[] {KeyEvent.VK_F11});
						break;
					case "F12":
						pressOrder(click, new int[] {KeyEvent.VK_F12});
						break;
					default:
						throw new UnsupportedOperationException("The keyboard combination \"" + keyCombo + "\" is not defined");
					}
				}
			}
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Presses keys in the given order, left to right.
	 * Releases them in reverse order.
	 * O(n), where n is the number of keys.
	 * @param r The Robot that does the KeyEvents.
	 * @param events the list of KeyEvents.
	 */
	private void pressOrder(Robot r, int[] events) {
		for(int i = 0; i < events.length; ++i) {
			r.keyPress(events[i]);
		}
		for(int i = events.length - 1; i >= 0; --i) {
			r.keyRelease(events[i]);
		}
	}
	
	/**
	 * Used to print a unicode character.
	 * This isn't quite an "input", as many unicode characters don't have direct device inputs.
	 * Instead, it's a copy-paste from a file containing several Unicode planes.
	 * @param r The Robot that does the KeyEvents
	 * @throws InterruptedException
	 */
	private void clickUnicode(Robot r) throws InterruptedException
	{
		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection s = new StringSelection(Character.valueOf((char)(unicodeKey)).toString());
		c.setContents(s, null);
		pressOrder(r, new int[] {KeyEvent.VK_CONTROL, KeyEvent.VK_V});
		Thread.sleep(100);
	}
	
	/**
	 * Converts the instructions from String form to the proper forms (As globals). (i.e. ints, etc.)
	 * @param instructions The instructions to be converted
	 */
	private void convertInstructions(ArrayList<String> instructions)
	{
		numClicks = Integer.valueOf(instructions.get(0));
		try {
			unicodeKey = Integer.valueOf(instructions.get(1), 16);
		} catch (NumberFormatException e) {
			throw e;
		}
		keyCombo = instructions.get(2);
		isUnicode = Boolean.valueOf(instructions.get(3));
	}

	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException { 
		Advanced_Keyboard forTesting = new Advanced_Keyboard();
		for (int i = 0; i < 26; ++i) {
			ArrayList<String> instr = new ArrayList<String>();
			instr.add("1");	//Number of Clicks
			instr.add(String.valueOf(8162 + i));	//UnicodeKey
			instr.add("F4");//KeyCombo
			instr.add("true"); //IsUnicode
			forTesting.startAutoclick(instr);			//Thread.sleep(1000L);
		}
	}
}
