package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;


public class FileIO 
{
	/**
	 * converts the supplied file into a single string
	 * @param filename
	 * @return
	 */
	public static String loadFile(File filename)	//method to load any file
	{
		if (filename.exists())
		{
			StringBuilder fileAsString = new StringBuilder("");
			String currentLine="";
			try
			{
				FileReader in = new FileReader(filename);	//new filereader
				BufferedReader reader = new BufferedReader(in);	//buffered reader
				
				while((currentLine=reader.readLine())!=null)	//while not at end of file
				{
					fileAsString.append(currentLine +"\n"); 	//add current line to string gathered so far
				}
				in.close();
				reader.close();
			}
			catch(Exception ex)	//error loading file
			{
				JOptionPane.showMessageDialog(null, "Problem loading from file system : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
			
			
		return fileAsString.toString().substring(0, fileAsString.length()-1);	//return loaded file
		}
		
		
		else
		{
			return "";
		}
	}
	
	public static void saveFile(File f, String text)
	{
		if (f!=null)
		{
			try
			{
				PrintWriter out = new PrintWriter(new FileOutputStream(f),true);	//open file
				out.print(text);	//write to file
				out.close();	//close file
			}
			catch(IOException ex)
			{
				JOptionPane.showMessageDialog(null, "Problem saving to file system : " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
	}
	
	public static void makeSaveDir()
	{
		File f = new File("./autotyper");
		if(!f.exists())
		{
			f.mkdir();
		}
		System.out.println(f.exists());
	}
	
}
