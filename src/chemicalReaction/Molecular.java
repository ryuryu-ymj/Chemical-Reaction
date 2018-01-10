package chemicalReaction;

/**
 * 分子などの化合物
 * @author ryuryu
 *
 */
public enum Molecular
{
	He("He", "ヘリウム"),
	Ne("Ne", "ネオン"),
	Ar("Ar", "アルゴン"),

	H2("H-H", "水素"),
	O2("O-O", "酸素"),
	Cl2("Cl-Cl", "塩素"),
	N2("N-N", "窒素"),
	//I2("I-I", "ヨウ素"),

	CO2("C-O-O", "二酸化炭素"),
	H2O("H-H-O", "水"),
	NH3("N-H-H-H", "アンモニア"),
	CH4("C-H-H-H-H", "メタン"),
	C2H4("C-C-H-H-H-H", "エチレン"),
	C2H6("C-C-H-H-H-H-H-H", "エタン"),
	C2H2("C-C-H-H", "アセチレン"),
	C3H4("C-C-C-H-H-H-H", "プロピン"),
	CH3OH("C-H-H-H-O-H", "メタノール"),
	HCHO("H-C-H-O", "ホルムアルデヒド"),
	CH3CHO("C-H-H-H-C-H-O", "アセトアルデヒド"),
	H2C2O4("H-H-C-C-O-O-O-O", "シュウ酸"),
	CO("C-O", "一酸化炭素"),
	NO("N-O", "一酸化窒素"),
	N2O("N-N-O", "一酸化二窒素"),
	NO2("N-O-O", "二酸化窒素"),
	H2O2("H-H-O-O", "過酸化水素"),
	H2S("H-H-S", "硫化水素"),
	HF("H-F", "フッ化水素"),
	HCl("H-Cl", "塩化水素"),
	//HBr("H-Br", "臭化水素"),
	//HI("H-I", "ヨウ化水素"),
	CH3COOH("C-H-H-H-C-O-O-H", "酢酸"),
	H2CO3("H-H-C-O-O-O", "炭酸"),
	HNO3("H-N-O-O-O", "硝酸"),
	H2SO4("H-H-S-O-O-O-O", "硫酸"),
	H3PO4("H-H-H-P-O-O-O-O", "リン酸"),
	SO2("S-O-O", "二酸化硫黄"),
	O3("O-O-O", "オゾン"),

	CaO("Ca-O", "酸化カルシウム"),
	CaF2("Ca-F-F", "フッ化カルシウム"),
	AlF3("Al-F-F-F", "フッ化アルミニウム"),
	NaF("Na-F", "フッ化ナトリウム"),
	NaH("Na-H", "水素化ナトリウム"),
	//NaI("Na-I", "ヨウ化ナトリウム"),
	NaCl("Na-Cl", "塩化ナトリウム"),
	NaOH("Na-O-H", "水酸化ナトリウム"),
	NaHS("Na-H-S", "硫化水素ナトリウム"),
	NaCN("Na-C-N", "シアン化ナトリウム"),
	NaHF2("Na-H-F-F", "フッ化水素ナトリウム"),
	MgCl2("Mg-Cl-Cl", "塩化マグネシウム"),
	MgF2("Mg-F-F", "フッ化マグネシウム"),
	MgH2("Mg-H-H", "水素化マグネシウム"),
	//MgI2("Mg-I-I", "ヨウ化マグネシウム"),
	MgO("Mg-O", "酸化マグネシウム"),
	MgS("Mg-S", "硫化マグネシウム"),
	
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
