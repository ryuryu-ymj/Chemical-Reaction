package chemicalReaction;

/**
 * 化学反応式を格納する型
 * @author ryuryu
 *
 */
public enum ChemicalEquation
{
	水素の燃焼("2H2+O2=2H2O"),
	;

	/**
	 * 化学反応式の左辺の元素の配列
	 */
	private Element[] inputElements;
	/**
	 * 化学反応式の右辺の元素の配列
	 */
	private Element[] outputElements;
	/**
	 * 反応熱　吸熱ならマイナス
	 */
	private int heatOfReaction;

	/**
	 *
	 * @param chemicalEquation
	 */
	ChemicalEquation(String chemicalEquation)
	{
		String[] sides = chemicalEquation.split("=");
		String[] leftElements = sides[0].split("+");
		String[] rightElements = sides[1].split("+");
		this.inputElements = inputElements;
		this.outputElements = outputElements;
		this.heatOfReaction = heatOfReaction;
	}

	/**
	 * 化学反応式の項を格納する型
	 * @author ryuryu
	 *
	 */
	public class Member
	{
		/**
		 * 項の係数
		 */
		private int num;
		/**
		 * 項の元素
		 */
		Element element;

		public Member(int num, Element element)
		{
			this.num = num;
			this.element = element;
		}
	}
}
