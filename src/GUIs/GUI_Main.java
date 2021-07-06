package GUIs;

import java.awt.AWTException;
import java.awt.Label;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Orientation;

public class GUI_Main {
	
	/**
	 * This is the VBox used in the autoclicker creator GUI. It is composed of all the HBoxes with the dropdowns and buttons.
	 */
	private VBox clicks_gui;
	
	/**
	 * This is the counter used in the autoclicker creator GUI. Note that the numbering is maintained even with removal, so the
	 * user knows clearly which clicks they have removed.
	 */
	private int clicks_counter = 0;
	
	/**
	 * This is a mapping ArrayList to map the counter indices to real indices.
	 */
	private ArrayList<Integer> clicks_i;
	
	/**
	 * When creating an autoclicker, this is the list of active individual autoclick instructions. For example, "Wait 100"
	 * might be an example of one of these Strings in the list.
	 */
	private ArrayList<String> clicks;
	
	/**
	 * This is which autoclicker is currently active.
	 */
	private String curAutoclicker = "";
	
	public void gui_initial()
	{
		//TODO -> Implement -> Cole
		gui_general("Initial GUI", false);
		/*
		 * Call gui_general("Initial GUI", false), and have this Pane be centered on the screen, in decently larger dimensions
		 */
	}
	
	public void gui_main(String autoclickName)
	{
		//TODO -> Implement -> Cole
		gui_general(autoclickName, true);
		/*
		 * Call gui_general(autoclickName, true), and have this Pane be off to the right in a smaller size, like in the drawn
		 * GUI specs
		 */
	}
	
	/*
	 * Creates a Pane of 4 HBoxes/VBoxes, in the order, from top to bottom, of:
	 * (1) gui_message(autoclickName)
	 * (2) gui_mainButtons()
	 * (3) If and only if runButton is true, gui_autoclickButton()
	 * (4) gui_exit()
	 * (These should be top to bottom like a VBox; if it doesn't go that way normally, force it via a nested VBox)
	 */
	public VBox gui_general(String autoclickName, boolean runButton)
	{
		//TODO -> Make It Look Nicer -> Evan
		VBox v = new VBox();
		//Adding a null argument might be dodgy
		v.getChildren().addAll(gui_message(autoclickName), gui_mainButtons());
		if (runButton == true)
			v.getChildren().add(gui_autoclickButton());
		v.getChildren().add(gui_exit()); 
		return v;
	}
	
	/*
	 * Must be O(1)
	 * This just returns an HBox with a very small button on the lower-right corner of the HBox, which says "Exit";
	 * when clicked on, should call System.exit(0). (See drawn GUI for more position specifics)
	 */
	public HBox gui_exit()
	{
		HBox h = new HBox();
		Button b = new Button("Exit");
		b.setOnMouseClicked(event -> {System.exit(0);});
		h.getChildren().add(b);
		return h;
	}

	/*
	 * Must be O(n), where n is the number of characters in autoclickName
	 * Return an HBox that contains a message (Or series of messages), based on the input of the autoclicker's name;
	 * 		If autoclickName equals "Initial GUI", this message should be one welcoming the user to the program
	 * 		Otherwise, it should be a reminder to the user of *which* autoclicker, indicated by autoclickName, is
	 * 		currently loaded into the program
	 */
	public HBox gui_message(String autoclickName)
	{
		//TODO -> Say More in Current Autoclicker Message -> Cole
		HBox h = new HBox();
		Text t = new Text();
		
		if (autoclickName.equals("Initial GUI")) {
			t.setText("Welcome to the Autoclicker Program!");
		} else {
			t.setText("Current Autoclicker: " + autoclickName);
		}
		h.getChildren().add(t);
		return h;
	}
	
	public VBox gui_mainButtons()
	{
		//TODO -> JOptionPane for Inputting Filename (And Rest of Load Autoclicker Functionality) -> Cole
		/*
		 * Must be O(1)
		 * This should be the two buttons, "Create Autoclicker" and "Load Autoclicker", as drawn in the GUI specs.
		 * If "Load Autoclicker" is clicked, the user first gets a prompt in the "Autoclickers" folder to select which
		 * autoclicker they would like to load (And reminds them that they should be in that folder, since calibration
		 * requires it), and then after they select the file, set curAutoclicker equal to the name of that file, but
		 * *without* any extensions or folder location data, and then calls gui_main(curAutoclicker). If "Create Autoclicker"
		 * is clicked, just call gui_createAutoclicker().
		 */
		VBox v = new VBox();
		Button b1 = new Button("CreateAutoclicker");
		b1.setOnMouseClicked(event -> {gui_createAutoclicker();}); 
		Button b2 = new Button("Load Autoclicker");
		b2.setOnMouseClicked(event -> {
			JFrame jf = new JFrame();
			curAutoclicker = JOptionPane.showInputDialog(jf, "Enter Autoclicker Name");
		}); 
		v.getChildren().addAll(b1,b2);
		return v;
	}
	
