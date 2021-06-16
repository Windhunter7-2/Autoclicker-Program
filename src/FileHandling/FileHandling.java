package FileHandling;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import External.MainDirectory;

public class FileHandling {
	
	/**
	 * This is the main directory of the programs.
	 */
	private static MainDirectory md = new MainDirectory();
	
	/**
	 * This reads the given file, fileNameWithExt, and returns it as a String.
	 * @param fileNameWithExt The file to read from
	 * @return The text in the file
	 * @throws IOException
	 */
	public String getText(String fileNameWithExt) throws IOException
	{
		String retString = new String("");
		BufferedReader br = new BufferedReader(new FileReader(md.getMainDirectory() + fileNameWithExt));
		int next = br.read();
		while (next != -1) {
			retString = retString + (char)next;
			next = br.read();
		}
		br.close();
		return retString;
	}
	
	/**
	 * This writes to the file fileNameWithExt, with the contents being written as the given String, contents.
	 * @param fileNameWithExt The file being written to (Overwrites, rather than concatenates)
	 * @param contents The contents to put into the file
	 * @throws IOException
	 */
	public void setText(String fileNameWithExt, String contents)throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter(md.getMainDirectory() + fileNameWithExt));	
		bw.write(contents);
		bw.close();
	}
	
	/**
	 * Main method. Strictly for testing.
	 * @param args Command line arguments
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		FileHandling fh = new FileHandling();
		String ogText = fh.getText("Amogus.txt");
		System.out.println(ogText);
		fh.setText("Amogus.txt", "GETOUTOFMYHEADGETOUTOFMYHEADGETOUTOFMYHEADGETOUTOFMYHEADGETOUTOFMYHEAD");
		System.out.println(fh.getText("Amogus.txt"));
		fh.setText("Amogus.txt", ogText);
	}
}
