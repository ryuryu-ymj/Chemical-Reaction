package chemicalReaction;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Card extends GameObject
{
	/**
	 * 縦幅　余白も入れて
	 */
	public static final int HEIGHT = 227;
	/**
	 * 横幅　余白も入れて
	 */
	public static final int WIDTH = 170;
	/**
	 * 余白の枠の幅
	 */
	public static final int SPACE_BREADTH = 23;
	/**
	 * カード番号
	 */
	private int num;
	/**
	 * 元素記号および分子式、組成式
	 */
	public String symbol;
	/**
	 * カードをつかんだときのカードの左上からの座標
	 */
	private static float holdX, holdY;
	/**
	 * 今つかんでいるカード　つかんでいなっかたら-1
	 */
	static int holdCardNum;
	/**
	 * 移動速度
	 */
	private float speedX, speedY;
	/**
	 * そのカードが置かれている座標
	 */
	private float putX, putY;
	/**
	 * カードを自動で動かすときのコマ数
	 */
	private int moveCount;

	/**
	 * コンストラクタ
	 */
	Card()
	{
		active = false;
		holdX = 0;
		holdY = 0;
	}

	@Override
	public void update(GameContainer gc)
	{
		if (moveCount > 1)
		{
			x += speedX;
			y += speedY;
			moveCount--;
		}
		else if (moveCount == 1)
		{
			x = putX;
			y = putY;
			moveCount--;
		}
	}

	@Override
	public void render(Graphics g)
	{
		GameImage.renderCard(num, x, y);
		//img[num].draw((float)(x - width / 2 / 3.5), y - height / 2 / 5, (float)(width * 4.5 / 3.5), height * 6 / 5);
		//System.out.println(CardSet.getSymbolFromNum(0));
		/*g.setColor(Color.blue);
		g.drawRoundRect(x, y, width, height, 7);
		g.drawString(symbol, x, y);*/
	}

	/**
	 * カードをつかむ
	 */
	public void holdCard(GameContainer gc)
	{
		holdX = gc.getInput().getMouseX() - x;
		holdY = gc.getInput().getMouseY() - y;
		//holdCardNum = num;
		//System.out.println(holdX);
	}

	/**
	 * カードを移動させる
	 */
	public void shiftCard(GameContainer gc)
	{
		x = gc.getInput().getMouseX() - holdX;
		y = gc.getInput().getMouseY() - holdY;
	}

	/**
	 * カードを置く
	 */
	public void putdownCard(GameContainer gc)
	{
	}

	/**
	 * カードを自動で滑らかに移動させる
	 * @param afterX 動かす先のx座標
	 * @param afterY 動かす先のy座標
	 * @param moveCount 動かすときのコマ数
	 */
	public void moveCardAuto(int imoveCount)
	{
		moveCount = imoveCount;
		speedX = (putX - x) / moveCount;
		speedY = (putY - y) / moveCount;
	}

	/**
	 * 初期化処理
	 * @param num カード番号
	 * @param symbol 元素記号および分子式、組成式
	 * @param x x成分 最初に表示させるカードの左上の頂点
	 * @param y y成分 最初に表示させるカードの左上の頂点
	 * @param putX そのカードが置かれている座標
	 * @param putY そのカードが置かれている座標
	 */
	public void activate(int num, String symbol, int x, int y, int putX, int putY)
	{
		active = true;
		this.num = num;
		this.symbol = symbol;
		this.x = x;
		this.y = y;
		this.putX = putX;
		this.putY = putY;
	}

	/**
	 *
	 * @return カード番号
	 */
	public int getNum()
	{
		return num;
	}
}
