package FileHandling;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import External.MainDirectory;

public class ImageCompareFiles {
	private static MainDirectory md = new MainDirectory();
	
	public BufferedImage loadImage(String fileName) throws IOException
	{
		return ImageIO.read(new File(fileName));//new BufferedImage(new File())
		/*
		 * Gets the image stored in the given fileName location, and stores it into a BufferedImage, then returns that
		 * It's fine to use FileHandling methods to access the files
		 */
		//return null;
	}
	
	public void saveImage(BufferedImage image, String fileName) throws IOException
	{
		ImageIO.write(image, "png", new File(fileName));
		/*
		 * Converts the BufferedImage image into a .png image, and then saves it to the given fileName
		 * It's fine to use FileHandling methods to access the files
		 */
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
		// TODO Auto-generated method stub
		
	}

}
