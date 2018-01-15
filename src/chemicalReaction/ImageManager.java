package chemicalReaction;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 * ゲームで使う画像の管理、描画
 * @author ryuryu
 *
 */
public class ImageManager
{
	/**
	 * カードの表面の画像<p>
	 * 配列番号＝原子番号
	 */
	private static Image[] card;
	/** カードの裏面の画像 */
	private static Image cardBack;
	/** カードを置く枠の画像 */
	private static Image cardFrame;
	/** ボタン */
	private static Image button;
	/** "OK"ボタン */
	private static Image buttonOk;
	/** "一枚引く"ボタン */
	private static Image buttonDraw;
	/** 切り札を出す場の枠 */
	private static Image fieldFrame;
	/** 手札を出す枠 */
	private static Image handCardFrame;

	ImageManager()
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

		try
		{
			SpriteSheet ss = new SpriteSheet("res/img/background.png", Card.WIDTH, Card.HEIGHT);
			cardBack = ss.getSubImage(1, 0);
			cardFrame = ss.getSubImage(0, 0);
			//button = ss.getSubImage(2, 0);
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}

		try
		{
			SpriteSheet ss = new SpriteSheet("res/img/button.png", 642, 189);
			button = ss.getSubImage(0, 0);
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}

		try
		{
			SpriteSheet ss = new SpriteSheet("res/img/background2.png", 1280, Card.HEIGHT);
			fieldFrame = ss.getSubImage(0, 0);
			handCardFrame = ss.getSubImage(0, 1);
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
	 * カードを置く枠の画像 を描画する
	 * @param x 画像を表示するx座標
	 * @param y 画像を表示するy座標
	 */
	public static void renderCardFrame(float x, float y)
	{
		cardFrame.draw(x, y);
	}

	public static void renderField(float x, float y)
	{
		fieldFrame.draw(x, y);
	}

	public static void renderButton(float x, float y)
	{
		button.draw(x, y);
	}

	public static void renderHandCardFrame(float x, float y)
	{
		handCardFrame.draw(x, y);
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
	 *
	 * @return ボタンの画像
	 */
	public static Image getButton()
	{
		return button;
	}

	public static Image getField()
	{
		return fieldFrame;
	}

	public static Image getHandCardFrame()
	{
		return handCardFrame;
	}
}
