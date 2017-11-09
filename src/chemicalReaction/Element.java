package chemicalReaction;

public enum Element
{
	H(1, "H", "水素"),
	O(8, "O", "酸素");

	private int num;
	private String symbol;
	private String name;

	Element(int num, String symbol, String name)
	{
		this.symbol = symbol;
		this.num = num;
		this.name = name;
	}

	public String getSymbol()
	{
		return symbol;
	}

	public int getNum()
	{
		return num;
	}
}
