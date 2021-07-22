package GUIs;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import External.ImageEditing;
import External.MainDirectory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Insets;

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
	
	/**
	 * Calls the initial (Startup) GUI.
	 */
	public void gui_initial()
	{
		mainMenu = gui_general("Initial GUI", false);
		mainStage.setTitle("Autoclicker Main Menu");
		mainStage.setScene(new Scene(mainMenu, 415, 195));
		mainStage.setMinWidth(430);
		mainStage.setMinHeight(230);
		mainStage.show();
	}
	
	/**
	 * Calls the main GUI, and has it off to the side. (AKA after an autoclicker is loaded or created)
	 * @param autoclickName The name of the autoclicker loaded into the program
	 */
	public void gui_main(String autoclickName)
	{
		mainMenu = gui_general(autoclickName, true);
		mainStage.setScene(new Scene(mainMenu, 420, 310));
		mainStage.setMinWidth(425);
		mainStage.setMinHeight(350);
		mainStage.setX(Screen.getPrimary().getVisualBounds().getMaxX()-420);	
	}
	
	/**
	 * Creates a Pane of 4 general areas, in the order, from top to bottom, of:
	 * (1) gui_message(autoclickName)
	 * (2) gui_mainButtons()
	 * (3) If and only if runButton is true, gui_autoclickButton()
	 * (4) gui_exit()
	 * @param autoclickName The name of the autoclicker loaded (Or, if applicable, the String indicating initual GUI)
	 * @param runButton Whether or not the button to run the loaded autoclicker should be loaded
	 * @return A VBox of these 4 sections
	 */
	public VBox gui_general(String autoclickName, boolean runButton)
	{
		VBox v = new VBox();
		HBox h1 = new HBox();
		HBox h2 = new HBox();
		HBox h3 = new HBox();
		HBox h4 = new HBox();
		h1.getChildren().addAll(gui_mainButtons());
		h1.setTranslateX(100);
		h1.setTranslateY(20);
		h2.getChildren().addAll(gui_autoclickButton());
		h2.setTranslateX(40);
		h2.setTranslateY(50);
		h3.getChildren().addAll(gui_exit());
		h3.setTranslateX(350);
		h3.setTranslateY(80);
		h4.getChildren().addAll(gui_message(autoclickName));
		h4.setTranslateX(10);
		
		//Adding Stuff
		v.getChildren().addAll(h4, h1);
		if (runButton == true) {
			v.getChildren().addAll(h2);
		}
		v.getChildren().addAll(h3); 
		return v;
		
	}
	
	/**
	 * Creates an HBox with an "Exit" button
	 * @return The HBox with the "Exit" button
	 */
	public HBox gui_exit()
	{
		HBox h = new HBox();
		Button b = new Button("Exit");
		b.setOnMouseClicked(event -> {System.exit(0);});
		h.getChildren().add(b);
		return h;
	}

	/**
	 * Return an HBox that contains a message (Or series of messages), based on the input of the autoclicker's name.
	 * If autoclickName equals "Initial GUI", this message should be one welcoming the user to the program; otherwise,
	 * it should be a reminder to the user of *which* autoclicker, indicated by autoclickName, is currently loaded
	 * into the program.
	 * @param autoclickName Which autoclicker is loaded (If applicable), or alternatively, the String representatin the initial GUI
	 * @return An HBox of the appropriate text message
	 */
	public HBox gui_message(String autoclickName)
	{
		HBox h = new HBox();
		Text t = new Text();
		t.setTextAlignment(TextAlignment.CENTER);	
		if (autoclickName.equals("Initial GUI")) {
			t.setText("Welcome to the Autoclicker Program!");
		} else {
			t.setText("The autoclicker currently loaded is: \n" + autoclickName);
		}
		t.setStyle("-fx-font: 24 arial");
		h.getChildren().add(t);
		return h;
	}
	
	/**
	 * The two main buttons of the program, which are to load an autoclicker and to create an autoclicker.
	 * @return A VBox with these two buttons
	 */
	public VBox gui_mainButtons()
	{
		VBox v = new VBox();
		Button b1 = new Button("CreateAutoclicker");
		b1.setOnMouseClicked(event -> {gui_createAutoclicker();}); 
		Button b2 = new Button("Load Autoclicker");
		b2.setOnMouseClicked(event -> {
			FileChooser fc = new FileChooser();
			fc.setTitle("Select Which Autoclicker To Load");
			fc.setInitialDirectory(new File(new MainDirectory().getMainDirectory() + "Autoclicker-Program/Autoclickers"));
			File f = fc.showOpenDialog(null);
			if (f != null) {
				curAutoclicker = f.getName().split("\\.")[0];
				gui_main(curAutoclicker);
			}
		});
		ImageEditing resizer = new ImageEditing();
		b1 = (Button) resizer.resize(b1, 1.5, 1.5);
		b1.setTranslateY(0);
		b2 = (Button) resizer.resize(b2, 1.5, 1.5);
		b2.setTranslateY(20);
		v.getChildren().addAll(b1,b2);
		return v;
	}
	
	/**
	 * The button to run the currently loaded autoclicker. Also has a horizontal separator for looking nicer.
	 * @return A VBox with the button that runs the autoclicker
	 */
	public VBox gui_autoclickButton()
	{
		//General Setup & Location Settings
		VBox vbox = new VBox();
		ImageEditing img = new ImageEditing();
		MainDirectory md = new MainDirectory();
		String location = (md.getMainDirectory() + "Autoclicker-Program/Settings/Graphics/");
		
		//Add Horizontal Separator
		String separatorLoc = (location + "Separator_Horizontal.png");
		HBox separator = img.imageToHBox(separatorLoc, "transparent");
		vbox.getChildren().addAll(separator);
		
		//Add Autoclick Button
		String buttonLoc = (location + "CustomButton_Autoclick.png");
		Button button = img.imageToButton(buttonLoc, "", "", true);
		HBox autoclick = new HBox();
		autoclick.getChildren().addAll( button);
		autoclick.setTranslateX(30);
		vbox.getChildren().add(autoclick);
		
		//Button Functionality
		button.setOnMouseClicked(event ->{
			try {
				new GUI_General().runAutoclick(curAutoclicker);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (AWTException e) {
				e.printStackTrace();
			}
		});
		return vbox;
	}
	
	/**
	 * The functionality of the "Submit" button in the "Create Autoclicker" GUI.
	 * @param createStage The stage that is the "Create Autoclicker" main GUI
	 */
	private void gui_create_submit(Stage createStage)
	{
		//Error Check: Ignore Button if No Instructions Yet
		if (clicks.size() < 1)
			return;
		
		//Prompt & Storing Which Autoclicker Name
		String filename = JOptionPane.showInputDialog(null, "Please input the name for this autoclicker you just created:",
				"Name of Generated Autoclicker", JOptionPane.QUESTION_MESSAGE);
		curAutoclicker = filename;
		
		//Replace Image Comparison Placeholder Name with Actual Filename
		for (int i = 0; i < clicks.size(); i++)
			clicks.set( i, clicks.get(i).replaceAll("autoclickerName", filename) );
		
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
	
	/**
	 * The main "Create Autoclicker" GUI.
	 */
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
		stage.setTitle("Create A New Autoclicker");
		
		//Add the First Initial "Add" HBox
		HBox click = gui_click(clicks_counter);
		clicks_gui.getChildren().add(click);
		clicks_i = new ArrayList<Integer>();
		
		//Add Main Buttons
		HBox mainButtons = gui_exit();
		Button submit = new Button("Submit (Create Autoclicker)");
		submit.setOnAction( e -> gui_create_submit(stage) );
		mainButtons.getChildren().add(0, submit);
		mainButtons.getChildren().get(1).setTranslateX(20);
		mainButtons.setTranslateX(450);
		list.setBottom(mainButtons);
		Scene scene = new Scene(list, 700, 500);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * This is the functionality for the "Add" button in the "Create Autoclicker" GUI.
	 * @param choiceBox The dropdown for which click type
	 * @param addBttn A reference to the button itself, for enabling/disabling purposes
	 * @param remBttn A reference to the "Remove" button, for enabling/disabling purposes
	 * @param clickNum The instruction's number (e.g. The third instruction added would have this as 3)
	 */
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
	
	/**
	 * This is the functionality for the "Remove" button in the "Create Autoclicker" GUI.
	 * @param choiceBox The dropdown for which click type
	 * @param clickNum The instruction's number (e.g. The third instruction added would have this as 3)
	 */
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
	
	/**
	 * This is each one of the horizontal rows of buttons for each individual click instruction. It uses a relatively primitive
	 * data structure to support adding and removing while maintaining numerical order.
	 * @param clickNum The instruction's number (e.g. The third instruction added would have this as 3)
	 * @return An HBox of that particular instruction's buttons (Note that these buttons will be unique per each instruction)
	 */
	public HBox gui_click(int clickNum)
	{
		//Main Part: Number Plus Main HBox
		HBox clickType = new HBox();
		String largeFont = "-fx-font: 24 arial;";
		Text click = new Text("Click " + (clickNum + 1) + ":");
		click.setStyle(largeFont);
		
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
		
		//Remove Original "Selection" Button
		if (dropdown.getValue() != null)
			dropdown.getItems().remove(0);
		
		//Add Button
		Button add = new Button("Add");
		Button remove = new Button("Remove");
		add.setOnAction( e -> gui_click_addButton(dropdown, add, remove, clickNum) );
		clickType.getChildren().add(add);
		
		//Remove Button
		remove.setDisable(true);
		remove.setOpacity(0.5);
		remove.setOnAction( e -> gui_click_remButton(dropdown, clickNum) );
		clickType.getChildren().add(remove);
		
		//Adding Stuff to Boxes
		HBox h_addRem = new HBox();
		remove.setTranslateX(20);
		h_addRem.getChildren().add(add);
		h_addRem.getChildren().add(remove);
		HBox h_drop = new HBox();
		h_addRem.setTranslateX(20);
		h_drop.getChildren().add(dropdown);
		h_drop.getChildren().add(h_addRem);
		HBox h_click = new HBox();
		h_drop.setTranslateX(20);
		h_click.getChildren().add(click);
		h_click.getChildren().add(h_drop);
		clickType.getChildren().add(h_click);
		return clickType;
	}

	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) throws IOException, AWTException {
		
	}
}
