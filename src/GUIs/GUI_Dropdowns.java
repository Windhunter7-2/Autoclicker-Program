package GUIs;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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
		Stage s = new Stage();
		VBox vb = new VBox();
		
		HBox hb1 = new HBox(new Text("Use click?"));
		CheckBox cb1 = new CheckBox();
		hb1.getChildren().add(cb1);
		
		HBox hb2 = new HBox(new Text("Name for this action:"));
		TextField nameField = new TextField();
		hb2.getChildren().add(nameField);
		
		HBox hb3 = new HBox(new Text("Type of click:"));
		TextField typeField = new TextField("LeftClick");
		hb3.getChildren().add(typeField);
		
		HBox hb4 = new HBox(new Text("Number of clicks:"));
		TextField numField = new TextField("0");
		hb4.getChildren().add(numField);
		
		HBox hb5 = new HBox(new Text("Use mouse wheel?"));
		CheckBox cb2 = new CheckBox();
		hb5.getChildren().add(cb2);
		
		HBox hb6 = new HBox(new Text("How far to scroll? (positive values scroll downwards)"));
		TextField scrollField = new TextField("0");
		hb6.getChildren().add(scrollField);
		
		HBox hb7 = new HBox(new Text("Delay between clicks / turns:"));
		TextField cdoField = new TextField("0");
		hb7.getChildren().add(cdoField);
		
		HBox hb8 = new HBox(new Text("How long to hold each click:"));
		TextField holdField = new TextField("0");
		hb8.getChildren().add(holdField);
		
		Button submit = new Button("Submit Instruction");
		submit.setOnMouseClicked(event -> s.hide());
		vb.getChildren().addAll(hb1, hb2, hb3, hb4, hb5, hb6, hb7, hb8, submit);
		s.setScene(new Scene(vb, 420, 475));
		s.showAndWait();
		return "Mouse " 
		+ cb1.isSelected() 
		+ " x:"
		+ nameField.getText()
		+ " "
		+ typeField.getText()
		+ " "
		+ numField.getText()
		+ " "
		+ cb2.isSelected()
		+ " "
		+ scrollField.getText()
		+ " "
		+ cdoField.getText()
		+ " "
		+ holdField.getText();
		
		
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
