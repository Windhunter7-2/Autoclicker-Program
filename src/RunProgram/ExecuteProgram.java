package RunProgram;

import java.awt.AWTException;
import java.io.IOException;

import ClickingTranslation.Mapping;
import GUIs.GUI_Main;
import javafx.application.Application;
import javafx.stage.Stage;

public class ExecuteProgram extends Application {

	//TO DO -> Finishing Planning...
	
	/**
	 * Initializes the program.
	 * @param nullStage A dummy parameter
	 */
	public void start(Stage nullStage)
	{
		GUI_Main start = new GUI_Main();
		start.gui_initial();
	}
	
	/**
	 * Main method. Runs the program.
	 * @param args Command line arguments
	 * @throws AWTException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, AWTException {
		launch(args);
	}

}
