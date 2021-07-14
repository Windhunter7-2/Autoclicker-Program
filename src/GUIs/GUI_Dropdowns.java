package GUIs;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
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
		Text t1 = new Text("Number of times to iterate this keyboard sequence:");
		TextField clickField = new TextField("1");
		Text t2 = new Text("Delay between key presses (In milliseconds):");
		TextField cdoField = new TextField("0");
		Text t3 = new Text("How long to hold each key (In milliseconds):");
		TextField holdField = new TextField("0");
		Text t4 = new Text("Sequence of keys:");
		TextField strField = new TextField("ABC");
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
		TextField nameField = new TextField("Name Of Click");
		nameField.setText( nameField.getText().replaceAll("\\s+", "") );	//Delete Spaces from Name Field
		hb2.getChildren().add(nameField);
		
		//Dropdown for Click Type
		HBox hb3 = new HBox(new Text("Type of click:"));
		ChoiceBox<String> typeField = new ChoiceBox<>();
		String select = "Select Click Type...";
		typeField.getItems().add(select);
		typeField.getItems().add("Left Click");
		typeField.getItems().add("Right Click");
		typeField.getItems().add("Middle Click");
		typeField.getItems().add("No Click (Use Scroller)");
		typeField.setValue(select);
		if (typeField.getValue() != null)	//Remove Original "Selection" Button
			typeField.getItems().remove(0);
		hb3.getChildren().add(typeField);	//Add to HBox
		
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
		
		//Dropdown Text Set
		String type = typeField.getValue();
		type = type.replaceAll("\\s+","");	//Delete Spaces
		if ( type.equals("NoClick(UseScroller)") || (type.equals("SelectClickType...")) )
			type = "NoClick";
		
		return "Mouse " 
		+ cb1.isSelected() 
		+ " x:"
		+ nameField.getText()
		+ " "
		+ type
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
		//Set Stage and Boxes
		Stage s = new Stage();
		VBox vb = new VBox();
		
		//Selecting to Wait Until Same Or Different
		Text t1 = new Text("Select if you would like to wait until the images are the same or different:\n\n");
		Text t2 = new Text("Wait Until Images Are the Same");
		Text t3 = new Text("Wait Until Images Are Different");
		Text t4 = new Text("\nNote: The image location and image to compare against will be configured during calibration"
				+ "\n(Calibration occurs when the autoclicker is loaded the first time)\n\n");
		HBox hb = new HBox();
		CheckBox cb1 = new CheckBox();
		cb1.setSelected(true);
		CheckBox cb2 = new CheckBox();
		hb.getChildren().addAll(t2, cb1, t3, cb2);
		
		//Image Name
		Text t5 = new Text("What would you like to call this image to be compared?");
		TextField nameField = new TextField("Name of Image");
		
		//Submit Button
		Button submit = new Button("Submit Instruction");
		submit.setOnMouseClicked(event -> s.hide());
		submit.setTranslateY(20);
		
		//Set Scene
		vb.getChildren().addAll(t1, hb, t4, t5, nameField, submit);
		s.setScene(new Scene(vb, 520, 275));
		s.showAndWait();
		return ( "CompareImages "
				+ "autoclickerName_" + imageNumber + ".png "
				+ "xs:" + nameField.getText().replaceAll("\\s+", "") + " "
				+ cb1.isSelected() + " " + cb2.isSelected() );
	}
	
	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		
	}

	
}
