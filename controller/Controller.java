package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import model.FileIO;
import model.TextConverter;
import model.TextPrinter;
import view.AutoTyperGUI;

public class Controller implements ActionListener 
{

	private AutoTyperGUI gui;
	private TextPrinter printer;
	private TextConverter converter;
	
	public Controller()
	{
		gui = new AutoTyperGUI();
		printer = new TextPrinter();
		converter = new TextConverter();
	}
	
	public static void main(String[] args) 
	{
		Controller c = new Controller();
		c.getGUI().registerListener(c);
		FileIO.makeSaveDir();

	}
	
	public AutoTyperGUI getGUI()
	{
		return gui;
	}

	public void actionPerformed(ActionEvent event) 
	{
		if(event.getActionCommand().equals("bGo"))
		{
			String strDelay = gui.getDelay();
			int delay;
			try
			{
				delay = Integer.parseInt(strDelay);
			}
			catch(Exception e)
			{
				delay = 0;
			}
			
			String strInitDelay = gui.getInitDelay();
			int initDelay;
			try
			{
				initDelay = Integer.parseInt(strInitDelay);
			}
			catch(Exception e)
			{
				initDelay = 5000; //default to 5 seconds
			}
			
			new PrinterThread(printer, gui.getText(), delay, initDelay).start();
		}
		else if(event.getActionCommand().equals("bClear"))
		{
			gui.setText("");
		}
		else if(event.getActionCommand().equals("miSave"))
		{
			String input = JOptionPane.showInputDialog(null,"Save As...","");	//ask user for name
			if (input != null) //cancel not pressed
			{
				FileIO.saveFile(new File("./autotyper/" + input), gui.getText());
			}

		}
		else if(event.getActionCommand().equals("miLoad"))
		{
			Object[] savedFiles = new File("./autotyper").listFiles();
			
			if(savedFiles.length>0)
			{
				String selection = JOptionPane.showInputDialog(gui,"Choose a save file","Choose File",JOptionPane.QUESTION_MESSAGE,null,savedFiles, savedFiles[0]).toString();
				
				int index = 0;
				for(Object obj: savedFiles)
				{
					String strFile = ((File)obj).toString();
					if (strFile.toString().equals(selection))
					{
						break;
					}
					index++;
				}

				gui.setText(FileIO.loadFile((File)savedFiles[index]));
			}
		}
		else if(event.getActionCommand().equals("miMsg"))
		{
			String input = JOptionPane.showInputDialog(null,"Type a message","");	//ask user for name
			if (input != null) //cancel not pressed
			{
				gui.setText(converter.convertText(input));
			}
		}
		else if(event.getActionCommand().equals("miPad"))
		{
			StringBuilder newStr = new StringBuilder();
			
			String str = gui.getText();
			String[] lines = str.split("\n");
			
			for(String line: lines)
			{
				if(!line.equals(""))
				{
					newStr.append("&#13;&#13;" + line + "&#13;&#13;\n");
				}
			}
			
			gui.setText(newStr.toString());
		}
	}
	
	class PrinterThread extends Thread
	{
		private TextPrinter printer;
		private String text;
		int delay;
		int initDelay;
		
		public PrinterThread(TextPrinter printer, String text, int delay, int initDelay)
		{
			this.printer = printer;
			this.text = text;
			this.delay = delay;
			this.initDelay = initDelay;
		}
		
		public void run()
		{
			try
			{
				Thread.sleep(initDelay);
			}
			catch(InterruptedException ex)
			{
				
			}
			if(delay>0)
			{
				printer.writeText(text, delay);
			}
			else
			{
				printer.writeText(text);
			}
		}
	}

}
