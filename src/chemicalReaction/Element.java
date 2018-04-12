package chemicalReaction;

import java.util.ArrayList;
import java.util.Random;

/**
 * 元素
 *
 * @author ryuryu
 */
public enum Element
{
    H(1, "水素", 1, 1),
    He(2, "ヘリウム", 1, 18),
    Li(3, "リチウム", 2, 1),
    Be(4, "ベリリウム", 2, 2),
    B(5, "ホウ素", 2, 13),
    C(6, "炭素", 2, 14),
    N(7, "窒素", 2, 15),
    O(8, "酸素", 2, 16),
    F(9, "フッ素", 2, 17),
    Ne(10, "ネオン", 2, 18),
    Na(11, "ナトリウム", 3, 1),
    Mg(12, "マグネシウム", 3, 2),
    Al(13, "アルミニウム", 3, 13),
    Si(14, "ケイ素", 3, 14),
    P(15, "リン", 3, 15),
    S(16, "硫黄", 3, 16),
    Cl(17, "塩素", 3, 17),
    Ar(18, "アルゴン", 3, 18),
    K(19, "カリウム", 4, 1),
    Ca(20, "カルシウム", 4, 2),
	/*Mn(25, "マンガン"),
	Fe(26, "鉄"),
	Cu(29, "銅"),
	Zn(30, "亜鉛"),
	Ag(47, "銀"),
	I(53, "ヨウ素"),*/;

    /**
     * カードの画像の配列番号
     */
    private int num;
    /**
     * 元素記号
     */
    private String symbol;
    /**
     * 日本語での名前
     */
    private String name;
    /**
     * 族
     */
    private int group;
    /**
     * 周期
     */
    private int period;

    /**
     * コンストラクタ
     *
     * @param num    カードの画像の配列番号
     * @param name   日本語での名前
     * @param group  族
     * @param period 周期
     */
    Element(int num, String name, int period, int group)
    {
        this.symbol = toString();
        this.num = num;
        this.name = name;
        this.group = group;
        this.period = period;
    }

    /**
     * 元素記号
     */
    public String getSymbol()
    {
        return symbol;
    }

    /**
     * カードの画像の配列番号
     */
    public int getNum()
    {
        return num;
    }

    /**
     * 日本語での名前
     */
    public String getName()
    {
        return name;
    }

    public int getGroup()
    {
        return group;
    }

    public int getPeriod()
    {
        return period;
    }

    /**
     * 元素を１つランダムに選ぶ
     *
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
