package FileHandling;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import External.MainDirectory;

public class ImageCompareFiles {
	
	/**
	 * The main directory of the programs
	 */
	private static MainDirectory md = new MainDirectory();
	
	/**
	 * Gets the image stored in the given fileName location, and returns it as a BufferedImage.
	 * @param fileName The file name to load the image from
	 * @return The image loaded from the file
	 * @throws IOException
	 */
	public BufferedImage loadImage(String fileName) throws IOException
	{
		return ImageIO.read(new File(fileName));//new BufferedImage(new File())
	}
	
	/**
	 * Converts the BufferedImage image into a .png image, saving it to the given fileName.
	 * @param image The BufferedImage to save as an image
	 * @param fileName The file name to save the image as
	 * @throws IOException
	 */
	public void saveImage(BufferedImage image, String fileName) throws IOException
	{
		ImageIO.write(image, "png", new File(fileName));
	}
	
	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		ImageCompareFiles icf = new ImageCompareFiles();
		try {
			BufferedImage bi = icf.loadImage(md.getMainDirectory() + "Autoclicker-Program/Obema.jpg");
			icf.saveImage(bi, md.getMainDirectory() + "Autoclicker-Program/Obema.png");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
