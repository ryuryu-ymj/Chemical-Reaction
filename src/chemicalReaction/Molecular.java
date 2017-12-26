package chemicalReaction;

/**
 * 分子
 * @author ryuryu
 *
 */
public enum Molecular
{
	H2("水素"),
	O2("酸素"),
	C_O2("二酸化炭素"),
	H2_O("水"),
	N_H3("アンモニア"),
	C_H4("メタン"),
	C2_H4("エチレン"),
	C_O("一酸化炭素"),
	Cl2("塩素"),
	Na_Cl("塩化ナトリウム"),
	Ca_O("酸化カルシウム"),

	;

	/** 元素記号 */
	private String symbol;
	/** 分子の日本語名 */
	private String name;
	/** 分子を構成する原子の配列 */
	private Element[] elements;
	/** 分子を構成する原子の数の配列 */
	private int[] nums;

	private Molecular(String name)
	{
		symbol = toString().replaceAll("_", "");
		this.name = name;
		String[] elementsAndNums = toString().split("_");
		elements = new Element[elementsAndNums.length];
		nums = new int[elementsAndNums.length];
		for (int i = 0; i < nums.length; i++)
		{
			try
			{
				nums[i] = Integer.parseInt("" + elementsAndNums[i].charAt(elementsAndNums[i].length() - 1));
			}
			catch(NumberFormatException e)
			{
				nums[i] = 0;
			}
			if (nums[i] == 0)
			{
				elements[i] = Element.getFromSymbol(elementsAndNums[i].substring(0, elementsAndNums[i].length()));
			}
			else
			{
				elements[i] = Element.getFromSymbol(elementsAndNums[i].substring(0, elementsAndNums[i].length() - 1));
			}
		}
	}

	public String getSymbol()
	{
		return symbol;
	}

	public String getName()
	{
		return name;
	}

	public Element[] getElements()
	{
		return elements;
	}

	public int[] getNums()
	{
		return nums;
	}
}
