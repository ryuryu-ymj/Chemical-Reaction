package chemicalReaction;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * ゲームで使う画像の管理、描画
 * @author ryuryu
 *
 */
public class GameImage
{
	/**
	 * カードの表面の画像<p>
	 * 配列番号＝原子番号
	 */
	private static Image[] card;
	static
	{
		try
		{
			card = new Image[22];
			SpriteSheet ss = new SpriteSheet("res/img/card.png", Card.WIDTH, Card.HEIGHT);
			for (int i = 0; i < card.length - 1; i++)
			{
				card[i + 1] = ss.getSubImage(i % 7, i / 7);
			}
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

	/** カードの裏面の画像 */
	private static Image cardBack;
	/** カードを置く枠の画像 */
	private static Image cardFrame;
	static
	{
		try
		{
			SpriteSheet ss = new SpriteSheet("res/img/background.png", Card.WIDTH, Card.HEIGHT);
			cardBack = ss.getSubImage(1, 0);
			cardFrame = ss.getSubImage(0, 0);
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * カードの表面の画像を描画する
	 * @param num カードの配列番号＝原子番号
	 * @param x 画像を表示するx座標
	 * @param y 画像を表示するy座標
	 */
	public static void renderCard(int num, float x, float y)
	{
		card[num].draw(x, y);
	}

	/**
	 * カードの裏面の画像を描画する
	 * @param x 画像を表示するx座標
	 * @param y 画像を表示するy座標
	 */
	public static void renderCardBack(float x, float y)
	{
		cardBack.draw(x, y);
	}

	/**
	 * 
	 * @param num 原子番号
	 * @return カードの表面の画像
	 */
	public static Image getCard(int num)
	{
		return card[num];
	}

	/**
	 * 
	 * @return カードの裏面の画像
	 */
	public static Image getCardBack()
	{
		return cardBack;
	}

	/**
	 * カードを置く枠の画像 を描画する
	 * @param x 画像を表示するx座標
	 * @param y 画像を表示するy座標
	 */
	public static void renderCardFrame(float x, float y)
	{
		cardFrame.draw(x, y);
	}
}
