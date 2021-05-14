package ClickingTranslation;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;

public class Mouse {

	private boolean clickUsed = false;
	private int xPos = 0;
	private int yPos = 0;
	
	/**
	 * This is which button to click on the mouse, i.e. LeftClick, RightClick, etc.
	 */
	private String mouseButton = "";
	
	/**
	 * This is how many times the key will be pressed.
	 */
	private int numClicks = 0;
	
	/**
	 * Whether or not the mouse wheel is the instruction to use (Instead of clicking)
	 */
	private boolean mouseWheelUsed = false;
	
	/**
	 * The amount to scroll the mouse wheel (If using it instead of clicking)
	 */
	private int mouseWheelAmt = 0;

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
			
			//If Clicking
			if (clickUsed)
			{
				//Set Up Click
				click.mouseMove(xPos, yPos);
				click.setAutoDelay(constantDelayOverride);
				
				//Set Up Which Mouse Button to Click
				int whichButton = 0;
				if ( mouseButton.equals("LeftClick") )
					whichButton = InputEvent.BUTTON1_DOWN_MASK;
				else if ( mouseButton.equals("RightClick") )
					whichButton = InputEvent.BUTTON3_DOWN_MASK;
				else if ( mouseButton.equals("MiddleClick") )
					whichButton = InputEvent.BUTTON2_DOWN_MASK;
				
				//Click Mouse Button
				for (int i = 0; i < numClicks; i++)
				{
					if ( mouseButton.equals("NoClick") )
						break;
					click.mousePress(whichButton);
					click.delay(heldDownForXMilliseconds);	//Hold Down (If Applicable)
					click.mouseRelease(whichButton);
				}
				click.delay(constantDelayOverride);
			}
			
			//If Mouse Wheel
			else if (mouseWheelUsed)
			{
				click.mouseWheel(mouseWheelAmt);
			}
			
		//Try/Catch Block
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Converts the instructions from String form to the proper forms (As globals). (i.e. ints, etc.)
	 * @param instructions The instructions to be converted
	 */
	private void convertInstructions(ArrayList<String> instructions)
	{
		//Instructions Format = {clickUsed, xPos, yPos, mouseButton, numClicks, mouseWheelUsed, mouseWheelAmt,
		//		constantDelayOverride, heldDownForXMilliseconds}
		String S1 = instructions.get(0);
		String S2 = instructions.get(1);
		String S3 = instructions.get(2);
		String S4 = instructions.get(3);
		String S5 = instructions.get(4);
		String S6 = instructions.get(5);
		String S7 = instructions.get(6);
		String S8 = instructions.get(7);
		String S9 = instructions.get(8);
		if ( S1.equals("true") )
			clickUsed = true;
		if ( S6.equals("true") )
			mouseWheelUsed = true;
		xPos = Integer.parseInt(S2);
		yPos = Integer.parseInt(S3);
		numClicks = Integer.parseInt(S5);
		mouseWheelAmt = Integer.parseInt(S7);
		constantDelayOverride = Integer.parseInt(S8);
		heldDownForXMilliseconds = Integer.parseInt(S9);
		mouseButton = S4;
	}

	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		
		//Example Test
		ArrayList<String> instr = new ArrayList<String>();
		instr.add("false");	//Click Used
		instr.add("1000");	//x Position
		instr.add("540");	//y Position
		instr.add("LeftClick");	//LeftClick / RightClick / MiddleClick / NoClick / ???(If Not Used)
		instr.add("3");	//Number of Clicks
		instr.add("true");	//Mouse Wheel Used
		instr.add("-100");	//Mouse Wheel Amount (Negative = Up, Positive = Down)
		instr.add("0");	//Constant Delay OVERRIDE (DEFAULT Should Be 0)
		instr.add("0");	//Held Down for This Many Milliseconds (PER Click)
		
		Mouse forTesting = new Mouse();
		forTesting.startAutoclick(instr);

	}


}
