package RunProgram;

import java.awt.AWTException;
import java.io.IOException;

import ClickingTranslation.Mapping;
import GUIs.GUI_Main;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
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
		
		//TODO -> USE THIS FOR TESTING GUI METHODS!!!
		start.gui_createAutoclicker();
		//Example, Testing gui_click()
//		Stage testStage = new Stage();
//		HBox testHBox = start.gui_click(17);
//		Scene scene = new Scene(testHBox, 400, 300);
//		testStage.setScene(scene);
//		testStage.show();
		
//		start.gui_initial();
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
