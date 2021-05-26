/**
 * Important Note: The executeInstruction() method is going to be the only commonly-edited part of this class for various versions.
 */

package ClickingTranslation;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import ImageComparison.CompareImages;

public class Mapping {
	
	/**
	 * This executes a particular instruction given (An instruction is all the words of one of the lines from the user's file).
	 * @param instructions The *current* instructions given on what to do
	 */
	private void executeInstruction(ArrayList<String> instructions)
	{
		//Set First Word (AKA Which Class to Run)
		String first = instructions.get(0);
		
		//Remove First Word from Instructions
		instructions.remove(0);
		
		//Execute the Appropriate Class
		if ( first.equals("Mouse") )
		{
			Mouse start = new Mouse();
			start.startAutoclick(instructions);
		}
		else if ( first.equals("Keyboard") )
		{
			Keyboard start = new Keyboard();
			start.startAutoclick(instructions);
		}
		else if ( first.equals("AdvKeyboard") )
		{
			Advanced_Keyboard start = new Advanced_Keyboard();
			start.startAutoclick(instructions);
		}
		else if ( first.equals("Wait") )
		{
			CompareImages start = new CompareImages();
			start.startAutoclick_wait(instructions);
		}
		else if ( first.equals("CompareImages") )
		{
			CompareImages start = new CompareImages();
			start.startAutoclick_cmpImg(instructions);
		}
		
		//MAJOR EDITING: INSERT OTHER FORMS OF AUTO-CLICKS <HERE>...
		
		//Else; for Errors, Notify User of Invalid Input
		else
			JOptionPane.showMessageDialog(null, "The instruction \"" + first + " " + instructions + "\" is not a valid one."
					+ " Please make sure that, if you did any edits, or encountered a bug that would result in this type"
					+ " error, please be sure to fix it. (And, if a bug, to mention it)");
	}
	
	/**
	 * This takes a String (From the file contents of the user), and runs the mapping portion of the clicking translation.
	 * @param fileContents The contents from the user's file, in total
	 */
	public void startMap(String fileContents)
	{
		ArrayList<String> lines = contentsToLines(fileContents);
		for (int i = 0; i < lines.size(); i++)
		{
			ArrayList<String> instructions = lineToInstructions( lines.get(i) );
			executeInstruction(instructions);
		}
	}
	
	/**
	 * This takes a String (From the file contents of the user), and turns it into an ArrayList of the lines of that file.
	 * @param fileContents The contents from the user's file, in total
	 * @return returned The ArrayList with these Strings (In order)
	 */
	private ArrayList<String> contentsToLines(String fileContents)
	{
		ArrayList<String> returned = new ArrayList<String>();
		String currentLine = "";
		for (int currentChar = 0; true; currentChar++)
		{
			//End of File Contents; Break
			if (fileContents.length() == currentChar)
				break;
			
			//Get Current Character
			char tempC = fileContents.charAt(currentChar);
			
			//If End of Line, Add to List of Lines
			if ( (tempC == '\r') || (tempC == '\n') )
			{
				if ( !currentLine.isEmpty() )
				{
					returned.add(currentLine);
					currentLine = "";
				}
			}
			
			//Else, Add Character to Current Line
			else
				currentLine = (currentLine + tempC);
		}
		
		//Add Last Line
		if ( !currentLine.isEmpty() )
			returned.add(currentLine);
		
		//Return
		return returned;
	}
	
	/**
	 * This takes a line from the user's file contents, and converts it to instruction format.
	 * @param line The *current* line from the user's file contents
	 * @return returned The ArrayList with the instruction parts (In order)
	 */
	private ArrayList<String> lineToInstructions(String line)
	{
		ArrayList<String> returned = new ArrayList<String>();
		String currentWord = "";
		for (int currentChar = 0; true; currentChar++)
		{
			//End of File Contents; Break
			if (line.length() == currentChar)
				break;
			
			//Get Current Character
			char tempC = line.charAt(currentChar);
			
			//If Space, Add to List of Instruction Parts (AKA Words)
			if ( (tempC == ' ') || (tempC == '\t') )
			{
				if ( !currentWord.isEmpty() )
				{
					returned.add(currentWord);
					currentWord = "";
				}
			}
			
			//Else, Add Character to Current Word
			else
				currentWord = (currentWord + tempC);
		}
		
		//Add Last Word
		if ( !currentWord.isEmpty() )
			returned.add(currentWord);
		
		//Return
		return returned;
	}

	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		
		//Example Test
		Mapping forTesting = new Mapping();
		forTesting.startMap("Mouse true 1000 540 LeftClick 2 false -1 200 0\n"
				+ "          Mouse true 1000 740 LeftClick 1 false -1 200 0");

	}

}
