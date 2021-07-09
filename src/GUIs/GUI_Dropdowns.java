package GUIs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI_Dropdowns {
	
	/*
	 * ALL of the below do the same thing, but for their respective autoclick type:
	 * They provide a GUI of options for that type (For example, the GUI for the "Wait" instruction will have
	 * a single textbox where the user types in how long they want to wait, and then submit that, and after
	 * clicking that button, it calls GUI_General.createAutoclickInstr() and that method's resultant String is
	 * what's returned.
	 * See MappingFiles.java for a quick reference on what the requirements for each autoclick type are.
	 */
	
	public String adv_Keyboard = "";
	public String keyboard = "";
	public String mouse = "";
	public String wait = "";
	public String imageCompare = "";
	
	//THIS IS AN EXAMPLE BUTTON
	public void exampleButton(Stage stage)
	{
		System.out.println("Button clicked!");
		keyboard = "This Should Be the Autoclicker Instruction for Whatever Stage Is Given!";
		stage.close();
	}
	
	public String dropdown_advKeyboard()
	{
		return "";
	}

	public String dropdown_Keyboard()
	{
		
		//THIS IS EXAMPLE CODE TO IMPLEMENT AN EXAMPLE BUTTON; THE BUTTON FOR THE METHOD WILL BE THE EQUIVALENT OF A "SUBMIT" BUTTON!!!
		GUI_Main x = new GUI_Main();
		VBox y = x.gui_general("Initial GUI", false);
		Stage z = new Stage();
		z.setScene(new Scene(y, 420, 225));
		Button test = new Button("TESTING");
		test.setOnAction( e -> exampleButton(z) );
		y.getChildren().add(test);
		z.showAndWait();
		return keyboard;
	}

	public String dropdown_Mouse()
	{
		return "";
	}

	public String dropdown_Wait()
	{
		return "";
	}

	public String dropdown_ImageCompare(int imageNumber)
	{
		return "";
	}
	
	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {

		
		
		
		
		
		
	}

	
}
