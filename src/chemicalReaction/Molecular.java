package chemicalReaction;

/**
 * 分子
 * @author ryuryu
 *
 */
public enum Molecular
{
	H2("H-H", "水素"),
	O2("O-O", "酸素"),
	CO2("C-O-O", "二酸化炭素"),
	H2O("H-H-O", "水"),
	NH3("N-H-H-H", "アンモニア"),
	CH4("C-H-H-H-H", "メタン"),
	C2H4("C-C-H-H-H-H", "エチレン"),
	CO("C-O", "一酸化炭素"),
	NO("N-O", "一酸化窒素"),
	N2O("N-N-O", "一酸化二窒素"),
	NO2("N-O-O", "二酸化窒素"),
	Cl2("Cl-O", "塩素"),
	NaCl("Na-Cl", "塩化ナトリウム"),
	CaO("Ca-O", "酸化カルシウム"),
	;

	/** 分子式 */
	private String symbol;
	/** 分子の日本語名 */
	private String name;
	/** 分子を構成する原子の配列 */
	private Element[] elements;

	/**
	 * コンストラクタ
	 * @param name 分子の日本語名
	 */
	private Molecular(String struction, String name)
	{
		symbol = toString();
		this.name = name;
		String[] elements = struction.split("-");
		this.elements = new Element[elements.length];
		for (int i = 0; i < elements.length; i++)
		{
			this.elements[i] = Element.getFromSymbol(elements[i]);
		}
	}

	/**
	 *
	 * @return 分子式
	 */
	public String getSymbol()
	{
		return symbol;
	}

	/**
	 *
	 * @return 分子の日本語名
	 */
	public String getName()
	{
		return name;
	}

	/**
	 *
	 * @return 分子を構成する原子の配列
	 */
	public Element[] getElements()
	{
		return elements;
	}
}
