package model;

public class TextPrinter 
{
	CharPrinter printer;
	
	public TextPrinter()
	{
		printer = new CharPrinter();
	}
	
	public void writeText(String text)
	{
		for(int x=0; x<text.length(); x++)
		{
			try
			{
				printer.printChar(text.charAt(x));
			}
			catch(Throwable t)
			{
				System.out.println(t.getMessage());
			}
		}
		try
		{
			printer.printChar('\n');
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage());
		}
	}
	
	/**
	 * delay is the number of milliseconds that the method will 
	 * sleep for after printing each line
	 * @param text
	 * @param delay
	 */
	public void writeText(String text, int delay)
	{
		for(int x=0; x<text.length(); x++)
		{
			try
			{

				printer.printChar(text.charAt(x));
				if(text.charAt(x) == '\n')
				{
					Thread.sleep(delay);
				}
			}
			catch(Throwable t)
			{
				System.out.println(t.getMessage());
			}
		}
		try
		{
			//printer.printChar('\n');
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage());
		}
	}
}
