package GUIs;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
	/*
	 * public void exampleButton(Stage stage) {
	 * System.out.println("Button clicked!"); keyboard =
	 * "This Should Be the Autoclicker Instruction for Whatever Stage Is Given!";
	 * stage.close(); }
	 */
	
	public String dropdown_advKeyboard()
	{
		return "";
	}

	public String dropdown_Keyboard()
	{
		Stage s = new Stage();
		VBox vb = new VBox();
		Text t1 = new Text("Number of presses per key:");
		TextField clickField = new TextField("1");
		Text t2 = new Text("Delay between key presses:");
		TextField cdoField = new TextField("0");
		Text t3 = new Text("How long to hold each key:");
		TextField holdField = new TextField("0");
		Text t4 = new Text("Sequence of keys:");
		TextField strField = new TextField();
		Button submit = new Button("Submit Instruction");
		submit.setOnMouseClicked(event -> s.hide());
		vb.getChildren().addAll(t1, clickField, t2, cdoField, t3, holdField, t4, strField, submit);
		s.setScene(new Scene(vb, 420, 275));
		s.showAndWait();
		return "Keyboard " + clickField.getText() + " " + cdoField.getText() + " " + holdField.getText() + " " + strField.getText();
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
