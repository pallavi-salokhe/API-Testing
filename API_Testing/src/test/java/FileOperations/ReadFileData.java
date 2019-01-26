package FileOperations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


public class ReadFileData {

public static BufferedReader br = null;
	
	public static BufferedReader getFileHandle(String filename) throws FileNotFoundException
	{
		 br =  new BufferedReader(new FileReader(filename));
		return br;
	}
	
	public static String[] getHeaders(BufferedReader br) throws IOException
	{
		String text = br.readLine();
		System.out.println(text);
		String[] strArray = text.split("\\|");
		System.out.println(strArray);
		return strArray;
		
	}
	
	
}
