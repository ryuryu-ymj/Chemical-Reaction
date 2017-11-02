package chemicalReaction;

/**
 * カードの役の管理
 * @author ryuryu
 *
 */
public class CardSet
{
	/**
	 * 元素記号および分子式<p>
	 * 配列番号は１から
	 */
	private static String[] element = {null, "H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar"};

	/**
	 * 元素記号を原子番号に変換する
	 * @param Symbol 元素記号
	 * @return 原子番号
	 */
	public static int getNumFromSymbol(String symbol)
	{
		for(int i = 1; i < element.length; i++)
		{
			if(element[i] == symbol)
			{
				return i;
			}
		}
		return 0;
	}

	/**
	 * 原子番号を元素記号に変換する
	 * @param num 原子番号
	 * @return 元素記号
	 */
	public static String getSymbolFromNum(int num)
	{
		if(num < element.length && num >= 0)
		{
			return element[num];
		}
		return null;
	}
}
