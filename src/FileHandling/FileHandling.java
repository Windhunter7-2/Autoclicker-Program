package FileHandling;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import External.MainDirectory;
//import java.io.FileNotFoundException;
public class FileHandling {
	private static MainDirectory md = new MainDirectory();
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
		/*
		 * Must be O(n), where n is the number of characters in the textfile
		 * Reads the file fileNameWithExt, then return it as a String
		 */
	}
	
	public void setText(String fileNameWithExt, String contents)throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter(md.getMainDirectory() + fileNameWithExt));	
		bw.write(contents);
		bw.close();
		/*
		 * Must be O(n), where n is the number of characters in the contents
		 * Writes to the file fileNameWithExt, with the contents being the given String, contents
		 */
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
