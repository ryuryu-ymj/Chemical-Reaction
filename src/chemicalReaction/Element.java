package chemicalReaction;

import java.util.Random;

public enum Element
{
	H(1, "H", "水素"),
	He(2, "He", "ヘリウム"),
	Li(3, "Li", "リチウム"),
	Be(4, "Be", "ベリリウム"),
	O(8, "O", "酸素");

	/** 原子番号ただし分子や組成式も含む */
	private int num;
	/** 元素記号 */
	private String symbol;
	/** 日本語での名前 */
	private String name;

	/**
	 * コンストラクタ
	 * @param num 原子番号ただし分子や組成式も含む
	 * @param symbol 元素記号
	 * @param name 日本語での名前 
	 */
	Element(int num, String symbol, String name)
	{
		this.symbol = symbol;
		this.num = num;
		this.name = name;
	}

	/** 元素記号 */
	public String getSymbol()
	{
		return symbol;
	}

	/** 原子番号ただし分子や組成式も含む */
	public int getNum()
	{
		return num;
	}

	/** 日本語での名前 */
	public String getName()
	{
		return name;
	}

	public static Element getRandomOne()
	{
		int index = new Random().nextInt(Element.values().length);
		return Element.values()[index];
	}
}
