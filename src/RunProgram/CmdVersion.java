package RunProgram;

import java.awt.AWTException;
import java.io.IOException;
import GUIs.GUI_General;
import GUIs.GUI_Main;
import javafx.application.Application;
import javafx.stage.Stage;

public class CmdVersion extends Application{
	
	public static String argType;
	public static String input;
	
	/**
	 * Initializes the program. It chooses a particular method to run, based off of what the commandline input is.
	 * @param nullStage A dummy parameter
	 * @throws IOException 
	 * @throws AWTException 
	 * @throws InterruptedException 
	 */
	public void start(Stage nullStage) throws IOException, InterruptedException, AWTException
	{
		//If Creating an Autoclicker; Input Not Used Here
		if ( argType.equals("Create") )
		{
			GUI_Main create = new GUI_Main();
			create.gui_createAutoclicker();
		}
		
		//If Loading an Autoclicker; Input = Name of Autoclicker to Be Loaded/Run (NOT Full Paths of Any Kind)
		if ( argType.equals("Run") )
		{
			GUI_General run = new GUI_General();
			run.runAutoclick(input);
			System.exit(0);
		}
	}
	
	/**
	 * Main method. Runs the program, and sets the command-line arguments.
	 * Since getText() and setText() both use the main directory, the input should not include the main directory of these programs.
	 * (For example, if you installed the general programs in C:/Dragon Games/, then "C:/Dragon Games/" should *not* be in the input)
	 * @param args Command line arguments
	 * @throws AWTException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, AWTException {
		argType = args[0];
		input = args[1];	//See Note About Input! This Commandline Interface Is Primarily for Other Programs' Automations
		launch(args);
	}
}
