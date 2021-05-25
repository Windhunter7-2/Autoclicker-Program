package ClickingTranslation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Keyboard {

	/**
	 * This is a string of ASCII characters to press on the keyboard, but only the standard ones, such as alphanumeric or symbols;
	 * special ASCII, such as Escape and Enter will be done in Advanced_Keyboard.
	 */
	private String keyboardButton = "";
	
	/**
	 * This is how many times the key will be pressed.
	 */
	private int numClicks = 0;
	
	/**
	 * The default setting is to not have any delay in between button presses; however, sometimes this can be useful, so if this
	 * is not 0, each keypress should be delayed by that amount of time.
	 */
	private int constantDelayOverride = 0;
	
	/**
	 * Sometimes you want to hold down a key rather than just press it; this allows for that; defaultly this is 0, but you can have
	 * it hold down a key for this amount of time.
	 */
	private int heldDownForXMilliseconds = 0;
	
	/**
	 * This starts the auto-click of the given instructions.
	 * @param instructions The instructions given (In order) for the auto-click
	 */
	public void startAutoclick(ArrayList<String> instructions)
	{
		//Set Up Robot
		convertInstructions(instructions);
		Robot click = null;
		try {
			click = new Robot();
			
			//Set Up Click
			click.setAutoDelay(constantDelayOverride);
			
			//Initialize Clicks
			for (int i = 0; i < numClicks; i++)
			{
				//For Each Character
				for (int j = 0; j < keyboardButton.length(); j++)
				{
					//Set Up Shift (If Applicable)
					boolean capitalLetter = false;
					if ( ( Character.isUpperCase(keyboardButton.charAt(j)) ) || (keyboardButton.charAt(j) == '~') 
							|| (keyboardButton.charAt(j) == '!') || (keyboardButton.charAt(j) == '@')
							|| (keyboardButton.charAt(j) == '#') || (keyboardButton.charAt(j) == '$')
							|| (keyboardButton.charAt(j) == '%') || (keyboardButton.charAt(j) == '^')
							|| (keyboardButton.charAt(j) == '&') || (keyboardButton.charAt(j) == '*')
							|| (keyboardButton.charAt(j) == '(') || (keyboardButton.charAt(j) == ')')
							|| (keyboardButton.charAt(j) == '_') || (keyboardButton.charAt(j) == '+')
							|| (keyboardButton.charAt(j) == '|') || (keyboardButton.charAt(j) == '{')
							|| (keyboardButton.charAt(j) == '}') || (keyboardButton.charAt(j) == '\"')
							|| (keyboardButton.charAt(j) == ':') || (keyboardButton.charAt(j) == '?')
							|| (keyboardButton.charAt(j) == '<') || (keyboardButton.charAt(j) == '>') )
						capitalLetter = true;
					
					//Set Up Which Keyboard Button to Click
					int whichButton = 0;
					if ( (keyboardButton.charAt(j) == 'A') || (keyboardButton.charAt(j) == 'a') )
						whichButton = KeyEvent.VK_A;
					else if ( (keyboardButton.charAt(j) == 'B') || (keyboardButton.charAt(j) == 'b') )
						whichButton = KeyEvent.VK_B;
					else if ( (keyboardButton.charAt(j) == 'C') || (keyboardButton.charAt(j) == 'c') )
						whichButton = KeyEvent.VK_C;
					else if ( (keyboardButton.charAt(j) == 'D') || (keyboardButton.charAt(j) == 'd') )
						whichButton = KeyEvent.VK_D;
					else if ( (keyboardButton.charAt(j) == 'E') || (keyboardButton.charAt(j) == 'e') )
						whichButton = KeyEvent.VK_E;
					else if ( (keyboardButton.charAt(j) == 'F') || (keyboardButton.charAt(j) == 'f') )
						whichButton = KeyEvent.VK_F;
					else if ( (keyboardButton.charAt(j) == 'G') || (keyboardButton.charAt(j) == 'g') )
						whichButton = KeyEvent.VK_G;
					else if ( (keyboardButton.charAt(j) == 'H') || (keyboardButton.charAt(j) == 'h') )
						whichButton = KeyEvent.VK_H;
					else if ( (keyboardButton.charAt(j) == 'I') || (keyboardButton.charAt(j) == 'i') )
						whichButton = KeyEvent.VK_I;
					else if ( (keyboardButton.charAt(j) == 'J') || (keyboardButton.charAt(j) == 'j') )
						whichButton = KeyEvent.VK_J;
					else if ( (keyboardButton.charAt(j) == 'K') || (keyboardButton.charAt(j) == 'k') )
						whichButton = KeyEvent.VK_K;
					else if ( (keyboardButton.charAt(j) == 'L') || (keyboardButton.charAt(j) == 'l') )
						whichButton = KeyEvent.VK_L;
					else if ( (keyboardButton.charAt(j) == 'M') || (keyboardButton.charAt(j) == 'm') )
						whichButton = KeyEvent.VK_M;
					else if ( (keyboardButton.charAt(j) == 'N') || (keyboardButton.charAt(j) == 'n') )
						whichButton = KeyEvent.VK_N;
					else if ( (keyboardButton.charAt(j) == 'O') || (keyboardButton.charAt(j) == 'o') )
						whichButton = KeyEvent.VK_O;
					else if ( (keyboardButton.charAt(j) == 'P') || (keyboardButton.charAt(j) == 'p') )
						whichButton = KeyEvent.VK_P;
					else if ( (keyboardButton.charAt(j) == 'Q') || (keyboardButton.charAt(j) == 'q') )
						whichButton = KeyEvent.VK_Q;
					else if ( (keyboardButton.charAt(j) == 'R') || (keyboardButton.charAt(j) == 'r') )
						whichButton = KeyEvent.VK_R;
					else if ( (keyboardButton.charAt(j) == 'S') || (keyboardButton.charAt(j) == 's') )
						whichButton = KeyEvent.VK_S;
					else if ( (keyboardButton.charAt(j) == 'T') || (keyboardButton.charAt(j) == 't') )
						whichButton = KeyEvent.VK_T;
					else if ( (keyboardButton.charAt(j) == 'U') || (keyboardButton.charAt(j) == 'u') )
						whichButton = KeyEvent.VK_U;
					else if ( (keyboardButton.charAt(j) == 'V') || (keyboardButton.charAt(j) == 'v') )
						whichButton = KeyEvent.VK_V;
					else if ( (keyboardButton.charAt(j) == 'W') || (keyboardButton.charAt(j) == 'w') )
						whichButton = KeyEvent.VK_W;
					else if ( (keyboardButton.charAt(j) == 'X') || (keyboardButton.charAt(j) == 'x') )
						whichButton = KeyEvent.VK_X;
					else if ( (keyboardButton.charAt(j) == 'Y') || (keyboardButton.charAt(j) == 'y') )
						whichButton = KeyEvent.VK_Y;
					else if ( (keyboardButton.charAt(j) == 'Z') || (keyboardButton.charAt(j) == 'z') )
						whichButton = KeyEvent.VK_Z;
					else if ( (keyboardButton.charAt(j) == '0') || (keyboardButton.charAt(j) == ')') )
						whichButton = KeyEvent.VK_0;
					else if ( (keyboardButton.charAt(j) == '1') || (keyboardButton.charAt(j) == '!') )
						whichButton = KeyEvent.VK_1;
					else if ( (keyboardButton.charAt(j) == '2') || (keyboardButton.charAt(j) == '@') )
						whichButton = KeyEvent.VK_2;
					else if ( (keyboardButton.charAt(j) == '3') || (keyboardButton.charAt(j) == '#') )
						whichButton = KeyEvent.VK_3;
					else if ( (keyboardButton.charAt(j) == '4') || (keyboardButton.charAt(j) == '$') )
						whichButton = KeyEvent.VK_4;
					else if ( (keyboardButton.charAt(j) == '5') || (keyboardButton.charAt(j) == '%') )
						whichButton = KeyEvent.VK_5;
					else if ( (keyboardButton.charAt(j) == '6') || (keyboardButton.charAt(j) == '^') )
						whichButton = KeyEvent.VK_6;
					else if ( (keyboardButton.charAt(j) == '7') || (keyboardButton.charAt(j) == '&') )
						whichButton = KeyEvent.VK_7;
					else if ( (keyboardButton.charAt(j) == '8') || (keyboardButton.charAt(j) == '*') )
						whichButton = KeyEvent.VK_8;
					else if ( (keyboardButton.charAt(j) == '9') || (keyboardButton.charAt(j) == '(') )
						whichButton = KeyEvent.VK_9;
					else if ( (keyboardButton.charAt(j) == '`') || (keyboardButton.charAt(j) == '~') )
						whichButton = KeyEvent.VK_BACK_QUOTE;
					else if ( (keyboardButton.charAt(j) == '-') || (keyboardButton.charAt(j) == '_') )
						whichButton = KeyEvent.VK_MINUS;
					else if ( (keyboardButton.charAt(j) == '=') || (keyboardButton.charAt(j) == '+') )
						whichButton = KeyEvent.VK_EQUALS;
					else if ( (keyboardButton.charAt(j) == '[') || (keyboardButton.charAt(j) == '{') )
						whichButton = KeyEvent.VK_OPEN_BRACKET;
					else if ( (keyboardButton.charAt(j) == ']') || (keyboardButton.charAt(j) == '}') )
						whichButton = KeyEvent.VK_CLOSE_BRACKET;
					else if ( (keyboardButton.charAt(j) == '\\') || (keyboardButton.charAt(j) == '|') )
						whichButton = KeyEvent.VK_BACK_SLASH;
					else if ( (keyboardButton.charAt(j) == ';') || (keyboardButton.charAt(j) == ':') )
						whichButton = KeyEvent.VK_SEMICOLON;
					else if ( (keyboardButton.charAt(j) == '\'') || (keyboardButton.charAt(j) == '\"') )
						whichButton = KeyEvent.VK_QUOTE;
					else if ( (keyboardButton.charAt(j) == ',') || (keyboardButton.charAt(j) == '<') )
						whichButton = KeyEvent.VK_COMMA;
					else if ( (keyboardButton.charAt(j) == '.') || (keyboardButton.charAt(j) == '>') )
						whichButton = KeyEvent.VK_PERIOD;
					else if ( (keyboardButton.charAt(j) == '/') || (keyboardButton.charAt(j) == '?') )
						whichButton = KeyEvent.VK_SLASH;
					else
						whichButton = KeyEvent.VK_SPACE;
					
					//Click Keyboard Button
					if (capitalLetter)
						click.keyPress(KeyEvent.VK_SHIFT);
					click.keyPress(whichButton);
					click.delay(heldDownForXMilliseconds);	//Hold Down (If Applicable)
					if (capitalLetter)
						click.keyRelease(KeyEvent.VK_SHIFT);
					click.keyRelease(whichButton);
				}
				
				//Delay Override (If Applicable) for Entire String
				click.delay(constantDelayOverride);
			}
				
		//Try/Catch Block
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Converts the instructions from String form to the proper forms (As globals). (i.e. ints, etc.)
	 * @param instructions The instructions to be converted
	 */
	private void convertInstructions(ArrayList<String> instructions)
	{
		//Instructions Format = {numClicks, constantDelayOverride, heldDownForXMilliseconds, keyboardButton}
		String S1 = instructions.get(0);
		String S2 = instructions.get(1);
		String S3 = instructions.get(2);
		String S4 = instructions.get(3);
		numClicks = Integer.parseInt(S1);
		constantDelayOverride = Integer.parseInt(S2);
		heldDownForXMilliseconds = Integer.parseInt(S3);
		keyboardButton = S4;
	}

	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		ArrayList<String> instr = new ArrayList<String>();
		instr.add("1");	//Number of Clicks
		instr.add("0");	//Constant Delay OVERRIDE (DEFAULT Should Be 0)
		instr.add("0");	//Held Down for This Many Milliseconds (PER Click)
		instr.add("Xena Alcatraz [ { ] } \\ | ; : \' \" , < . > / ?     \'");	//Key / Set of Keys to Click
		
		Keyboard forTesting = new Keyboard();
		forTesting.startAutoclick(instr);

	}


}
