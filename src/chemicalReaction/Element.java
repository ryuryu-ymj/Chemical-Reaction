package chemicalReaction;

import java.util.ArrayList;
import java.util.Random;

/**
 * 元素
 * @author ryuryu
 *
 */
public enum Element
{
	H(1, "水素"),
	He(2, "ヘリウム"),
	Li(3, "リチウム"),
	Be(4, "ベリリウム"),
	B(5, "ホウ素"),
	C(6, "炭素"),
	N(7, "窒素"),
	O(8, "酸素"),
	F(9, "フッ素"),
	Ne(10, "ネオン"),
	Na(11, "ナトリウム"),
	Mg(12, "マグネシウム"),
	Al(13, "アルミニウム"),
	Si(14, "ケイ素"),
	P(15, "リン"),
	S(16, "硫黄"),
	Cl(17, "塩素"),
	Ar(18, "アルゴン"),
	K(19, "カリウム"),
	Ca(20, "カルシウム"),
	/*Mn(25, "マンガン"),
	Fe(26, "鉄"),
	Cu(29, "銅"),
	Zn(30, "亜鉛"),
	Ag(47, "銀"),
	I(53, "ヨウ素"),*/
	;

	/** カードの画像の配列番号 */
	private int num;
	/** 元素記号 */
	private String symbol;
	/** 日本語での名前 */
	private String name;

	/**
	 * コンストラクタ
	 * @param num カードの画像の配列番号
	 * @param name 日本語での名前
	 */
	Element(int num, String name)
	{
		this.symbol = toString();
		this.num = num;
		this.name = name;
	}

	/** 元素記号 */
	public String getSymbol()
	{
		return symbol;
	}

	/** カードの画像の配列番号 */
	public int getNum()
	{
		return num;
	}

	/** 日本語での名前 */
	public String getName()
	{
		return name;
	}

	/**
	 * 元素を１つランダムに選ぶ
	 * @return 選ばれた元素
	 */
	public static Element getRandomOne()
	{
	    //Element[] elements = {H, H, H, O, O, O, He, Ne, Ar};
        ArrayList<Element> elements = new ArrayList<>();
        for (int i = 0; i < Molecular.values().length; i++)
        {
            for (int j = 0; j < Molecular.values()[i].getElements().length; j++)
            {
                elements.add(Molecular.values()[i].getElements()[j]);
            }
        }
        //System.out.println(elements);
		int index = new Random().nextInt(elements.size());
		return elements.get(index);
	}

	public static Element getFromSymbol(String symbol)
	{
		return valueOf(symbol);
	}
}
