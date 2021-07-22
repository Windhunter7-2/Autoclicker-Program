package GUIs;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI_Dropdowns {
	
	/**
	 * Sub-GUI for advanced keyboards: Unicode keys.
	 * @param numClicks The number of times to do this action
	 * @return The String representation of the GUI fields filled out
	 */
	private String advKeyboard_unicode(int numClicks)
	{
		//Set Stage and Boxes
		Stage s = new Stage();
		s.setTitle("Define Unicode Input");
		VBox vb = new VBox();
		
		//Unicode Key
		Text t = new Text("Enter the code point of the Unicode character you are using:\n(This is the 4-digit hexadecimal"
				+ "representation; you can find all Unicode code points here:\n\t\thttps://unicode-table.com/en/)\n\n");
		TextField unicodeField = new TextField("1F600");
		
		//Submit Button
		Button submit = new Button("Submit Instruction");
		submit.setOnMouseClicked(event -> s.hide());
		submit.setTranslateY(20);
		
		//Set Scene
		vb.getChildren().addAll(t, unicodeField, submit);
		s.setScene(new Scene(vb, 520, 275));
		s.showAndWait();

		//Return
		return ( "AdvKeyboard "
				+ numClicks + " "
				+ unicodeField.getText() + " "
				+ "N/A true");
	}
	
	/**
	 * Sub-GUI for advanced keyboards: common special keys selection.
	 * @param numClicks The number of times to do this action
	 * @return The String representation of the GUI fields filled out
	 */
	private String advKeyboard_keyCombo_common(int numClicks)
	{
		//Set Stage and Boxes
		Stage s = new Stage();
		s.setTitle("Define Key Combo Input");
		VBox vb = new VBox();
		
		//Grid GUI for Keyboard Combo
		Text t = new Text("Type of click:");
		ComboBox<String> typeField = new ComboBox<>();
		String select = "Select Click Type...";
		typeField.getItems().add(select);
		typeField.setValue(select);
		
		//Dropdown Keyboard Combinations
		typeField.getItems().add("Printscreen");
		typeField.getItems().add("Caps Lock");
		typeField.getItems().add("Tab");
		typeField.getItems().add("Enter");
		typeField.getItems().add("Escape");
		
		
		
		
		//TODO -> Version 1.2: Add These Additional Keyboard Functionalities
		
		
		
		
		
//		typeField.getItems().add("Delete");
//		typeField.getItems().add("Backspace");
//		typeField.getItems().add("Shift");
//		typeField.getItems().add("Alt");
//		typeField.getItems().add("Ctrl");

		//Submit Button
		Button submit = new Button("Submit Instruction");
		submit.setOnMouseClicked(event -> s.hide());
		submit.setTranslateY(20);
		
		//Set Scene
		vb.getChildren().addAll(t, typeField, submit);
		s.setScene(new Scene(vb, 520, 275));
		s.showAndWait();

		//Dropdown Text Set -> Open Up Other GUI & Return
		String type = typeField.getValue();
		if ( (type.equals("Select Click Type...")) )
			type = "Tab";
		
		//Return
		return ( "AdvKeyboard "
				+ numClicks + " "
				+ "N/A "
				+ type + " "
				+ "false");
	}
	
	/**
	 * Sub-GUI for advanced keyboards: keyboard combo selection.
	 * @param numClicks The number of times to do this action
	 * @return The String representation of the GUI fields filled out
	 */
	private String advKeyboard_keyCombo_combo(int numClicks)
	{
		//Set Stage and Boxes
		Stage s = new Stage();
		s.setTitle("Define Key Combo Input");
		VBox vb = new VBox();
		
		//Grid GUI for Keyboard Combo
		Text t = new Text("Type of click:");
		ComboBox<String> typeField = new ComboBox<>();
		String select = "Select Click Type...";
		typeField.getItems().add(select);
		typeField.setValue(select);
		
		//Dropdown Keyboard Combinations
		typeField.getItems().add("Windows + .");
		typeField.getItems().add("Windows + Shift + S");
		typeField.getItems().add("Alt + Tab");
		
		//Submit Button
		Button submit = new Button("Submit Instruction");
		submit.setOnMouseClicked(event -> s.hide());
		submit.setTranslateY(20);
		
		//Set Scene
		vb.getChildren().addAll(t, typeField, submit);
		s.setScene(new Scene(vb, 520, 275));
		s.showAndWait();

		//Dropdown Text Set -> Open Up Other GUI & Return
		String type = typeField.getValue();
		if ( (type.equals("Select Click Type...")) )
			type = "Windows + Shift + S";
		
		//Return
		return ( "AdvKeyboard "
				+ numClicks + " "
				+ "N/A "
				+ type + " "
				+ "false");
	}
	
	/**
	 * Sub-GUI for advanced keyboards: function keys selection.
	 * @param numClicks The number of times to do this action
	 * @return The String representation of the GUI fields filled out
	 */
	private String advKeyboard_keyCombo_func(int numClicks)
	{
		//Set Stage and Boxes
		Stage s = new Stage();
		s.setTitle("Define Key Combo Input");
		VBox vb = new VBox();
		
		//Grid GUI for Keyboard Combo
		Text t = new Text("Type of click:");
		ComboBox<String> typeField = new ComboBox<>();
		String select = "Select Click Type...";
		typeField.getItems().add(select);
		typeField.setValue(select);
		
		//Dropdown Keyboard Combinations
		typeField.getItems().add("F1");
		typeField.getItems().add("F2");
		typeField.getItems().add("F3");
		typeField.getItems().add("F4");
		typeField.getItems().add("F5");
		typeField.getItems().add("F6");
		typeField.getItems().add("F7");
		typeField.getItems().add("F8");
		typeField.getItems().add("F9");
		typeField.getItems().add("F10");
		typeField.getItems().add("F11");
		typeField.getItems().add("F12");
		
		//Submit Button
		Button submit = new Button("Submit Instruction");
		submit.setOnMouseClicked(event -> s.hide());
		submit.setTranslateY(20);
		
		//Set Scene
		vb.getChildren().addAll(t, typeField, submit);
		s.setScene(new Scene(vb, 520, 275));
		s.showAndWait();

		//Dropdown Text Set -> Open Up Other GUI & Return
		String type = typeField.getValue();
		if ( (type.equals("Select Click Type...")) )
			type = "F12";
		
		//Return
		return ( "AdvKeyboard "
				+ numClicks + " "
				+ "N/A "
				+ type + " "
				+ "false");
	}
	
	/**
	 * Sub-GUI for advanced keyboards: keyboard combination type selection.
	 * @param numClicks The number of times to do this action
	 * @return The String representation of the GUI fields filled out
	 */
	private String advKeyboard_keyCombo(int numClicks)
	{
		//Set Stage and Boxes
		Stage s = new Stage();
		s.setTitle("Define Advanced Keyboard Type");
		VBox vb = new VBox();
		
		//What Type of Key Combo to Do
		HBox hb = new HBox();
		Text t = new Text("Type of click:");
		ChoiceBox<String> typeField = new ChoiceBox<>();
		String select = "Select Click Type...";
		typeField.getItems().add(select);
		typeField.getItems().add("Common Special Keys (e.g. Tab)");
		typeField.getItems().add("Keyboard Combinations (e.g. Windows + .)");
		typeField.getItems().add("Function Keys (e.g. F12)");
		typeField.setValue(select);
		if (typeField.getValue() != null)	//Remove Original "Selection" Button
			typeField.getItems().remove(0);
		hb.getChildren().addAll(t, typeField);	//Add to HBox
		
		//Submit Button
		Button cont = new Button("Continue");
		cont.setOnMouseClicked(event -> s.hide());
		cont.setTranslateY(20);
		
		//Set Scene
		vb.getChildren().addAll(hb, cont);
		s.setScene(new Scene(vb, 520, 275));
		s.showAndWait();

		//Dropdown Text Set -> Open Up Other GUI & Return
		String type = typeField.getValue();
		if ( (type.equals("Select Click Type...")) )
			type = "Common Special Keys (e.g. Tab)";
		if (type.equals("Common Special Keys (e.g. Tab)"))
			return advKeyboard_keyCombo_common(numClicks);
		if (type.equals("Keyboard Combinations (e.g. Windows + .)"))
			return advKeyboard_keyCombo_combo(numClicks);
		else	//Function Keys (e.g. F12)
			return advKeyboard_keyCombo_func(numClicks);
	}
	
	/**
	 * GUI for advanced keyboards; this further splits into some other GUIs. (See above code)
	 * @return The String representation of the GUI fields filled out
	 */
	public String dropdown_advKeyboard()
	{
		//Set Stage and Boxes
		Stage s = new Stage();
		s.setTitle("Define Advanced Keyboard Input");
		VBox vb = new VBox();
		
		//Number of Clicks
		HBox hb1 = new HBox();
		Text t1 = new Text("Enter how many times you would like to repeat this instruction:");
		TextField numClicksField = new TextField("1");
		hb1.getChildren().addAll(t1, numClicksField);
		
		//Whether to Do Unicode Key(s) Or Keyboard Combo
		HBox hb2 = new HBox();
		Text t2 = new Text("Type of click:");
		ChoiceBox<String> typeField = new ChoiceBox<>();
		String select = "Select Click Type...";
		typeField.getItems().add(select);
		typeField.getItems().add("Unicode Character(s)");
		typeField.getItems().add("Keyboard Combination / Functional Key(s) (e.g. F12)");
		typeField.setValue(select);
		if (typeField.getValue() != null)	//Remove Original "Selection" Button
			typeField.getItems().remove(0);
		hb2.getChildren().addAll(t2, typeField);	//Add to HBox
		
		//Submit Button
		Button cont = new Button("Continue");
		cont.setOnMouseClicked(event -> s.hide());
		cont.setTranslateY(20);
		
		//Set Scene
		vb.getChildren().addAll(hb1, hb2, cont);
		s.setScene(new Scene(vb, 520, 275));
		s.showAndWait();

		//Dropdown Text Set -> Open Up Other GUI & Return
		String type = typeField.getValue();
		if ( (type.equals("Select Click Type...")) )
			type = "Unicode Character(s)";
		if (type.equals("Unicode Character(s)"))
			return advKeyboard_unicode( Integer.parseInt(numClicksField.getText()) );
		else	//Keyboard Combo(s)
			return advKeyboard_keyCombo( Integer.parseInt(numClicksField.getText()) );
	}

	/**
	 * GUI for normal keyboard keys.
	 * @return The String representation of the GUI fields filled out
	 */
	public String dropdown_Keyboard()
	{
		Stage s = new Stage();
		s.setTitle("Define Keyboard Input");
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

	/**
	 * GUI for mouse clicks and/or scrolls.
	 * @return The String representation of the GUI fields filled out
	 */
	public String dropdown_Mouse()
	{
		Stage s = new Stage();
		s.setTitle("Define Mouse Click");
		VBox vb = new VBox();
		
		HBox hb1 = new HBox(new Text("Use click?"));
		CheckBox cb1 = new CheckBox();
		hb1.getChildren().add(cb1);
		hb1.setPadding(new Insets(0, 0, 8, 0));
		
		HBox hb2 = new HBox(new Text("Name for this action:"));
		TextField nameField = new TextField("Name Of Click");
		nameField.setText( nameField.getText().replaceAll("\\s+", "") );	//Delete Spaces from Name Field
		hb2.getChildren().add(nameField);
	
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
		hb5.setPadding(new Insets(0,0,8,0));
		
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
		s.setScene(new Scene(vb, 420, 275));
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

	/**
	 * GUI for generic wait.
	 * @return The String representation of the GUI field filled out
	 */
	public String dropdown_Wait()
	{
		Stage s = new Stage();
		s.setTitle("Define Wait Period");
		VBox vb = new VBox();
		HBox hb = new HBox(new Text("Wait duration (milliseconds):"));
		TextField waitField = new TextField("0");
		hb.getChildren().addAll(waitField);
		Button submit = new Button("Submit Instruction");
		submit.setOnMouseClicked(event -> s.hide());
		vb.getChildren().addAll(hb, submit);
		s.setScene(new Scene(vb, 420, 150));
		s.showAndWait();
		return "Wait " + waitField.getText();
	}

	/**
	 * GUI for image comparison wait.
	 * @param imageNumber Which image number to correspond to the image wait command (Needed for calibration)
	 * @return The String representation of the GUI fields filled out
	 */
	public String dropdown_ImageCompare(int imageNumber)
	{
		//Set Stage and Boxes
		Stage s = new Stage();
		s.setTitle("Define Image Comparison");
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
