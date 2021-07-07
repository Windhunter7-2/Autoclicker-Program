package External;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.transform.Scale;

public class ImageEditing {
	
	/**
	 * Resizes the given Node (e.g. HBox, VBox, Button, etc.) to the given new width and height. These width and height are not
	 * by pixel, but by relative scale; for example, width of 2.0 would make it twice as wide as previously.
	 * @param node The Node to resize
	 * @param width Relative new width
	 * @param height Relative new height
	 * @return the resized Node
	 */
	public Node resize(Node node, double width, double height)
	{
		Scale scale = new Scale();
		scale.setX(width);
		scale.setY(height);
		node.getTransforms().add(scale);
		return node;
	}

	/**
	 * Using the given color (All lower-case letters, to match CSS styles) as the background color for the image, get the
	 * image from the given location on the user's computer, and then return as an HBox.
	 * @param imgLocation Where on the computer the image file is located
	 * @param color The color (All lower-case letters, to match CSS styles) of the background ("transparent" for transparent)
	 * @return the HBox with the image in it
	 */
	public HBox imageToHBox(String imgLocation, String color)
	{
		//Get Image
		FileInputStream input = null;
		try {
			input = new FileInputStream(imgLocation);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Image image = new Image(input);
		ImageView img = new ImageView(image);
		
		//Set As an HBox and Set the Background Color
		HBox hbox = new HBox();
		String style = ("-fx-background-color:" + color + ";");
		hbox.setStyle(style);
		hbox.getChildren().add(img);
		return hbox;
	}
	
	/**
	 * Using the given color (All lower-case letters, to match CSS styles) as the background color for the image, get the
	 * image from the given location on the user's computer, and then, along with the given text, return as a Button.
	 * If noTransparency is true, do not change the background color, and leave as the default.
	 * @param imgLocation Where on the computer the image file is located
	 * @param color The color (All lower-case letters, to match CSS styles) of the background ("transparent" for transparent)
	 * @param buttonText The text to display on the button
	 * @param noTransparency If true, treat like a normal button (General button background color), instead of the given color
	 * @return the Button with the image and text on it
	 */
	public Button imageToButton(String imgLocation, String color, String buttonText, boolean noTransparency)
	{
		//Get Image
		FileInputStream input = null;
		try {
			input = new FileInputStream(imgLocation);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Image image = new Image(input);
		ImageView img = new ImageView(image);
		
		//Set As a Button and Set the Background Color
		Button button = new Button(buttonText);
		String style = ("-fx-background-color:" + color + ";");
		if (noTransparency == false)
			button.setStyle(style);
		button.setGraphic(img);
		return button;
	}
	
	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
