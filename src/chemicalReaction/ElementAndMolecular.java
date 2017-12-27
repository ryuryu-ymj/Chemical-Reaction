package chemicalReaction;

public interface ElementAndMolecular
{
	/**
	 *
	 * @return 元素記号および分子式
	 */
	public String getSymbol();

	/**
	 *
	 * @return 日本語名
	 */
	public String getName();

	/**
	 *
	 * @return Image.cardでの配列番号
	 */
	public int getNum();
}