	public VBox gui_autoclickButton()
	{
		//TODO -> Horizontal Separator -> Evan
		/*
		 * Must be O(1)
		 * This should have two things: A horizontal separator, and a button for running the current autoclicker. (See
		 * the drawn GUI specs for the exact wording, etc. requirements for the button) When the button is clicked, it
		 * should call GUI_General.runAutoclick(), with the String being passed being curAutoclicker.
		 */
		VBox v = new VBox();
		Separator s = new Separator(Orientation.HORIZONTAL);
		Button b = new Button("Run Autoclicker"); //TODO Find GUI specs
		b.setOnMouseClicked(event ->{
			try {
				new GUI_General().runAutoclick(curAutoclicker);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AWTException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		v.getChildren().add(s);
		v.getChildren().add(b);
		return v;
	}
	
	public void gui_createAutoclicker()
	{
		//TODO -> Submission / Exit Buttons -> Evan
		//Initialization
		clicks_gui = new VBox();
		clicks = new ArrayList<String>();
		
		//Create the Panel Itself
		Pane list = new Pane();
		list.getChildren().add(clicks_gui);
		Stage stage = new Stage();
		Scene scene = new Scene(list, 400, 300);
		stage.setScene(scene);
		stage.show();
		
		//Add the First Initial "Add" HBox
		HBox click = gui_click(clicks_counter);
		clicks_gui.getChildren().add(click);
		clicks_i = new ArrayList<Integer>();
		
		//Add Main Buttons
		//TODO -> gui_exit() HBox at the Bottom of the OVERALL VBox, and ADD a Button to the LEFT That Says to "Submit":
		//		This Button Will Prompt the User for a Filename, Then Call
		//		GUI_General.createAutoclick(MainDirectory.getMainDirectory() + "/Autoclicker-Program/Autoclickers/F.txt",
		//				clicks), where F is the filename the user types in
		//		and Then Call gui_main(F)
		
		/*
		 * This is the main GUI for creating autoclickers.
		 */
	}
	
	private void gui_click_addButton(ChoiceBox<String> choiceBox, Button addBttn, Button remBttn, int clickNum)
	{
		//Error Check: If Still on "Select", Return (AKA Do Nothing from the Button)
		String choice = choiceBox.getValue();
		if ( choice.equals("Select Click Type...") )
			return;
		remBttn.setDisable(false);
		remBttn.setOpacity(1);
		
		//Call Appropriate Dropdown GUI
		GUI_Dropdowns sub_gui = new GUI_Dropdowns();
		String autoclickInstr = "";
		if ( choice.equals("Mouse") )
			autoclickInstr = sub_gui.dropdown_Mouse();
		if ( choice.equals("Keyboard") )
			autoclickInstr = sub_gui.dropdown_Keyboard();
		if ( choice.equals("Advanced Keyboard") )
			autoclickInstr = sub_gui.dropdown_advKeyboard();
		if ( choice.equals("Wait (General)") )
			autoclickInstr = sub_gui.dropdown_Wait();
		if ( choice.equals("Wait (Compare Images)") )
			autoclickInstr = sub_gui.dropdown_ImageCompare(clickNum);
		
		//Add Autoclicker Instruction
		clicks_i.add(clicks_counter);
		if (clicks_counter != 0)
		{
			int index = (clicks_i.get(clicks_counter-1) + 1);	//clicks_i[Previous] + 1
			clicks_i.set(clicks_counter, index);
		}
		clicks.add(autoclickInstr);
		clicks_counter++;
		HBox newHBox = gui_click(clicks_counter);
		clicks_gui.getChildren().add(newHBox);
		
		//Gray Out Dropdown & Add Buttons
		choiceBox.setDisable(true);
		choiceBox.setOpacity(2.0);
		addBttn.setDisable(true);
		addBttn.setOpacity(0.5);
		return;
	}
	
	private void gui_click_remButton(ChoiceBox<String> choiceBox, int clickNum)
	{
		//Error Check: If Still on "Select", Return (AKA Do Nothing from the Button)
		String choice = choiceBox.getValue();
		if ( choice.equals("Select Click Type...") )
			return;
		
		//"Remove" the HBox (And Shift the Others Because of the Removal)
		int curIndex = clicks_i.get(clickNum);
		clicks.remove(curIndex);
		clicks_gui.getChildren().remove(curIndex);
		for (int i = clickNum; i < clicks_i.size(); i++)
		{
			int c = (clicks_i.get(i) - 1);
			clicks_i.set(i, c);
		}
		return;
	}
	
	public HBox gui_click(int clickNum)
	{
		//Main Part: Number Plus Main HBox
		HBox clickType = new HBox();
		String largeFont = "-fx-font: 24 arial;";
		Text blank1 = new Text("      ");
		Text blank2 = new Text("      ");
		Text blank3 = new Text("      ");
		Text click = new Text("Click " + (clickNum + 1) + ":");
		click.setStyle(largeFont);
		clickType.getChildren().add(click);
		clickType.getChildren().add(blank1);
		
		//The Dropdown Buttons
		ChoiceBox<String> dropdown = new ChoiceBox<>();
				
		//Adding the Dropdown Buttons
		String select = "Select Click Type...";
		dropdown.getItems().add(select);
		dropdown.getItems().add("Mouse");
		dropdown.getItems().add("Keyboard");
		dropdown.getItems().add("Advanced Keyboard");
		dropdown.getItems().add("Wait (General)");
		dropdown.getItems().add("Wait (Compare Images)");
		dropdown.setValue(select);
		clickType.getChildren().add(dropdown);
		
		//Remove Original "Selection" Button
		if (dropdown.getValue() != null)
			dropdown.getItems().remove(0);
		
		//Add Button
		clickType.getChildren().add(blank2);
		Button add = new Button("Add");
		Button remove = new Button("Remove");
		add.setOnAction( e -> gui_click_addButton(dropdown, add, remove, clickNum) );
		clickType.getChildren().add(add);
		
		//Remove Button
		clickType.getChildren().add(blank3);
		remove.setDisable(true);
		remove.setOpacity(0.5);
		remove.setOnAction( e -> gui_click_remButton(dropdown, clickNum) );
		clickType.getChildren().add(remove);
		return clickType;
		
		/*
		 * This is an individual row of the text, and buttons, for each click, as per the GUI drawn specs, etc.
		 */
	}

	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) throws IOException, AWTException {
		
	}
}
