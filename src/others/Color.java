//Author: Zack Oldham

package others;

import pojo.*;

public enum Color 
{
	VISITOR,
	GREEN,
	GOLD,
	ORANGE,
	PURPLE,
	INVALID_COLOR;
	
	
	
	public static Color valueOf(Permit p)
	{
		String pColor = p.getPermitColor().getColor();

		if(pColor.equals("Visitor"))
		{
			return VISITOR;
		}
		else if(pColor.equals("Green"))
		{
			return GREEN;
		}
		else if(pColor.equals("Gold"))
		{
			return GOLD;
		}
		else if(pColor.equals("Orange"))
		{
			return ORANGE;
		}
		else if(pColor.equals("Purple"))
		{
			return PURPLE;
		}
		else
		{
			return INVALID_COLOR;
		}
	}
	
	public static Color getColor(String c)
	{
		String pColor = c;

		if(pColor.equals("Visitor"))
		{
			return VISITOR;
		}
		else if(pColor.equals("Green"))
		{
			return GREEN;
		}
		else if(pColor.equals("Gold"))
		{
			return GOLD;
		}
		else if(pColor.equals("Orange"))
		{
			return ORANGE;
		}
		else if(pColor.equals("Purple"))
		{
			return PURPLE;
		}
		else
		{
			return INVALID_COLOR;
		}
	}

}
