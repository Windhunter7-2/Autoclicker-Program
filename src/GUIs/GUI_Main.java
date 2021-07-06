package GUIs;

import java.awt.AWTException;
import java.awt.Label;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import External.MainDirectory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Separator;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
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
	 * The VBox used for the main menu.
	 */
	private VBox mainMenu = new VBox();

	/**
	 * The stage used for the main menu.
	 */
	private Stage mainStage = new Stage();
	
	/**
	 * This is which autoclicker is currently active.
	 */
	
	private String curAutoclicker = "";
	
	public void gui_initial()
	{
		//TODO -> Implement -> Cole
		
		mainMenu = gui_general("Initial GUI", false);
		mainStage.setScene(new Scene(mainMenu, 600, 800));
		mainStage.show();
		/*
		 * Call gui_general("Initial GUI", false), and have this Pane be centered on the screen, in decently larger dimensions
		 */
	}
	
	public void gui_main(String autoclickName)
	{
		//TODO -> Implement -> Cole
		
		mainMenu = gui_general(autoclickName, true);
		mainStage.setScene(new Scene(mainMenu, 600, 800));
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
		//For Better Spacing
		Text blank1 = new Text("\t\t\t");
		Text blank2 = new Text("\t\t\t");
		Text blank3 = new Text("\t\t\t\t\t\t\t\t\t");
		Text blank4 = new Text("\t\n\n\t");
		Text blank5 = new Text("\t\t\t");
		Text blank6 = new Text("\t\n\n\n\t");
		
		//Apply Better Spacing
		VBox v = new VBox();
		HBox h1 = new HBox();
		HBox h2 = new HBox();
		HBox h3 = new HBox();
		h1.getChildren().addAll( blank1, gui_mainButtons() );
		h2.getChildren().addAll( blank2, gui_autoclickButton() );
		h3.getChildren().addAll( blank3, gui_exit() );
		
		//Adding Stuff
		v.getChildren().addAll(gui_message(autoclickName), blank4, h1);
		if (runButton == true)
			v.getChildren().addAll(blank5, h2);
		v.getChildren().addAll(blank6, h3); 
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
		Text blank = new Text("\t\t\t");
		Button b1 = new Button("CreateAutoclicker");
		b1.setOnMouseClicked(event -> {gui_createAutoclicker();}); 
		Button b2 = new Button("Load Autoclicker");
		b2.setOnMouseClicked(event -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("Select Which Autoclicker To Load");
			fc.setInitialDirectory(new File(new MainDirectory().getMainDirectory() + "Autoclicker-Program/Autoclickers"));
			File f = fc.showOpenDialog(null);
			if (f != null) {
				System.out.println(f.getName());
				curAutoclicker = f.getName().split("\\.")[0];
				gui_main(curAutoclicker);
			}
		}); 
		v.getChildren().addAll(b1,blank,b2);
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
	
	private void gui_create_submit(Stage createStage)
	{
		//Error Check: Ignore Button if No Instructions Yet
		System.out.println("Currently:\n\t" + clicks.size() );
		if (clicks.size() < 1)
			return;
		
		//Prompt & Storing Which Autoclicker Name
		String filename = JOptionPane.showInputDialog(null, "Please input the name for this autoclicker you just created:",
				"Name of Generated Autoclicker", JOptionPane.QUESTION_MESSAGE);
		curAutoclicker = filename;
		
		//Creating the File
		filename = (filename + ".txt");
		GUI_General createFile = new GUI_General();
		try {
			createFile.createAutoclick(filename, clicks);
		} catch (IOException e) {
			e.printStackTrace();
		}
		createStage.hide();
		gui_main(curAutoclicker);
		return;
	}
	
	public void gui_createAutoclicker()
	{
		//Initialization
		clicks_gui = new VBox();
		clicks = new ArrayList<String>();
		clicks_counter = 0;
		
		//Create the Panel Itself
		BorderPane list = new BorderPane();
		Text border1 = new Text("");
		Text border2 = new Text("");
        list.setPadding(new Insets(16));
        list.setLeft(border1);
        list.setRight(border2);
		list.setTop(clicks_gui);
		Stage stage = new Stage();
		
		//Add the First Initial "Add" HBox
		HBox click = gui_click(clicks_counter);
		clicks_gui.getChildren().add(click);
		clicks_i = new ArrayList<Integer>();
		
		//Add Main Buttons
		HBox mainButtons = gui_exit();
		Text blank1 = new Text("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
		Text blank2 = new Text("\t");
		Button submit = new Button("Submit (Create Autoclicker)");
		submit.setOnAction( e -> gui_create_submit(stage) );
		mainButtons.getChildren().add(0, blank2);
		mainButtons.getChildren().add(0, submit);
		mainButtons.getChildren().add(0, blank1);
		list.setBottom(mainButtons);
		Scene scene = new Scene(list, 700, 500);
		stage.setScene(scene);
		stage.show();
		
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
