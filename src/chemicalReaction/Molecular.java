package chemicalReaction;

/**
 * 分子
 * @author ryuryu
 *
 */
public enum Molecular implements ElementAndMolecular
{
	H2(0, "水素"),
	O2(0, "酸素"),
	C_O2(0, "二酸化炭素"),
	H2_O(0, "水"),
	N_H3(0, "アンモニア"),
	C_H4(0, "メタン"),
	C2_H4(0, "エチレン"),
	C_O(0, "一酸化炭素"),
	Cl2(0, "塩素"),
	Na_Cl(0, "塩化ナトリウム"),
	Ca_O(0, "酸化カルシウム"),

	;

	/** 分子式 */
	private String symbol;
	/** 分子の日本語名 */
	private String name;
	/** 分子を構成する原子の配列 */
	private Element[] elements;
	/** 分子を構成する原子の数の配列 */
	private int[] numsOfElement;
	/** Image.cardでの配列番号 */
	private int num;

	/**
	 * コンストラクタ
	 * @param num Image.cardでの配列番号
	 * @param name 分子の日本語名
	 */
	private Molecular(int num, String name)
	{
		symbol = toString().replaceAll("_", "");
		this.name = name;
		String[] elementsAndNums = toString().split("_");
		elements = new Element[elementsAndNums.length];
		numsOfElement = new int[elementsAndNums.length];
		for (int i = 0; i < numsOfElement.length; i++)
		{
			try
			{
				numsOfElement[i] = Integer.parseInt("" + elementsAndNums[i].charAt(elementsAndNums[i].length() - 1));
			}
			catch(NumberFormatException e)
			{
				numsOfElement[i] = 0;
			}
			if (numsOfElement[i] == 0)
			{
				elements[i] = Element.getFromSymbol(elementsAndNums[i].substring(0, elementsAndNums[i].length()));
			}
			else
			{
				elements[i] = Element.getFromSymbol(elementsAndNums[i].substring(0, elementsAndNums[i].length() - 1));
			}
		}
	}

	@Override
	/**
	 *
	 * @return 分子式
	 */
	public String getSymbol()
	{
		return symbol;
	}

	@Override
	/**
	 *
	 * @return 分子の日本語名
	 */
	public String getName()
	{
		return name;
	}

	@Override
	/**
	 * Image.cardでの配列番号
	 */
	public int getNum()
	{
		return num;
	}

	/**
	 *
	 * @return 分子を構成する原子の配列
	 */
	public Element[] getElements()
	{
		return elements;
	}

	/**
	 *
	 * @return 分子を構成する原子の数の配列
	 */
	public int[] getNumsOfElement()
	{
		return numsOfElement;
	}
}
