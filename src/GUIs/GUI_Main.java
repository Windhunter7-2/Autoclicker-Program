package GUIs;

import java.util.ArrayList;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class GUI_Main {
	
	/**
	 * This says whether or not another row for an additional autoclick instruction should be added. For example, if
	 * there are Click 1 and Click 2, and Click 2's "Add" button is clicked, then after the next GUI where the user sets
	 * that autoclick instruction, this boolean will temporarily be turned to true again, and after the line is added,
	 * go back to false.
	 */
	public boolean addInstr = true;
	
	/**
	 * When creating an autoclicker, this is the list of active individual autoclick instructions. For example, "Wait 100"
	 * might be an example of one of these Strings in the list.
	 */
	private ArrayList<String> clicks;
	
	/**
	 * When creating an autoclicker, this is the list of the active individual autoclick instructions, as they appear
	 * on the GUI.
	 */
	private ArrayList<HBox> gui_clicks;
	
	/**
	 * When creating an autoclicker, this is the list of indices for the active individual autoclick instructions.
	 * Any integers in this ArrayList with the value of (-1) are regarded as "removed" for the actual clicks;
	 * otherwise, not the case.
	 */
	private ArrayList<Integer> clickIndices;
	
	/**
	 * This is which autoclicker is currently active.
	 */
	private String curAutoclicker = "";
	
	public void gui_initial()
	{
		/*
		 * Call gui_general("Initial GUI", false), and have this Pane be centered on the screen, in decently larger dimensions
		 */
		
		return;
	}
	
	public void gui_main(String autoclickName)
	{
		/*
		 * Call gui_general(autoclickName, true), and have this Pane be off to the right in a smaller size, like in the drawn
		 * GUI specs
		 */
		
		return;
	}
	
	public Pane gui_general(String autoclickName, boolean runButton)
	{
		/*
		 * Creates a Pane of 4 HBoxes/VBoxes, in the order, from top to bottom, of:
		 * (1) gui_message(autoclickName)
		 * (2) gui_mainButtons()
		 * (3) If and only if runButton is true, gui_autoclickButton()
		 * (4) gui_exit()
		 * (These should be top to bottom like a VBox; if it doesn't go that way normally, force it via a nested VBox)
		 */
		
		return null;
	}
	
	private HBox gui_exit()
	{
		/*
		 * Must be O(1)
		 * This just returns an HBox with a very small button on the lower-right corner of the HBox, which says "Exit";
		 * when clicked on, should call System.exit(0). (See drawn GUI for more position specifics)
		 */
		
		return null;
	}

	private HBox gui_message(String autoclickName)
	{
		/*
		 * Must be O(n), where n is the number of characters in autoclickName
		 * Return an HBox that contains a message (Or series of messages), based on the input of the autoclicker's name;
		 * 		If autoclickName equals "Initial GUI", this message should be one welcoming the user to the program
		 * 		Otherwise, it should be a reminder to the user of *which* autoclicker, indicated by autoclickName, is
		 * 		currently loaded into the program
		 */
		
		return null;
	}
	
	private VBox gui_mainButtons()
	{
		/*
		 * Must be O(1)
		 * This should be the two buttons, "Create Autoclicker" and "Load Autoclicker", as drawn in the GUI specs.
		 * If "Load Autoclicker" is clicked, the user first gets a prompt in the "Autoclickers" folder to select which
		 * autoclicker they would like to load (And reminds them that they should be in that folder, since calibration
		 * requires it), and then after they select the file, set curAutoclicker equal to the name of that file, but
		 * *without* any extensions or folder location data, and then calls gui_main(curAutoclicker). If "Create Autoclicker"
		 * is clicked, just call gui_createAutoclicker().
		 */
		
		return null;
	}
	
	private VBox gui_autoclickButton()
	{
		/*
		 * Must be O(1)
		 * This should have two things: A horizontal separator, and a button for running the current autoclicker. (See
		 * the drawn GUI specs for the exact wording, etc. requirements for the button) When the button is clicked, it
		 * should call GUI_General.runAutoclick(), with the String being passed being curAutoclicker.
		 */
		
		return null;
	}
	
	private void gui_createAutoclicker()
	{
		/*
		 * No Big-O requirement
		 * This is the main GUI for creating autoclickers. It should be a constant while loop (For the duration of the "Create
		 * Autoclicker" GUI), which, every time addInstr is true, call gui_click(), with the parameter being a counter, (i.e.
		 * start at 0, then 1, then 2, etc.) put that returned HBox into the GUI (As a VBox) so that it matches the drawn GUI specs,
		 * and then toggle addInstr to false, waiting until true again, then repeat. (Something like a thread with a wait
		 * instruction might work, but this will likely need some experimentation) In addition, put a gui_exit() HBox at the bottom
		 * of the VBox, and *edit* that particular HBox to add a button on the left that says to basically submit the current
		 * autoclicker instruction; clicking this button will prompt the user for a filename, and it will then call
		 * GUI_General.createAutoclick(MainDirectory.getMainDirectory() + "/Autoclicker-Program/Autoclickers/F.txt",
		 * clicks), where F is the filename the user types in, followed by calling gui_main(F).
		 * *Every* time the GUI gets drawn, and *before* adding the HBox, use all the HBoxes from gui_clicks as the HBoxes in the
		 * GUI, but be sure to skip over any "non-existent" ones, which you can tell if clickIndices has a (-1) or a 1 at the same
		 * index, since these two ArrayLists have the same mapping. (Ergo, if clickIndices[5] is (-1), skip over gui_clicks[5])
		 * Only after all these HBoxes are put should you add the HBox that this method adds via the gui_click() method call.
		 */
		
		return;
	}
	
	private HBox gui_click(int clickNum)
	{
		/*
		 * Must be O(1)
		 * This is an individual row of the text, and buttons, for each click, as per the GUI drawn specs;
		 * i.e. "Click X:", the dropdown, the "Add" button, and the "Remove" button. The "Click X:" should show
		 * (1 + clickNum) in place of X; for example, if clickNum is 0, then the text on the left would be
		 * "Click 1:", like in the drawn GUI example. The dropdown should have default text of "Select Click Type..."
		 * and then have the dropdown options be "Advanced Keyboard", "Keyboard", "Mouse", "Wait (General)", and
		 * "Wait (Compare Images)". After the "Add" button is clicked, then the appropriate GUI_Dropdowns.java method
		 * is called (See that class for the specific names), and then that String is then *inserted* into the
		 * ArrayList clicks, at the index clickNum. (For example, if clickNum is 0, insert at index 0) In addition,
		 * this "Add" button also calls clickIndices.add(clickNum, 1), as well as makes the dropdown menu "stuck"
		 * (Grayed out) so that the user can't reselect from there, and toggles addInstr to true. If the "Remove"
		 * button is clicked, then call clickIndices.set( clickNum, (-1) ).
		 */
		
		return null;
	}
	
	
	
	
	
	
	
	
	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {

		
		
		
		
		
		
		
	}

	
}
