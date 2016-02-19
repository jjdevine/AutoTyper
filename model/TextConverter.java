package model;

public class TextConverter 
{
	CharConverter cConv = new CharConverter();
	
	public String convertText(String text)
	{
		String[] arrText = {"","","","",""};
		
		for(int index=0; index<text.length(); index++)
		{
			if(index!=0)
			{
				concatenateArrays(arrText, cConv.getCharSpace());
			}
			concatenateArrays(arrText, cConv.convertChar(text.charAt(index)));
		}
		
		StringBuilder result = new StringBuilder();
		
		result.append(arrText[0]);
		result.append("\n");
		result.append(arrText[1]);
		result.append("\n");
		result.append(arrText[2]);
		result.append("\n");
		result.append(arrText[3]);
		result.append("\n");
		result.append(arrText[4]);
		
		return result.toString();
	}
	
	/**
	 * puts contents of arr2 onto the end of contents in arr2, matching the indices
	 * @param arr1
	 * @param arr2
	 */
	public void concatenateArrays(String[] arr1, String[] arr2)
	{
		for(int x=0; x<arr1.length; x++)
		{
			arr1[x] += arr2[x];
		}
	}
}
